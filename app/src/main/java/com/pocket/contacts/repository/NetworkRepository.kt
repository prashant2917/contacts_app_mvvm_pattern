package com.pocket.contacts.repository

import com.pocket.contacts.datasource.RemoteDataSource
import com.pocket.contacts.model.Contact
import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.model.ResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun fetchContacts(): Flow<Response<ContactModel>> = remoteDataSource.fetchContacts()

    suspend fun addContact(contact: Contact): Flow<Response<ResponseModel>> =
        remoteDataSource.addContact(contact)

    suspend fun updateContact(contact: Contact): Flow<Response<ResponseModel>> =
        remoteDataSource.updateContact(contact)
}