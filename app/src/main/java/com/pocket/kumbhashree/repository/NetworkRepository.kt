package com.pocket.kumbhashree.repository

import com.pocket.kumbhashree.model.Contact
import com.pocket.kumbhashree.network.ApiInterface
import com.pocket.kumbhashree.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository : Repository() {
    private val request = RetrofitBuilder.buildService(ApiInterface::class.java)
    override fun fetchContacts(): List<Contact> {
        var list = listOf<Contact>()
        val call = request.getContacts()


        call.enqueue(object : Callback<List<Contact>> {
            override fun onResponse(call: Call<List<Contact>>, response: Response<List<Contact>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        list = it.toList()
                    }

                } else {
                    list = emptyList()
                }
            }

            override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                list = emptyList()
            }
        })
        return list
    }
}