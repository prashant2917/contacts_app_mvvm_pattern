package com.pocket.kumbhashree.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pocket.kumbhashree.model.ContactModel
import com.pocket.kumbhashree.repository.LocalDatabaseRepository
import com.pocket.kumbhashree.repository.NetworkRepository
import com.pocket.kumbhashree.repository.Repository

class ContactsViewModel : ViewModel() {
    private var mutableLiveDataContactsModel = MutableLiveData<ContactModel?>()
    private val liveDataIsOffline = MutableLiveData<Boolean>()
    fun fetchContacts(): MutableLiveData<ContactModel?> {

        val repository = getRepository()
        mutableLiveDataContactsModel = repository.fetchContacts()
        return mutableLiveDataContactsModel
    }

    fun setIsOffline(isOffline: Boolean) {
        liveDataIsOffline.value = isOffline
    }

    private fun getIsOffline(): Boolean? {
        return liveDataIsOffline.value
    }

    private fun getRepository(): Repository {
        return if (getIsOffline() == true) {
            LocalDatabaseRepository()
        } else {
            NetworkRepository()
        }
    }
}