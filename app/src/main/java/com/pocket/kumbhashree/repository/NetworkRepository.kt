package com.pocket.kumbhashree.repository


import com.pocket.kumbhashree.model.ContactModel
import com.pocket.kumbhashree.network.ApiInterface
import com.pocket.kumbhashree.network.RetrofitBuilder
import retrofit2.Response

object NetworkRepository {

    suspend fun fetchContacts(): Response<ContactModel> {
        return RetrofitBuilder.buildService(ApiInterface::class.java).getContacts()

    }
}