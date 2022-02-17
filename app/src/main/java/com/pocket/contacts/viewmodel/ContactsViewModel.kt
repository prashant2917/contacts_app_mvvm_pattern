package com.pocket.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactsViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {
    private var mutableLiveDataContactsModel = MutableLiveData<ContactModel?>()

    fun fetchContacts(): MutableLiveData<ContactModel?> {
        viewModelScope.launch(Dispatchers.Main) {
            networkRepository.fetchContacts().collect { response ->
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