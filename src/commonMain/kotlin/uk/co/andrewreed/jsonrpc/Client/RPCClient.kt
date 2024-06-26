package uk.co.andrewreed.jsonrpc.Client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import uk.co.andrewreed.jsonrpc.Invocation.Invocation
import uk.co.andrewreed.jsonrpc.RequestExecutor.Error
import uk.co.andrewreed.jsonrpc.RequestExecutor.Request
import uk.co.andrewreed.jsonrpc.RequestExecutor.Response
import uk.co.andrewreed.jsonrpc.kermit

class RPCClient(private val url: String) {

    private val requestIdGenerator = RequestIdGenerator()
    private var token: String? = null

    private val ktorClient: HttpClient = HttpClient {
        install(ContentNegotiation) { json() }
        expectSuccess = true
        developmentMode = true
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun <R> invoke(invocation: Invocation<R>) = execute(makeRequest(invocation))

    private fun <R> makeRequest(invocation: Invocation<R>) = Request(requestIdGenerator.next(), invocation)

    private suspend fun <R> execute(request: Request<R>): Response {
        kermit.i("Request -> $request")

        //  convert to response object
        val response = ktorClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(request.buildBody())
            if (!token.isNullOrEmpty()) {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }
        }
        kermit.i("Response -> ${response.bodyAsText()}")
        response.body<JsonObject>()["error"]?.let {
            throw ExecuteException(Json.decodeFromJsonElement<Error>(it))
        }
        return response.body()
    }

    fun setAuthToken(token: String?) {
        this.token = token
    }
}

typealias RequestId = Int

var lastIdx = 0

class RequestIdGenerator {
    fun next(): RequestId {
        lastIdx += 1
        return lastIdx
    }
}

class ExecuteException(val error: Error) : Throwable(error.toString())

abstract class ResultParserError(error: Throwable) : Throwable(error)
class InvalidFormatResultParserError(error: Throwable) : ResultParserError(error)
