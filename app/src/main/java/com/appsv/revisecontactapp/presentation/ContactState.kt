package com.appsv.revisecontactapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.appsv.revisecontactapp.data.database.Contact

data class ContactState (

    val contacts: List<Contact> = emptyList(),
    val id : MutableState<Int> = mutableStateOf(1),
    val name : MutableState<String> = mutableStateOf(""),
    val phoneNumber : MutableState<String> = mutableStateOf(""),
    val email : MutableState<String> = mutableStateOf(""),
    val isActive : MutableState<Boolean> = mutableStateOf(true),
    val dateOfCreation : MutableState<Long> = mutableStateOf(System.currentTimeMillis()),
    val image : MutableState<ByteArray?> = mutableStateOf(null)

)