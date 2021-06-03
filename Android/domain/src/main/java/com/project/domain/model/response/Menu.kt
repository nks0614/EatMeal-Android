package com.project.domain.model.response

data class Menu(
    val foods : ArrayList<Food>,
    val page : Int,
    val size : Int
)

data class Food(
    val name : String,
    val frequency : Int,
    val percent : Double,
    val breakfast : Int,
    val lunch : Int,
    val dinner : Int,
    val star : Double,
    val starMember : ArrayList<MemberStarFood>
) {
    constructor(name : String) : this(name, 0,0.0,0,0,0,0.0, ArrayList())
}

data class MemberStarFood(
    val mac : String,
    val star : Int
)