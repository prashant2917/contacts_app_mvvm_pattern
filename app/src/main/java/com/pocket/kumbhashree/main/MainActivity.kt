package com.pocket.kumbhashree.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pocket.kumbhashree.R
import com.pocket.kumbhashree.repository.NetworkRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository= NetworkRepository()
        repository.fetchContacts()
    }
}