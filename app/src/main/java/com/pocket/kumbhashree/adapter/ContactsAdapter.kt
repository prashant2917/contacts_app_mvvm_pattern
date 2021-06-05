package com.pocket.kumbhashree.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pocket.kumbhashree.databinding.RowContactsBinding
import com.pocket.kumbhashree.model.Contact


class ContactsAdapter(private var contactList: List<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = RowContactsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        with(holder) {
            val name =
                "${contactList[position].firstName} ${contactList[position].middleName}  ${contactList[position].lastName}"
            this.binding.tvName.text = name
            this.binding.tvMobile.text = "${contactList[position].mobileNo1}"
            this.binding.tvAddress.text = "${contactList[position].address}"
            val imageUrl = contactList[position].profileImageUrl.toString()
            if (imageUrl?.isNotEmpty()) {
                Glide.with(binding.root.context).load(PROFILE_PIC_URL + imageUrl)
                    .into(binding.ivProfile);
            }


        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ContactsViewHolder(val binding: RowContactsBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val PROFILE_PIC_URL = "https://pocketappz.com/upload/avatar/"

    }
}



