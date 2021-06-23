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
        if (contact.profileImageUrl.toString().isEmpty()) {
            return RetrofitBuilder.buildService(ApiInterface::class.java).addContact(
                contact.firstName,
                contact.middleName,
                contact.lastName,
                contact.mobileNo,
                contact.emailId,
                contact.address
            )
        } else {
            return RetrofitBuilder.buildService(ApiInterface::class.java).addContact(
                contact.firstName,
                contact.middleName,
                contact.lastName,
                contact.mobileNo,
                contact.emailId,
                contact.address,
                contact.profileImageUrl
            )
        }
    }

    suspend fun updateContact(contact: Contact): Response<ResponseModel> {
        if (contact.profileImageUrl.toString().isEmpty()) {
            return RetrofitBuilder.buildService(ApiInterface::class.java).updateContact(
                contact.id,
                contact.firstName,
                contact.middleName,
                contact.lastName,
                contact.mobileNo,
                contact.emailId,
                contact.address
            )
        } else {
            return RetrofitBuilder.buildService(ApiInterface::class.java).updateContact(
                contact.id,
                contact.firstName,
                contact.middleName,
                contact.lastName,
                contact.mobileNo,
                contact.emailId,
                contact.address,
                contact.profileImageUrl
            )
        }
    }
}