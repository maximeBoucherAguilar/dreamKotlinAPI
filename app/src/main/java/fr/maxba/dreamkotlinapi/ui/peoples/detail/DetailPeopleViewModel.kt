package fr.maxba.dreamkotlinapi.ui.peoples.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fr.maxba.dreamkotlinapi.data.PeopleRepository
import fr.maxba.dreamkotlinapi.data.model.People
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DetailPeopleViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val peopleRepository: PeopleRepository by inject()

    var peopleId: MutableLiveData<Int> = MutableLiveData()

    var people: LiveData<People> = Transformations.switchMap(peopleId) { id -> peopleRepository.getById(id) }
}