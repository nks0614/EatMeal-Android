package com.project.eatmeal.data.response

data class Member(
    val id : String,
    val pw : String,
    val name : String,
    val memberClass : String,
    val starFood : ArrayList<MemberStarFood>
)

data class MemberStarFood(
    val name : String,
    val star : Int
)
