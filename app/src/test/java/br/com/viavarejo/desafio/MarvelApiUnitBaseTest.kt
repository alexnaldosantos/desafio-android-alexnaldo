package br.com.viavarejo.desafio

import br.com.viavarejo.desafio.api.MarvelApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileNotFoundException
import java.net.URL

open abstract class MarvelApiUnitBaseTest {

    protected lateinit var server: MockWebServer
    protected lateinit var api : MarvelApi

    @Before
    fun startMockWebServer() {
        server = MockWebServer()
        server.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MarvelApi::class.java)
    }

    @After
    fun stopMockWebServer() {
        server.shutdown()
    }

    protected fun readFile(path: String): String {
        val content: URL? = ClassLoader.getSystemResource(path)
        return content?.readText() ?: throw FileNotFoundException("file path: $path was not found")
    }
}