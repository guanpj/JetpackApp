package com.me.guanpj.jetpackapp.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Hankkin
 * @date 2019-05-30
 */
@Entity(tableName = "component")
data class Component(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val title: String,
    val description: String,
    val link: String
) : Serializable {
    override fun toString() = title
}