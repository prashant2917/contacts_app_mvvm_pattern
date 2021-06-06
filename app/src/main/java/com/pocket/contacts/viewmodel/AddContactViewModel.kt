package com.pocket.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.contacts.model.Contact
import com.pocket.contacts.model.ResponseModel
import com.pocket.contacts.repository.NetworkRepository
import kotlinx.coroutines.launch

class AddContactViewModel : ViewModel() {
    private var mutableLiveDataResponseModel = MutableLiveData<ResponseModel?>()
    fun addContact(contact: Contact): MutableLiveData<ResponseModel?> {
        viewModelScope.launch {
            val response = NetworkRepository.addContact(contact)
            if (response.isSuccessful) {
                response.body().let {
                    mutableLiveDataResponseModel.value = it
                }
            }
        }
        return mutableLiveDataResponseModel
    }
}