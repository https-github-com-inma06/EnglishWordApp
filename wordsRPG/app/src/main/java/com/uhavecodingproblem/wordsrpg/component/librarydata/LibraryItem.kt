package com.uhavecodingproblem.wordsrpg.component.librarydata

/**
 * wordsrpg
 * Class: LibraryItem
 * Created by pyg10.
 * Created On 2020-09-23.
 * Description:
 */
data class LibraryItem(var category: String, // 종류 수준별, 카테고리별, 시험별 등등
                       var education: String, // 수준별의 학력(초등, 중등, 고등)
                       var grade: String, // 수준별의 학년(1학년, 2학년...)
                       var word: String, // 단어
                       var mean: String) // 의미
