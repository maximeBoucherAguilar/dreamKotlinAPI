package fr.maxba.dreamkotlinapi.data.remote

import com.google.gson.annotations.SerializedName

data class PeoplesListResponse ( //TODO modif

    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String,

    @SerializedName("previous")
    val previous: String,

    @SerializedName("results")
    val results: List<PeopleResponse>
)