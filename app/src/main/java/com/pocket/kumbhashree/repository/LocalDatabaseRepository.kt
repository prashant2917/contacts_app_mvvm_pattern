package com.pocket.kumbhashree.repository

import androidx.lifecycle.MutableLiveData
import com.pocket.kumbhashree.model.ContactModel

class LocalDatabaseRepository : Repository() {
    override fun fetchContacts(): MutableLiveData<ContactModel?> {
        TODO("Not yet implemented")
    }
}