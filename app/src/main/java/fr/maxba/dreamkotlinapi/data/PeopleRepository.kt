package fr.maxba.dreamkotlinapi.data

import android.util.Log
import androidx.lifecycle.LiveData
import fr.maxba.dreamkotlinapi.data.locale.PeopleDao
import fr.maxba.dreamkotlinapi.data.model.People
import fr.maxba.dreamkotlinapi.data.remote.PeopleResponse
import fr.maxba.dreamkotlinapi.data.remote.PeopleService
import fr.maxba.dreamkotlinapi.data.remote.PeoplesResponseCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class PeopleRepository: KoinComponent {

    private val peopleDao: PeopleDao by inject()

    private val service = PeopleService.create()

    //region locale

    fun insertAll(peoples: List<People>) = doAsync {
        peopleDao.insertAll(peoples)
        Log.d("PeopleRepository","inserting peoples: $peoples")
    }

    fun insert(people: People) =
        insertAll(listOf(people))

    fun delete(people: People) = doAsync { peopleDao.delete(people) }

    fun getById(id: Int): LiveData<People> = peopleDao.getById(id)

    fun getAll(): LiveData<List<People>> = peopleDao.getAllLive()

    //endregion

    //region remote

    fun downloadPeoples(callback: PeoplesResponseCallback) {
        service.getTopRatedPeoples()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { peoplesListResponse -> insertAll(peoplesListResponse.results.map { peopleResponse -> peopleResponseToPeople(peopleResponse) }) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    fun downloadPeoplesWithExtraInfos(callback: PeoplesResponseCallback) {
        service.getTopRatedPeoples()
            .subscribeOn(Schedulers.io())
            .flatMap { peoplesListResponse -> Observable.fromIterable(peoplesListResponse.results) }
            .flatMap { peopleShortResponse -> service.getPeople(peopleShortResponse.id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { peopleFullResponse -> insert(peopleResponseToPeople(peopleFullResponse)) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    private fun peopleResponseToPeople(peopleResponse: PeopleResponse): People =
        People(
            id = peopleResponse.id,
            name = peopleResponse.name,
            birth_year = peopleResponse.birth_year,
            eye_color = peopleResponse.eye_color,
            gender = peopleResponse.gender
        )

    //endregion
}