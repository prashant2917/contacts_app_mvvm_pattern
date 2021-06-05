package com.pocket.kumbhashree.model

import com.google.gson.annotations.SerializedName

class ContactModel {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("contacts")
    var contactList: List<Contact> = emptyList()

}

class Contact(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("middle_name")
    val middleName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("mobile_no_1")
    val mobileNo1: String? = null,
    @SerializedName("mobile_no_2")
    val mobileNo2: String? = null,
    @SerializedName("profile_image")
    val profileImageUrl: String? = null,
)