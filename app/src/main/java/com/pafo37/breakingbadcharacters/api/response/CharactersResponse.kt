package com.pafo37.breakingbadcharacters.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CharactersResponse(
    @JsonProperty("char_id") var id: Int,
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
)