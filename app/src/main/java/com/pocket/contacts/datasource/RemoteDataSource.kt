package com.pocket.contacts.datasource

import com.pocket.contacts.model.Contact
import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.model.ResponseModel
import com.pocket.contacts.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun fetchContacts(): Flow<Response<ContactModel>> {
        return flow {
            emit(apiInterface.getContacts())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addContact(contact: Contact): Flow<Response<ResponseModel>> {
        if (contact.profileImageUrl.toString().isEmpty()) {
            return flow {
                emit(
                    apiInterface.addContact(
                        contact.firstName,
                        contact.middleName,
                        contact.lastName,
                        contact.mobileNo,
                        contact.emailId,
                        contact.address
                    )
                )
            }.flowOn(Dispatchers.IO)
        } else {
            return flow {
                emit(
                    apiInterface.addContact(
                        contact.firstName,
                        contact.middleName,
                        contact.lastName,
                        contact.mobileNo,
                        contact.emailId,
                        contact.address,
                        contact.profileImageUrl
                    )
                )
            }.flowOn(Dispatchers.IO)
        }
    }

    suspend fun updateContact(contact: Contact): Flow<Response<ResponseModel>> {
        if (contact.profileImageUrl.toString().isEmpty()) {
            return flow {
                emit(
                    apiInterface.updateContact(
                        contact.id,
                        contact.firstName,
                        contact.middleName,
                        contact.lastName,
                        contact.mobileNo,
                        contact.emailId,
                        contact.address
                    )
                )
            }.flowOn(Dispatchers.IO)
        } else {
            return flow {
                emit(
                    apiInterface.updateContact(
                        contact.id,
                        contact.firstName,
                        contact.middleName,
                        contact.lastName,
                        contact.mobileNo,
                        contact.emailId,
                        contact.address,
                        contact.profileImageUrl
                    )
                )
            }.flowOn(Dispatchers.IO)
        }
    }
}