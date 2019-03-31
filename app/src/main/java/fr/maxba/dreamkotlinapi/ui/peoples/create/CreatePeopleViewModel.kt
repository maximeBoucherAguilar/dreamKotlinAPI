package fr.maxba.dreamkotlinapi.ui.peoples.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import fr.maxba.dreamkotlinapi.data.PeopleRepository
import fr.maxba.dreamkotlinapi.data.model.People
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class CreatePeopleViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val peopleRepository: PeopleRepository by inject()

    var name: MutableLiveData<String> = MutableLiveData()

    var eye_color: MutableLiveData<String> = MutableLiveData()

    var birth_year: MutableLiveData<String> = MutableLiveData()

    var gender: MutableLiveData<String> = MutableLiveData()

    fun insert() {
        peopleRepository.insert(
            People(
                name = name.value?.capitalize() ?: "",
                eye_color = eye_color.value?.capitalize() ?: "",
                birth_year = birth_year.value?.capitalize() ?: "",
                gender = gender.value?.capitalize() ?: ""
            )
        )
    }
}