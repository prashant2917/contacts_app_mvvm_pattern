package com.pocket.contacts.viewmodel

import com.pocket.contacts.model.ContactModel
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ContactsViewModelTest {
    @Test
    fun fetchContactsSuccess() {
        val contactModel = getContactModel()
        assertTrue(contactModel.count > 0)
    }
    @Test
    fun fetchContactsFailure() {
        val contactModel = ContactModel()
        assertTrue(contactModel.count == 0)
    }

    private fun getContactModel(): ContactModel {
        var contactModel = ContactModel()
        return contactModel.apply {
            this.count = 10
            this.status = "ok"
        }
    }
}