package com.pocket.kumbhashree.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pocket.kumbhashree.R
import com.pocket.kumbhashree.viewmodel.ContactsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var contactsViewModel: ContactsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        contactsViewModel.setIsOffline(false)
        contactsViewModel.fetchContacts().observe(this, Observer {


        })

    }


}