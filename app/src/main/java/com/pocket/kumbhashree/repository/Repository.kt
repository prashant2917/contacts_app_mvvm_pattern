package com.pocket.kumbhashree.repository

import androidx.lifecycle.MutableLiveData
import com.pocket.kumbhashree.model.ContactModel

abstract class Repository {
    abstract fun fetchContacts(): MutableLiveData<ContactModel?>

    companion object {
        const val NO_DATA = "No Data"
        const val ERROR = "Error"

    }
}