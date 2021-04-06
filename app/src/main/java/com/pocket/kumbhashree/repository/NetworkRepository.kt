package com.pocket.kumbhashree.repository

import androidx.lifecycle.MutableLiveData
import com.pocket.kumbhashree.model.ContactModel
import com.pocket.kumbhashree.network.ApiInterface
import com.pocket.kumbhashree.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository : Repository() {
    private val request = RetrofitBuilder.buildService(ApiInterface::class.java)
    override fun fetchContacts(): MutableLiveData<ContactModel?> {
        val contactModelMutableLiveData = MutableLiveData<ContactModel?>()
        val call = request.getContacts()
        val contactModel = ContactModel()


        call.enqueue(object : Callback<ContactModel> {
            override fun onResponse(call: Call<ContactModel>, response: Response<ContactModel>) {
                if (response.isSuccessful) {
                    contactModelMutableLiveData.value = response.body()

                } else {
                    contactModel.apply {
                        this.status = NO_DATA
                        this.count = 0
                    }
                    contactModelMutableLiveData.value = contactModel

                }
            }

            override fun onFailure(call: Call<ContactModel>, t: Throwable) {
                contactModel.apply {
                    this.status = ERROR
                    this.count = 0
                }
            }
        })
        return contactModelMutableLiveData
    }
}