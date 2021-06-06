package com.pocket.contacts.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pocket.contacts.R
import com.pocket.contacts.adapter.ContactsAdapter
import com.pocket.contacts.databinding.FragmentContactsBinding
import com.pocket.contacts.viewmodel.ContactsViewModel

class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private lateinit var contactsViewModel: ContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        getContacts()
    }

    private fun init() {
        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        contactsViewModel.setIsOffline(false)
        binding.swipeRefreshLayout.setOnRefreshListener(refreshListener)
        binding.fabAdd.setOnClickListener(fabClickListener)
    }

    private val fabClickListener =
        View.OnClickListener { findNavController().navigate(R.id.go_to_add_contact) }

    private fun getContacts() {
        contactsViewModel.fetchContacts().observe(this, {
            if (it?.status == "ok" && it.count > 0) {
                it.contactList.let { contactList ->
                    if (contactList.isEmpty()) {
                        binding.recyclerContacts.visibility = View.GONE
                    } else {
                        binding.recyclerContacts.adapter = ContactsAdapter(contactList)
                    }
                }
            }

        })

    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        getContacts()
        binding.swipeRefreshLayout.isRefreshing = false

    }
}

