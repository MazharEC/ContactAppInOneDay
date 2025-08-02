package com.appsv.revisecontactapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var phoneNumber: String,
    var email: String,
    var isActive: Boolean,
    var dateOfCreation: Long,
    var image : ByteArray? = null
)