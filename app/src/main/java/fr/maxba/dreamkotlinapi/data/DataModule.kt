package fr.maxba.dreamkotlinapi.data

import androidx.room.Room
import fr.maxba.dreamkotlinapi.data.locale.PeopleDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModule = module {

    single { Room.databaseBuilder(androidApplication(), PeopleDatabase::class.java, PeopleDatabase.DATABASE_NAME).build() }

    single { get<PeopleDatabase>().peopleDao() }

    single { PeopleRepository() }

}