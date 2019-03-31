package fr.maxba.dreamkotlinapi.data.remote

interface PeoplesResponseCallback {

    fun onSuccess()

    fun onError(throwable: Throwable)
}