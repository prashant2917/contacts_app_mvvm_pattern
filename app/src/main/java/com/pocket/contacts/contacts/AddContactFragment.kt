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
    private var _binding: FragmentAddContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var addContactViewModel : AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        addContactViewModel = ViewModelProviders.of(this).get(AddContactViewModel::class.java)
        binding.btnSubmit.setOnClickListener(submitListener)
    }

    private val submitListener = View.OnClickListener {
       val contact = Contact()
        contact.apply {
            firstName= binding.etFirstName.text.toString()
            middleName= binding.etMiddleName.text.toString()
            lastName= binding.etLastName.text.toString()
            mobileNo1= binding.etMobileOne.text.toString()
            mobileNo2= binding.etMobileTwo.text.toString()
            address= binding.etAddress.text.toString()
        }
        addContact(contact)

    }

    private fun addContact(contact:Contact){

        addContactViewModel.addContact(contact).observe(this, { responseModel ->
            showToast(responseModel?.message.toString())
            if(responseModel?.status==1) {
                findNavController().popBackStack()
            }

        })
    }
}