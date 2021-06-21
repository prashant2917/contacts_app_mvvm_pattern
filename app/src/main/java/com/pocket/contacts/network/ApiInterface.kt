package com.pocket.contacts.network

import com.pocket.contacts.model.ContactModel
import com.pocket.contacts.model.ResponseModel
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("get_contacts")
    suspend fun getContacts(): Response<ContactModel>

    @FormUrlEncoded
    @POST("register_user")
    suspend fun addContact(
        @Field("first_name") firstName: String?,
        @Field("middle_name") middleName: String?,
        @Field("last_name") lastName: String?,
        @Field("mobile_no_1") mobileNoOne: String?,
        @Field("mobile_no_2") mobileNoTwo: String?,
        @Field("address") address: String?
    ): Response<ResponseModel>

    @FormUrlEncoded
    @POST("update_user")
    suspend fun upDateContact(
        @Field("id") id : Int?,
        @Field("first_name") firstName: String?,
        @Field("middle_name") middleName: String?,
        @Field("last_name") lastName: String?,
        @Field("mobile_no_1") mobileNoOne: String?,
        @Field("mobile_no_2") mobileNoTwo: String?,
        @Field("address") address: String?
    ): Response<ResponseModel>

}