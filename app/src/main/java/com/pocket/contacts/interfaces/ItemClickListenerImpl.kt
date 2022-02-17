package com.pocket.contacts.interfaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pocket.contacts.contacts.ContactsFragment
import com.pocket.contacts.contacts.ContactsFragmentDirections
import com.pocket.contacts.model.Contact

class ItemClickListenerImpl : ItemClickListener {
    override fun onItemClick(contact: Contact, fragment: Fragment) {
        val bundle = Bundle()
        bundle.putParcelable(ContactsFragment.KEY_OBJECT_CONTACT, contact)
        bundle.putBoolean(ContactsFragment.KEY_IS_EDIT, true)
        val action = ContactsFragmentDirections.goToAddContact(contact, true)
        fragment.findNavController().navigate(action)
    }
}