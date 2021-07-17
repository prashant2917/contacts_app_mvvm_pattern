package com.pocket.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {
    private var mutableLiveDataContactsModel = MutableLiveData<ContactModel?>()

    fun fetchContacts(): MutableLiveData<ContactModel?> {
        viewModelScope.launch(Dispatchers.Main) {
            NetworkRepository.fetchContacts().collect { response ->
                if (response.isSuccessful) {
                    response.body().let {
                        mutableLiveDataContactsModel.value = it
                    }
                }
            }
        }

        return mutableLiveDataContactsModel
    }
}