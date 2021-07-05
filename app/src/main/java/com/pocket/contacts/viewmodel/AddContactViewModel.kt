package com.pocket.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.contacts.model.Contact
import com.pocket.contacts.model.ResponseModel
import com.pocket.contacts.repository.NetworkRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddContactViewModel : ViewModel() {
    fun addContact(contact: Contact): MutableLiveData<ResponseModel?> {
        val mutableLiveDataResponseModel = MutableLiveData<ResponseModel?>()
        viewModelScope.launch {
            NetworkRepository.addContact(contact).collect { response ->
                if (response.isSuccessful) {
                    response.body().let {
                        mutableLiveDataResponseModel.value = it
                    }
                }
            }
        }
        return mutableLiveDataResponseModel
    }

    fun updateContact(contact: Contact): MutableLiveData<ResponseModel?> {
        val mutableLiveDataResponseModel = MutableLiveData<ResponseModel?>()
        viewModelScope.launch {
            NetworkRepository.updateContact(contact).collect { response ->
                if (response.isSuccessful) {
                    response.body().let {
                        mutableLiveDataResponseModel.value = it
                    }
                }
            }
        }
        return mutableLiveDataResponseModel
    }
}