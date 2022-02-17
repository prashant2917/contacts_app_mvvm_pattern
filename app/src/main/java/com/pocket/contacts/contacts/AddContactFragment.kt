package com.pocket.contacts.contacts

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.pocket.contacts.ContactApplication
import com.pocket.contacts.R
import com.pocket.contacts.adapter.ContactsAdapter
import com.pocket.contacts.databinding.FragmentAddContactsBinding
import com.pocket.contacts.extensions.*
import com.pocket.contacts.model.Contact
import com.pocket.contacts.validator.ValidatorFactory
import com.pocket.contacts.validator.ValidatorType
import com.pocket.contacts.viewmodel.AddContactViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactsBinding
    @Inject
    lateinit var addContactViewModel: AddContactViewModel
    private lateinit var contact: Contact
    private var isEdit: Boolean = false
    private var imageUrl: String = ""
    private var imageBase64: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val application = activity?.application as ContactApplication
        application.component.inject(this)
    }

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
        binding.progressVisibility = View.GONE
        binding.btnSubmit.setOnClickListener(submitListener)
        binding.ivEdit.setOnClickListener(editClickListener)
        if (isEdit) {
            binding.contact = contact
            imageUrl = contact.profileImageUrl.toString()
            if (imageUrl.isNotEmpty()) {
                binding.progressVisibility = View.VISIBLE
                activity?.disableUserInteraction()
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        imageBase64 = convertToBase64(ContactsAdapter.PROFILE_PIC_URL + imageUrl)
                    }
                    binding.progressVisibility = View.GONE
                    activity?.enableUserInteraction()
                }
            }
        }

        loadImageFromGlide(ContactsAdapter.PROFILE_PIC_URL + imageUrl, binding.ivProfile)
    }

    private val submitListener = View.OnClickListener {
        val validatorName = ValidatorType.ValidatorTypeName()
        val result = ValidatorFactory.getValidator(validatorName)
            .validate(binding.etFirstName, getString(R.string.error_enter_valid_first_name)) &&
                ValidatorFactory.getValidator(validatorName)
                    .validate(
                        binding.etMiddleName,
                        getString(R.string.error_enter_valid_middle_name)
                    ) &&
                ValidatorFactory.getValidator(validatorName)
                    .validate(
                        binding.etLastName,
                        getString(R.string.error_enter_valid_last_name)
                    ) &&
                ValidatorFactory.getValidator(ValidatorType.ValidatorTypeMobile())
                    .validate(
                        binding.etMobileNumber,
                        getString(R.string.error_enter_valid_mobile_number)
                    ) &&
                ValidatorFactory.getValidator(ValidatorType.ValidatorEmail())
                    .validate(
                        binding.etEmailId,
                        getString(R.string.error_enter_Valid_email_address)
                    ) &&
                ValidatorFactory.getValidator(ValidatorType.ValidatorAddress())
                    .validate(binding.etAddress, getString(R.string.error_enter_Valid_address))

        if (result) {
            val contact = buildContact()

            if (isEdit) {
                updateContact(contact)
            } else {
                addContact(contact)
            }
        }
    }
    private val editClickListener = View.OnClickListener {
        ImagePicker.with(this)
            .galleryOnly()
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
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
            mobileNo = binding.etMobileNumber.text.toString()
            emailId = binding.etEmailId.text.toString()
            address = binding.etAddress.text.toString()
            profileImageUrl = imageBase64
        }
    }

    private fun addContact(contact: Contact) {
        binding.progressVisibility = View.VISIBLE
        activity?.disableUserInteraction()
        addContactViewModel.addContact(contact).observe(this) { responseModel ->
            showToast(responseModel?.message.toString())
            binding.progressVisibility = View.GONE
            activity?.enableUserInteraction()
            findNavController().popBackStack()
        }
    }

    private fun updateContact(contact: Contact) {
        binding.progressVisibility = View.VISIBLE
        activity?.disableUserInteraction()
        addContactViewModel.updateContact(contact).observe(this) { responseModel ->
            showToast(responseModel?.message.toString())
            binding.progressVisibility = View.GONE
            activity?.enableUserInteraction()
            findNavController().popBackStack()
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data!!
                    loadImageFromGlide(fileUri, binding.ivProfile)
                    imageBase64 = convertToBase64(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    showToast(ImagePicker.getError(data))
                }
                else -> {
                    showToast("Task Cancelled")
                }
            }
        }
}