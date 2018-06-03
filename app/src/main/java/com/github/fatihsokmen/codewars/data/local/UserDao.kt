package com.github.fatihsokmen.codewars.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
abstract class UserDao {

    @Query("SELECT * FROM User ORDER BY date DESC LIMIT 5")
    abstract fun getUsers(): Flowable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(user: UserEntity)

    @Query("DELETE FROM User")
    abstract fun deleteAll()
}
