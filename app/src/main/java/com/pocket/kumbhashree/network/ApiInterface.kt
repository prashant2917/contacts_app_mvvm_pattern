package com.pocket.kumbhashree.network

import com.pocket.kumbhashree.model.Contact
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("get_contacts")
    fun getContacts(): Call<List<Contact>>
}