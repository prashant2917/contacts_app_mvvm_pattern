package com.pocket.contacts.interfaces

import androidx.fragment.app.Fragment
import com.pocket.contacts.model.Contact

interface ItemClickListener {
    fun onItemClick(contact: Contact, fragment: Fragment)
}