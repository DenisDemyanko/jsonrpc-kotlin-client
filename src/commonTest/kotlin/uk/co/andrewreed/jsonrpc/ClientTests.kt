package uk.co.andrewreed.jsonrpc

//expect fun runTest(test: suspend () -> Unit)

private val goerli = "https://eth-goerli.g.alchemy.com/v2/ri0TxzkaKF-VwB95D1Np8EmmQ1qcG8tH"
private val ropsten = "https://ropsten.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"
private val local = "http://127.0.0.1:8545"

// Tests are using Ganache
class ClientTests {
//    private val kermit = Kermit()
//
//    private val clientUrl = goerli
//
//    @Test
//    fun testPost() = runTest {
//        val client = RPCClient(clientUrl)
//        val service = object : RPCService(client) {
//            suspend fun gasPrice(): String {
//                val gasPrice = invoke("eth_gasPrice")
//                kermit.v("$gasPrice")
//                return gasPrice.content
//            }
//        }
//        val price = service.gasPrice()
//        // assertEquals("0x4a817c800", price)
//    }
//
//    // invoke("eth_getBalance", arrayOf(address)).content
//    @Test
//    fun testBalance() = runTest {
//        val client = RPCClient(clientUrl)
//        val service = object : RPCService(client) {
//            suspend fun balance(address: String): String {
//                val bal = invoke("eth_getBalance", JsonArray(listOf(JsonPrimitive(address), JsonPrimitive("latest"))))
//                kermit.v("$bal")
//                return bal.content
//            }
//        }
//        val balance = service.balance("0xF0C66B225FDA2fe9e0C54ce9B345F8A103c1Dca0")
//        assertEquals("0x3782dace9d90000", balance)
//    }
//
//    @Test
//    fun testPostWithParams() = runTest {
//        val client = RPCClient(clientUrl)
//        val service = object : RPCService(client) {
//            suspend fun sha(): String {
//                val sha = invoke("web3_sha3", JsonArray(listOf(JsonPrimitive("0x68656c6c6f20776f726c64"))))
//                kermit.v("$sha")
//                return sha.content
//            }
//        }
//        val shaResult = service.sha()
//        assertEquals("0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad", shaResult)
//    }
//
//    @Test
//    fun testPostCallWithParams() = runTest {
//        val client = RPCClient(clientUrl)
//        val map = mapOf(
//            "to" to JsonPrimitive("0x6cd7d44516a20882cEa2DE9f205bF401c0d23570"),
//            "data" to JsonPrimitive("0xfe50cc72")
//        )
//        val service = object : RPCService(client) {
//            suspend fun call(): String {
//                val resp = invoke(
//                    "eth_call",
//                    JsonArray(listOf(JsonObject(map)))
//                )
//                kermit.v("$resp")
//                return resp.content
//            }
//        }
//        val result = service.call()
//        assertEquals("0x0000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000b48656c6c6f20576f726c64000000000000000000000000000000000000000000", result)
//    }
//
//    @Test
//    fun testPostSerializableParams() = runTest {
//        val client = RPCClient(clientUrl)
//        val data = CallObject("0xF7e4B57862EC47A9B059b8D2D051bBd3A8A64A14", "0xfe50cc72")
//        val jsonElement = Json.encodeToJsonElement(CallObject.serializer(), data)
//        val service = object : RPCService(client) {
//            suspend fun call(): String {
//                val resp = invoke(
//                    "eth_call",
//                    JsonArray(listOf(jsonElement))
//                )
//                kermit.v("$resp")
//                return resp.content
//            }
//        }
//        val result = service.call()
//        assertEquals("0x0000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000b48656c6c6f20576f726c64000000000000000000000000000000000000000000", result)
//    }
//
//    @Serializable
//    data class CallObject(
//        val to: String,
//        val data: String
//    )
//
//    @Test
//    fun testPostInvalid() = runTest {
//        try {
//            val client = RPCClient(clientUrl)
//            val service = object : RPCService(client) {
//                suspend fun execute() { invoke("web3_sha3") }
//            }
//            service.execute()
//            fail("Should of thrown exception")
//        } catch (exception: Throwable) {
//            assertTrue(exception is ExecuteException)
//            assertEquals(-32602, exception.error.code)
//        }
//    }
//
//    @Test
//    fun testPostNoEndPoint() = runTest {
//        try {
//            val client = RPCClient("http://x")
//            val service = object : RPCService(client) {
//                suspend fun execute() { invoke("fsfdsfdfsdf") }
//            }
//            service.execute()
//            fail("Should of thrown exception")
//        } catch (exception: Throwable) {
//            assertFalse(exception is ExecuteException)
//        }
//    }
}
