package com.pocket.contacts.model

import com.google.gson.annotations.SerializedName

class ContactModel {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("contacts")
    var contactList: List<Contact> = emptyList()

}

class Contact {

    @SerializedName("first_name")
    var firstName: String? = ""

    @SerializedName("middle_name")
    var middleName: String? = ""

    @SerializedName("last_name")
    var lastName: String? = ""

    @SerializedName("address")
    var address: String? = ""

    @SerializedName("mobile_no_1")
    var mobileNo1: String? = ""

    @SerializedName("mobile_no_2")
    var mobileNo2: String? = ""

    @SerializedName("profile_image")
    var profileImageUrl: String? = ""
}