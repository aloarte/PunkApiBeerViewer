package com.p4r4d0x.punkapibeerviewer

import com.google.gson.Gson
import com.p4r4d0x.punkapibeerviewer.TestingConstants.Companion.TEST_DTO_BEER_JSON_VALUE_EMPTY
import com.p4r4d0x.punkapibeerviewer.TestingConstants.Companion.TEST_DTO_BEER_JSON_VALUE_FEW_ELEMENTS
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class DTOBeerUnitTest {


    private lateinit var gson: Gson
    @Before
    fun setup() {
        gson = Gson()
    }


    @Test
    fun jsonParse_fewElements() {
        //Parse from json into a class
        val dtoBeerRead: List<BeerDTO> =
            gson.fromJson(TEST_DTO_BEER_JSON_VALUE_FEW_ELEMENTS, Array<BeerDTO>::class.java)
                .toList()
        assertNotNull(dtoBeerRead)
        //Parse back from the class to the json value
        val jsonDtoBeerRead = gson.toJson(dtoBeerRead)
        assertEquals(TEST_DTO_BEER_JSON_VALUE_FEW_ELEMENTS, jsonDtoBeerRead)
    }

    @Test
    fun jsonParse_empty() {
        //Parse from json into a class
        val dtoBeerRead: List<BeerDTO> =
            gson.fromJson(TEST_DTO_BEER_JSON_VALUE_EMPTY, Array<BeerDTO>::class.java).toList()
        assertNotNull(dtoBeerRead)
        //Parse back from the class to the json value
        val jsonDtoBeerRead = gson.toJson(dtoBeerRead)
        assertEquals(TEST_DTO_BEER_JSON_VALUE_EMPTY, jsonDtoBeerRead)
    }
}
