package fr.maxba.dreamkotlinapi.data.locale

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.maxba.dreamkotlinapi.data.model.People

@Dao
interface PeopleDao {
    
    @Query("SELECT * FROM people WHERE id = :id")
    fun getById(id: Int): LiveData<People>

    @Query("SELECT * FROM People")
    fun getAllLive(): LiveData<List<People>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(people: People)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<People>)

    @Delete
    fun delete(people: People)
}