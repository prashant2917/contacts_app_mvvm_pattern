package com.pocket.kumbhashree.repository

import com.pocket.kumbhashree.model.Contact
import com.pocket.kumbhashree.model.ContactModel

abstract class Repository {
    abstract fun fetchContacts():ContactModel
}