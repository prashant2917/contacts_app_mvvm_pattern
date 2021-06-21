package com.pocket.contacts.repository

import com.pocket.contacts.model.Contact
import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.model.ResponseModel
import com.pocket.contacts.network.ApiInterface
import com.pocket.contacts.network.RetrofitBuilder
import retrofit2.Response

object NetworkRepository {
    suspend fun fetchContacts(): Response<ContactModel> {
        return RetrofitBuilder.buildService(ApiInterface::class.java).getContacts()
    }

    suspend fun addContact(contact: Contact): Response<ResponseModel> {
        return RetrofitBuilder.buildService(ApiInterface::class.java).addContact(
            contact.firstName,
            contact.middleName,
            contact.lastName,
            contact.mobileNo1,
            contact.mobileNo2,
            contact.address
        )
    }

    suspend fun updateContact(contact: Contact): Response<ResponseModel> {
        return RetrofitBuilder.buildService(ApiInterface::class.java).upDateContact(
            contact.id,
            contact.firstName,
            contact.middleName,
            contact.lastName,
            contact.mobileNo1,
            contact.mobileNo2,
            contact.address
        )
    }
}