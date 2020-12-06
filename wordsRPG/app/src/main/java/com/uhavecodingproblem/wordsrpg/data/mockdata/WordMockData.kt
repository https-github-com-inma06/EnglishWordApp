package com.uhavecodingproblem.wordsrpg.data.mockdata

import com.uhavecodingproblem.wordsrpg.data.StageInformation
import com.uhavecodingproblem.wordsrpg.data.WordInformation
import com.uhavecodingproblem.wordsrpg.data.PackageInformation

/**
 * wordsrpg
 * Class: WordMockData
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
object WordMockData {

    var wordMockData = initMockData()

    private fun initClearWordMockData(): MutableList<WordInformation>{
        val wordClearData = mutableListOf<WordInformation>()

        wordClearData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과","Apple is red fruit",  isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과","Apple is red fruit",  isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과","Apple is red fruit",  isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과","Apple is red fruit",  isStudyPassed = true, isTestPassed = true))
        wordClearData.add(WordInformation("Apple", "사과","Apple is red fruit",  isStudyPassed = true, isTestPassed = true))

        return wordClearData
    }

    private fun initWordMockData(): MutableList<WordInformation>{
        val wordData = mutableListOf<WordInformation>()

        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Banana", "바나나", "Banana is yellow fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))
        wordData.add(WordInformation("Apple", "사과", "Apple is red fruit", isStudyPassed = false, isTestPassed = false))

        return wordData
    }

    private fun initStageMockData(): MutableList<StageInformation>{
        val stage = mutableListOf<StageInformation>()

        stage.add(StageInformation(1, 3, initClearWordMockData()))
        stage.add(StageInformation(2, 3, initClearWordMockData()))
        stage.add(StageInformation(3, 0, initWordMockData()))
        stage.add(StageInformation(4, 0, initWordMockData()))

        return stage
    }

    private fun initStageSecondMockData(): MutableList<StageInformation>{
        val stage = mutableListOf<StageInformation>()

        stage.add(StageInformation(1, 3, initClearWordMockData()))
        stage.add(StageInformation(2, 3, initClearWordMockData()))
        stage.add(StageInformation(3, 3, initClearWordMockData()))
        stage.add(StageInformation(4, 3, initClearWordMockData()))
        stage.add(StageInformation(5, 3, initClearWordMockData()))
        stage.add(StageInformation(6, 3, initClearWordMockData()))
        stage.add(StageInformation(7, 3, initClearWordMockData()))
        stage.add(StageInformation(8, 3, initClearWordMockData()))
        stage.add(StageInformation(9, 3, initClearWordMockData()))
        stage.add(StageInformation(10, 3, initClearWordMockData()))
        stage.add(StageInformation(11, 3, initClearWordMockData()))
        stage.add(StageInformation(12, 1, initWordMockData()))
        stage.add(StageInformation(13, 0, initWordMockData()))

        return stage
    }

    private fun initStageThirdMockData(): MutableList<StageInformation>{
        val stage = mutableListOf<StageInformation>()

        stage.add(StageInformation(1, 3, initClearWordMockData()))
        stage.add(StageInformation(2, 3, initClearWordMockData()))
        stage.add(StageInformation(3, 3, initClearWordMockData()))
        stage.add(StageInformation(4, 3, initClearWordMockData()))
        stage.add(StageInformation(5, 3, initClearWordMockData()))
        stage.add(StageInformation(6, 3, initClearWordMockData()))
        stage.add(StageInformation(7, 3, initClearWordMockData()))
        stage.add(StageInformation(8, 3, initClearWordMockData()))
        stage.add(StageInformation(9, 3, initClearWordMockData()))
        stage.add(StageInformation(10, 3, initClearWordMockData()))
        stage.add(StageInformation(11, 3, initClearWordMockData()))
        stage.add(StageInformation(12, 2, initWordMockData()))
        stage.add(StageInformation(13, 0, initWordMockData()))
        stage.add(StageInformation(14, 0, initWordMockData()))
        stage.add(StageInformation(15, 0, initWordMockData()))
        stage.add(StageInformation(16, 0, initWordMockData()))
        stage.add(StageInformation(17, 0, initWordMockData()))
        stage.add(StageInformation(18, 0, initWordMockData()))

        return stage
    }

    private fun initMockData(): MutableList<PackageInformation>{
        val wordData = mutableListOf<PackageInformation>()

        wordData.add(PackageInformation("A", "수준별", "초등학생 1학년", "https://file.namu.moe/file/55b888377938a32b1021ee98af8e3dc9e870263f6038d6e36f02a931de823749", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "초등학생 2학년", "https://file.namu.moe/file/55b888377938a32b1021ee98af8e3dc9e870263f6038d6e36f02a931de823749", initStageSecondMockData()))
        wordData.add(PackageInformation("A", "수준별", "초등학생 3학년", "https://file.namu.moe/file/55b888377938a32b1021ee98af8e3dc9e870263f6038d6e36f02a931de823749", initStageThirdMockData()))
        wordData.add(PackageInformation("A", "수준별", "초등학생 4학년", "https://file.namu.moe/file/55b888377938a32b1021ee98af8e3dc9e870263f6038d6e36f02a931de823749", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "초등학생 5학년", "https://file.namu.moe/file/55b888377938a32b1021ee98af8e3dc9e870263f6038d6e36f02a931de823749", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "초등학생 6학년", "https://file.namu.moe/file/55b888377938a32b1021ee98af8e3dc9e870263f6038d6e36f02a931de823749", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "중학생 1학년", "https://post-phinf.pstatic.net/MjAxOTA0MTdfMjQ4/MDAxNTU1NDgyMjI1ODUz.qrhIasLWnqlJl74jqOQ5RVdvQUjQ0xCQnhOTx2pv8Tog.y8NeBRF0hryA_NR9-PWdYmTIpQCBfcUlH62YBjXFZgAg.JPEG/tip014j17090242.jpg?type=w1200", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "중학생 2학년", "https://post-phinf.pstatic.net/MjAxOTA0MTdfMjQ4/MDAxNTU1NDgyMjI1ODUz.qrhIasLWnqlJl74jqOQ5RVdvQUjQ0xCQnhOTx2pv8Tog.y8NeBRF0hryA_NR9-PWdYmTIpQCBfcUlH62YBjXFZgAg.JPEG/tip014j17090242.jpg?type=w1200", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "중학생 3학년", "https://post-phinf.pstatic.net/MjAxOTA0MTdfMjQ4/MDAxNTU1NDgyMjI1ODUz.qrhIasLWnqlJl74jqOQ5RVdvQUjQ0xCQnhOTx2pv8Tog.y8NeBRF0hryA_NR9-PWdYmTIpQCBfcUlH62YBjXFZgAg.JPEG/tip014j17090242.jpg?type=w1200", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "고등학생 1학년", "https://imagescdn.gettyimagesbank.com/500/201810/jv11187636.jpg", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "고등학생 2학년", "https://imagescdn.gettyimagesbank.com/500/201810/jv11187636.jpg", initStageMockData()))
        wordData.add(PackageInformation("A", "수준별", "고등학생 3학년", "https://imagescdn.gettyimagesbank.com/500/201810/jv11187636.jpg", initStageMockData()))

        wordData.add(PackageInformation("A", "시험별", "토익", "https://img.huffingtonpost.com/asset/5d814d8e3b00002b88d66359.jpeg?ops=scalefit_630_noupscale", initStageMockData()))
        wordData.add(PackageInformation("A", "시험별", "토플", "data:image/jpeg;base64,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", initStageMockData()))

        return wordData
    }


}