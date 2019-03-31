package fr.maxba.dreamkotlinapi.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.maxba.dreamkotlinapi.data.model.People

@Database(entities = [People::class], version = 1, exportSchema = false)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    companion object {
        const val DATABASE_NAME = "PeopleDatabase"
    }
}