package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * wordsrpg
 * Class: BasicPackage
 * Created by pyg10.
 * Created On 2021-04-05.
 * Description:
 */
object ResponseBasicPackage {

    data class ResponseBasic(
        val success: Boolean,
        @SerializedName("package") var basicPackage: MutableList<BasicPackage>
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readValue(Boolean::class.java.classLoader) as Boolean,
            parcel.createTypedArrayList(BasicPackage) as MutableList<BasicPackage>
        )

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.run {
                writeValue(success)
                writeTypedList(basicPackage)
            }
        }

        companion object CREATOR : Parcelable.Creator<ResponseBasic> {
            override fun createFromParcel(parcel: Parcel): ResponseBasic {
                return ResponseBasic(parcel)
            }

            override fun newArray(size: Int): Array<ResponseBasic?> {
                return arrayOfNulls(size)
            }
        }

    }

    @Entity
    data class BasicPackage(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "p_id")
        @SerializedName("p_id") val packageID: Int,
        @SerializedName("package_name") val packageName: String,
        @SerializedName("package_thumbnail") val packageThumbNail: String,
        @SerializedName("stage") val stageList: MutableList<Stage>
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.createTypedArrayList(Stage) as MutableList<Stage>
        )

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.run {
                writeInt(packageID)
                writeString(packageName)
                writeString(packageThumbNail)
                writeTypedList(stageList)
            }
        }

        companion object CREATOR : Parcelable.Creator<BasicPackage> {
            override fun createFromParcel(parcel: Parcel): BasicPackage {
                return BasicPackage(parcel)
            }

            override fun newArray(size: Int): Array<BasicPackage?> {
                return arrayOfNulls(size)
            }
        }
    }

    @Entity
    data class Stage(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "s_id")
        @SerializedName("s_id") val stageID: Int,
        @SerializedName("lean_stat") var stageLearningStatus: String,
        @SerializedName("test_stat") var stageTestStatus: String,
        @SerializedName("lock_stat") var stageLockStatus: String,
        @SerializedName("currentPage") var stageCurrentPage: Int,
        @SerializedName("score") var stageScore: Int?,
        @SerializedName("word") val wordList: MutableList<Word>
    ) : Parcelable{

        constructor(parcel: Parcel): this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.createTypedArrayList(Word) as MutableList<Word>
        )

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.run {
                writeInt(stageID)
                writeString(stageLearningStatus)
                writeString(stageTestStatus)
                writeString(stageLockStatus)
                writeInt(stageCurrentPage)
                writeValue(stageScore)
                writeTypedList(wordList)
            }
        }
        companion object CREATOR : Parcelable.Creator<Stage> {
            override fun createFromParcel(parcel: Parcel): Stage {
                return Stage(parcel)
            }

            override fun newArray(size: Int): Array<Stage?> {
                return arrayOfNulls(size)
            }
        }

    }

    @Entity
    data class Word(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "w_id")
        @SerializedName("w_id") val wordID: Int,
        @SerializedName("word") val word: String,
        @SerializedName("mean") val mean: String,
        @SerializedName("example") val wordExample: String?,
        @SerializedName("videoUri") val wordVideoUri: String?
    ): Parcelable{
        constructor(parcel: Parcel): this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.run {
                writeInt(wordID)
                writeString(word)
                writeString(mean)
                writeString(wordExample)
                writeString(wordVideoUri)
            }
        }
        companion object CREATOR : Parcelable.Creator<Word> {
            override fun createFromParcel(parcel: Parcel): Word {
                return Word(parcel)
            }

            override fun newArray(size: Int): Array<Word?> {
                return arrayOfNulls(size)
            }
        }
    }
}