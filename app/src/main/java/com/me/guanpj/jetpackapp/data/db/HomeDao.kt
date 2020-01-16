package com.me.guanpj.jetpackapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.me.guanpj.jetpackapp.data.bean.Component

/**
 * @author Hankkin
 * @date 2019-05-30
 */
@Dao
interface HomeDao {

    @Query("SELECT * FROM component ORDER BY id")
    fun getComponents(): LiveData<List<Component>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Component>)
}