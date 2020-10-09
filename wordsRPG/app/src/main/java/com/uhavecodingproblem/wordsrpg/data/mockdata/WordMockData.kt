package com.uhavecodingproblem.wordsrpg.data.mockdata

import com.uhavecodingproblem.wordsrpg.data.WordData
import com.uhavecodingproblem.wordsrpg.data.WordType

/**
 * wordsrpg
 * Class: WordMockData
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
object WordMockData {

    val wordMockData = initTypeMockData()

    private fun initElementaryOneMockData(): MutableList<WordData>{
        val elementaryOne = mutableListOf<WordData>()
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = false))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = true))
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = false))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = true))
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = false))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = true))
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = false))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = true))
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = false))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = false))
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = true))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = true))
        elementaryOne.add(WordData(word = "Apple", mean = "사과", isPassed = false))
        elementaryOne.add(WordData(word = "Banana", mean = "바나나", isPassed = true))
        return elementaryOne
    }

    private fun initTestMockData(): MutableList<WordData>{
        val testData = mutableListOf<WordData>()

        testData.add(WordData(word = "Alphabet", mean = "알파벳", isPassed = true))
        testData.add(WordData(word = "Alphabet", mean = "알파벳", isPassed = true))
        testData.add(WordData(word = "Alphabet", mean = "알파벳", isPassed = true))

        return testData
    }

    private fun initTypeMockData(): MutableList<WordType> {
        val list = mutableListOf<WordType>()
        list.add(WordType(type = "수준별", name = "초등학교 1학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg", words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 2학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 3학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 4학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 5학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg", words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 6학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "중학교 1학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "중학교 2학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "중학교 3학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "고등학교 1학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "고등학교 2학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "고등학교 3학년", thumbnailImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "시험별", name = "토익", thumbnailImage = "https://img.huffingtonpost.com/asset/5d814d8e3b00002b88d66359.jpeg?ops=scalefit_630_noupscale", words = initTestMockData()))
        list.add(WordType(type = "시험별", name = "토플", thumbnailImage = "https://i1.daumcdn.net/thumb/C230x300/?fname=https://blog.kakaocdn.net/dn/Oo7VA/btqGG8qcrwy/p0O8FA5TfIC3BlJMIlatWk/img.jpg", words = initTestMockData()))
        return list
    }

}