package com.pocket.kumbhashree.repository

import android.util.Log
import com.pocket.kumbhashree.model.Contact
import com.pocket.kumbhashree.model.ContactModel
import com.pocket.kumbhashree.network.ApiInterface
import com.pocket.kumbhashree.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository : Repository() {
    private val request = RetrofitBuilder.buildService(ApiInterface::class.java)
    override fun fetchContacts():ContactModel{
         var  contactModel  = ContactModel()
        val call = request.getContacts()


        call.enqueue(object : Callback<ContactModel> {
            override fun onResponse(call: Call<ContactModel>, response: Response<ContactModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        contactModel= ContactModel()
                        contactModel.status = it.status
                        contactModel.count = it.count
                        contactModel.contactList = it.contactList
                        Log.d("###","contact model is "+contactModel.status + contactModel.contactList.size)
                    }

                } else {

                    Log.d("###","list empty")
                }
            }

            override fun onFailure(call: Call<ContactModel>, t: Throwable) {

                Log.d("###","list empty failure"+t.message)
            }
        })
      return contactModel
    }
}