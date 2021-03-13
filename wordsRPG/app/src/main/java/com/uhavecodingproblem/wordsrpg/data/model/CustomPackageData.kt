package com.uhavecodingproblem.wordsrpg.data.model

/**
 * wordsrpg
 * Class: CustomPackageData.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 * 커스텀 패키지 데이터 클래스
 */

data class CustomPackageData(
   val c_id:String,
   val ownerName:String,
   val packageName:String,
   val profileImageUrl:String?,
   val likeCount:Int,
   val subscribeCount:Int,
   val hashTagList: MutableList<String> //Variable Name Change :: hashList -> hashTagList. fixed by atanasio.
)