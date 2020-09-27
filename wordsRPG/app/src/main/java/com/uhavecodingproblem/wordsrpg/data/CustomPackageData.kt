package com.uhavecodingproblem.wordsrpg.data

/**
 * wordsrpg
 * Class: CustomPackageData.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 * 커스텀 패키지 데이터 클래스
 */

data class CustomPackageData(
   val uid:Int,
   val ownerUid:Int,
   val packageName:String,
   val profileImageUrl:String?,
   val likeCount:Int,
   val subscribeCount:Int,
   val hashList: MutableList<String>
)