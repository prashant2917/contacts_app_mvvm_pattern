package com.pocket.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {
    private var mutableLiveDataContactsModel = MutableLiveData<ContactModel?>()
    private val liveDataIsOffline = MutableLiveData<Boolean>()
    fun fetchContacts(): MutableLiveData<ContactModel?> {

        viewModelScope.launch(Dispatchers.Main) {
            val response = NetworkRepository.fetchContacts()
            if (response.isSuccessful) {
                response.body().let {
                    mutableLiveDataContactsModel.value = it
                }

            }
        }

        return mutableLiveDataContactsModel
    }

    fun setIsOffline(isOffline: Boolean) {
        liveDataIsOffline.value = isOffline
    }


}