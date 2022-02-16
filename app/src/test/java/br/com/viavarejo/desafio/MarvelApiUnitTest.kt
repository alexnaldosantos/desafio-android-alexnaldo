package br.com.viavarejo.desafio

import br.com.viavarejo.desafio.api.MarvelApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileNotFoundException
import java.net.URL


class MarvelApiUnitTest : MarvelApiUnitBaseTest() {

    @Test
    fun `should hit endpoints with expected parameters for getCharacters`() = runBlocking {
        // given
        val body = super.readFile("characters.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        super.api.getCharacters("name","ts","apiKey","hash",0,1)
        val request = super.server.takeRequest()
        // then
        assertEquals(request.path,"/v1/public/characters?orderBy=name&ts=ts&apikey=apiKey&hash=hash&offset=0&limit=1")
    }

    @Test
    fun `should getCharacters returns expected minimal data`() = runBlocking {
        // given
        val body = super.readFile("characters.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        val result = super.api.getCharacters("name","ts","apiKey","hash",0,1)
        // then
        assertEquals(result.code, 200)
        assertEquals(result.status, "Ok")
        assertNotNull(result.data)
        assertEquals(result.data.offset,0)
        assertEquals(result.data.limit,1)
        assertEquals(result.data.total,1559)
        assertEquals(result.data.count,1)
        assertNotNull(result.data.results)
        assertEquals(result.data.results.size,1)
        assertEquals(result.data.results[0].id,1011334)
        assertEquals(result.data.results[0].name,"3-D Man")
        assertEquals(result.data.results[0].description,"")
        assertNotNull(result.data.results[0].thumbnail)
        assertEquals(result.data.results[0].thumbnail.path,"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784")
        assertEquals(result.data.results[0].thumbnail.extension,"jpg")
        assertEquals(result.data.results[0].thumbnail.imageUrl,"https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
    }

    @Test
    fun `should hit endpoints with expected parameters for getComicsByCharacterId`() = runBlocking {
        // given
        val body = super.readFile("characters.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        super.api.getComicsByCharacterId("1234", "ts", "apiKey", "hash")
        val request = super.server.takeRequest()
        // then
        assertEquals(
            request.path,
            "/v1/public/characters/1234/comics?ts=ts&apikey=apiKey&hash=hash"
        )
    }

    @Test
    fun `should getComicsByCharacterId returns expected minimal data`() = runBlocking {
        // given
        val body = super.readFile("hq.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        val result = super.api.getComicsByCharacterId("1009144", "ts", "apiKey", "hash")
        // then
        assertEquals(result.code, 200)
        assertEquals(result.status, "Ok")
        assertNotNull(result.data)
        assertEquals(result.data.offset,3)
        assertEquals(result.data.limit,1)
        assertEquals(result.data.total,52)
        assertEquals(result.data.count,1)
        assertNotNull(result.data.results)
        assertEquals(result.data.results.size,1)
        assertEquals(result.data.results[0].id,65466)
        assertEquals(result.data.results[0].title,"Captain America by Mark Waid, Ron Garney & Andy Kubert (Hardcover)")
        assertEquals(result.data.results[0].description,"One of the most celebrated runs in Captain America history - collected in full! The world believes Cap dead, but his life is saved by his greatest love - and his deadliest enemy! Will America celebrate his return, or will he become a man without a country? After facing Onslaught, Cap will find himself reborn once more - only to lose his shield! But there's no slowing down for the Sentinel of Liberty as Cap takes on Hydra, Skrulls, A.I.M. and more; unites with Iron Man against M.O.D.O.K.; and battles Korvac and the Red Skull for the fate of the future! Plus: Tales from World War II and more - from all eras of the Sentinel of Liberty's star-studded history! Collecting CAPTAIN AMERICA (1968) #444-454, CAPTAIN AMERICA (1998) #1-23, IRON MAN/CAPTAIN AMERICA ANNUAL '98, CAPTAIN AMERICA: SENTINEL OF LIBERTY #1-12 and ROUGH CUT, and material from CAPTAIN AMERICA: THE LEGEND and CAPTAIN AMERICA: RED, WHITE & BLUE.")
        assertNotNull(result.data.results[0].thumbnail)
        assertEquals(result.data.results[0].thumbnail!!.path,"http://i.annihil.us/u/prod/marvel/i/mg/8/50/5a21c1a2471bf")
        assertEquals(result.data.results[0].thumbnail!!.extension,"jpg")
        assertEquals(result.data.results[0].thumbnail!!.imageUrl,"https://i.annihil.us/u/prod/marvel/i/mg/8/50/5a21c1a2471bf.jpg")
    }
}