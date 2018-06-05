package com.hfad.catchat.Retrofit;

import com.hfad.catchat.Model.Contact;
import com.hfad.catchat.Model.Result;
import com.hfad.catchat.Model.User;
import com.hfad.catchat.Model.UserExtra;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("adduser.php")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("place") String place,
            @Field("phone") String phone,
            @Field("disease") String disease,
            @Field("age") int age,
            @Field("day") String day,
            @Field("dr") String dr);

    @GET("getcontacts.php")
    Call<List<Contact>> getContacts();

    @FormUrlEncoded
    @POST("getcontactbyid.php")
    Call<UserExtra> getContactById(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("editcontact.php")
    Call<User> editContact(
            @Field("id") String id,
            @Field("name") String name,
            @Field("place") String place,
            @Field("phone") String phone,
            @Field("disease") String disease,
            @Field("age") int age,
            @Field("day") String day,
            @Field("dr") String dr);;

}
