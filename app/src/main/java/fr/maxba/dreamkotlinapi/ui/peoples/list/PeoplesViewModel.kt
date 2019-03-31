package fr.maxba.dreamkotlinapi.ui.peoples.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.maxba.dreamkotlinapi.data.PeopleRepository
import fr.maxba.dreamkotlinapi.data.model.People
import fr.maxba.dreamkotlinapi.data.remote.PeoplesResponseCallback
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PeoplesViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val peopleRepository: PeopleRepository by inject()

    var peoples: LiveData<List<People>> = peopleRepository.getAll()

    fun delete(people: People) {
        peopleRepository.delete(people)
    }

    fun refresh(callback: PeoplesResponseCallback) {
        peopleRepository.downloadPeoples(callback)
    }
}