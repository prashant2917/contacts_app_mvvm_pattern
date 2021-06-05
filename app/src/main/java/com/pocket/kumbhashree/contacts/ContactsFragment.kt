package com.pocket.kumbhashree.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pocket.kumbhashree.adapter.ContactsAdapter
import com.pocket.kumbhashree.databinding.FragmentContactsBinding
import com.pocket.kumbhashree.viewmodel.ContactsViewModel

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
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

    }

    private fun getContacts() {

        contactsViewModel.fetchContacts().observe(this, {
            it?.contactList?.let { contactList ->
                if (contactList.isEmpty()) {
                    binding.recyclerContacts.visibility = View.GONE
                } else {
                    binding.recyclerContacts.adapter = ContactsAdapter(contactList)
                }
            }


        })


    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {


    }
}

