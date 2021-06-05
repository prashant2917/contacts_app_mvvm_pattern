package com.pocket.kumbhashree.network

import com.pocket.kumbhashree.model.ContactModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("get_contacts")
    suspend fun getContacts(): Response<ContactModel>


}