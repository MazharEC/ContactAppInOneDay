package com.appsv.revisecontactapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts ORDER BY 'user name' ASC")
    fun getContactsSortedByName(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY phoneNumber ASC")
    fun getContactsSortedByPhoneNumber(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY dateOfCreation ASC")
    fun getContactsSortedByDate(): Flow<List<Contact>>


}
