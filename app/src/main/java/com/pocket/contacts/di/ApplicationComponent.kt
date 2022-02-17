package com.pocket.contacts.di

import com.pocket.contacts.contacts.AddContactFragment
import com.pocket.contacts.contacts.ContactsFragment
import com.pocket.contacts.main.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(loginActivity: MainActivity)
    fun inject(fragment: ContactsFragment)
    fun inject(fragment: AddContactFragment)
}