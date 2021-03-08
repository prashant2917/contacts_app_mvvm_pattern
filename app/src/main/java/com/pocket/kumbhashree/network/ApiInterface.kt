package com.pocket.kumbhashree.network

import com.pocket.kumbhashree.model.Contact
import com.pocket.kumbhashree.model.ContactModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("get_contacts")
    fun getContacts(): Call<ContactModel>
}