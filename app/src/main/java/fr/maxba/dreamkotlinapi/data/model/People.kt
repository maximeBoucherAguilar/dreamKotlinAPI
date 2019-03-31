package fr.maxba.dreamkotlinapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class People(

    @PrimaryKey
    override var id: Int = 0,

    var name: String? = null,

    var birth_year: String? = null,

    var eye_color: String? = null,

    var gender: String? = null

//    var hair_color: String? = null,
//
//    var heignt: String? = null,
//
//    var homeworld: String? = null,
//
//    var mass: String? = null,

): BaseObject