package com.appsv.revisecontactapp.data.di

import android.app.Application
import androidx.room.Room
import com.appsv.revisecontactapp.data.database.ContactDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideDatabase(application: Application): ContactDataBase {
        return Room.databaseBuilder(
            application.baseContext,
            ContactDataBase::class.java,
            "contacts.db"
        ).fallbackToDestructiveMigration().build()

    }
}