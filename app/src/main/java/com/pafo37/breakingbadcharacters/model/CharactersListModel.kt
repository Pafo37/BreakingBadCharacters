package com.pafo37.breakingbadcharacters.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharactersListModel(
    var id: Int,
    var name: String,
    var birthday: String,
    var occupation: List<String>,
    var img: String,
    var status: String,
    var nickname: String,
    var appearance: List<Int>,
    var portrayed: String,
    var category: String,
    var betterCallSaulAppearance: List<Int>?
) : Parcelable