package com.pocket.kumbhashree.repository

import com.pocket.kumbhashree.model.Contact

abstract class Repository {
    abstract fun fetchContacts(): List<Contact>
}