package com.pocket.contacts.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pocket.contacts.databinding.FragmentAddContactsBinding
import com.pocket.contacts.extensions.showToast
import com.pocket.contacts.model.Contact
import com.pocket.contacts.viewmodel.AddContactViewModel

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactsBinding
    private lateinit var addContactViewModel: AddContactViewModel
    private lateinit var contact: Contact
    private var isEdit: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        contact = arguments?.getParcelable(ContactsFragment.KEY_OBJECT_CONTACT)!!
        isEdit = arguments?.getBoolean(ContactsFragment.KEY_IS_EDIT)!!
        addContactViewModel = ViewModelProviders.of(this).get(AddContactViewModel::class.java)
        binding.progressVisibility =View.GONE
        binding.btnSubmit.setOnClickListener(submitListener)
        if (isEdit) {
            binding.contact = contact
        }
    }

    private val submitListener = View.OnClickListener {
        val contact = buildContact()

        if (isEdit) {
            updateContact(contact)
        } else {
            addContact(contact)
        }
    }

    private fun buildContact(): Contact {
        val contactObj = Contact()
        return contactObj.apply {
            if (isEdit) {
                id = contact.id
            }
            firstName = binding.etFirstName.text.toString()
            middleName = binding.etMiddleName.text.toString()
            lastName = binding.etLastName.text.toString()
            mobileNo1 = binding.etMobileOne.text.toString()
            mobileNo2 = binding.etMobileTwo.text.toString()
            address = binding.etAddress.text.toString()
        }
    }

    private fun addContact(contact: Contact) {
        binding.progressVisibility = View.VISIBLE
        addContactViewModel.addContact(contact).observe(this, { responseModel ->
            showToast(responseModel?.message.toString())
            binding.progressVisibility =View.GONE
            findNavController().popBackStack()
        })
    }

    private fun updateContact(contact: Contact) {
        binding.progressVisibility =View.VISIBLE
        addContactViewModel.updateContact(contact).observe(this, { responseModel ->
            showToast(responseModel?.message.toString())
            binding.progressVisibility =View.GONE
            findNavController().popBackStack()
        })
    }
}