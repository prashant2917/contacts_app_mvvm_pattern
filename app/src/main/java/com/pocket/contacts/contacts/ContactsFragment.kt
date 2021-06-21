package com.pocket.contacts.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pocket.contacts.adapter.ContactsAdapter
import com.pocket.contacts.databinding.FragmentContactsBinding
import com.pocket.contacts.interfaces.ItemClickListener
import com.pocket.contacts.model.Contact
import com.pocket.contacts.viewmodel.ContactsViewModel

class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var contactsAdapter: ContactsAdapter

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
        View.OnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(KEY_OBJECT_CONTACT,Contact())
            bundle.putBoolean(KEY_IS_EDIT,false)
            val action = ContactsFragmentDirections.goToAddContact(Contact(),false)
            findNavController().navigate(action)
        }

    private fun getContacts() {
        binding.swipeRefreshLayout.isRefreshing =true
        contactsViewModel.fetchContacts().observe(this, {
            if (it?.status == "ok" && it.count > 0) {
                binding.contactModel = it
                contactsAdapter = ContactsAdapter(it.contactList, onItemClickListener)
                binding.recyclerContacts.adapter = contactsAdapter
                binding.swipeRefreshLayout.isRefreshing = false

            }

        })

    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        getContacts()


    }

    private val onItemClickListener = object : ItemClickListener {
        override fun onItemClick(contact: Contact) {
            val bundle = Bundle()
            bundle.putParcelable(KEY_OBJECT_CONTACT,contact)
            bundle.putBoolean(KEY_IS_EDIT,true)
            val action = ContactsFragmentDirections.goToAddContact(contact,true)
            findNavController().navigate(action)
        }
    }

    companion object {
        const val KEY_OBJECT_CONTACT = "contact"
        const val KEY_IS_EDIT = "is_edit"

    }
}

