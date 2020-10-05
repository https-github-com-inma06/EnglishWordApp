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

    private fun initTypeMockData(): MutableList<WordType> {
        val list = mutableListOf<WordType>()
        list.add(WordType(type = "수준별", name = "초등학교 1학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg", words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 2학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 3학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 4학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 5학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg", words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "초등학교 6학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "중학교 1학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "중학교 2학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "중학교 3학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "고등학교 1학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "고등학교 2학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        list.add(WordType(type = "수준별", name = "고등학교 3학년", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",  words = initElementaryOneMockData()))
        return list
    }

}