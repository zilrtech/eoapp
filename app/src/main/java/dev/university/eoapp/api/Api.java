package dev.university.eoapp.api;

import dev.university.eoapp.models.DefaultResponse;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.Provider_response;
import dev.university.eoapp.models.RequetsResponse;
import dev.university.eoapp.models.addnewoferrmodel;
import dev.university.eoapp.models.category_response;
import dev.university.eoapp.models.chat_mesage_response;
import dev.university.eoapp.models.myResponse;

import dev.university.eoapp.models.myevents_response;
import dev.university.eoapp.models.offers_response;
import dev.university.eoapp.models.user_info_response;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("createrequest")
    Call<DefaultResponse> createrequest(
            @Field("title") String title,
            @Field("details") String details,
            @Field("user_id") String user_id,

            @Field("image") String image

    );

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("users/register")
    Call<LoginResponse> userregister(
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("password") String password,
            @Field("phone_number") String phone_number,
            @Field("city") String city,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("users/update")
    Call<LoginResponse> userupdate(
            @Field("user_id") String user_id,
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("password") String password,
            @Field("phone_number") String phone_number,
            @Field("city") String city
    );

    @FormUrlEncoded
    @POST("users/register")
    Call<LoginResponse> providerregister(
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("password") String password,
            @Field("phone_number") String phone_number,
            @Field("city") String city,
            @Field("type") String type,
            @Field("license") String license,
            @Field("work") String work,
            @Field("name") String name

    );



    @FormUrlEncoded
    @POST("users/update")
    Call<LoginResponse> providerupdate(
            @Field("user_id") String user_id,
            @Field("email") String email,
            @Field("name") String firstname,
            @Field("password") String password,
            @Field("phone_number") String phone_number,
            @Field("city") String city,
            @Field("license") String license,
            @Field("work") String work


    );

    @FormUrlEncoded
    @POST("users/update")
    Call<LoginResponse> user_update(
            @Field("user_id") String user_id,
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("password") String password,
            @Field("phone_number") String phone_number,
            @Field("city") String city

    );


    @FormUrlEncoded
    @POST("event/insert")
    Call<LoginResponse> addevent(
            @Field("client_id") String client_id,
            @Field("location") String location,
            @Field("idea") String idea,
            @Field("guests") String guests,
            @Field("type") String type,
            @Field("date") String date,
            @Field("gender") String gender,
            @Field("name") String name,
            @Field("budget") String budget


    );

    @GET("users/info")
    Call<user_info_response> get_user_info(@Query("user_id") String user_id);

    @GET("shop/providers")
    Call<Provider_response> getproviders();

    @GET("category/list")
    Call<category_response> getcategories();

    @GET("category/offers")
    Call<offers_response> getoffes(@Query("category_id") String category_id);

    @GET("event/list")
    Call<myevents_response> get_cat_events(@Query("category_id") String category_id);

    @GET("event/offers")
    Call<offers_response> get_event_offes(@Query("event_id") String event_id);



    @FormUrlEncoded
    @POST("event/acceptoffer")
    Call<LoginResponse> acceptoffer(
            @Field("event_id") String event_id,
            @Field("offer_id") String offer_id,
            @Field("message") String message

    );

    @FormUrlEncoded
    @POST("event/addoffer")
    Call<LoginResponse> addtoffer(
            @Field("event_id") String event_id,
            @Field("provider_id") String provider_id,
            @Field("price") String price

    );
    @FormUrlEncoded
    @POST("chat/sendmessage")
    Call<LoginResponse> sendmessage(
            @Field("message") String message,
            @Field("event_id") String event_id,
            @Field("isClient") String isClient

    );





    @FormUrlEncoded
    @POST("event/offertocategory")
    Call<addnewoferrmodel> sendofffer(
            @Field("title") String title,
            @Field("cat_id") String cat_id,
            @Field("provider_id") String provider_id,
            @Field("deatils") String deatils

    );





    @GET("event/delete")
    Call<myevents_response> delete_offer(@Query("event_id") String event_id);

    @GET("event/mylist")
    Call<myevents_response> get_my_events(@Query("client_id") String client_id);



    @GET("chat/list")
    Call<myevents_response> get_chat(@Query("client_id") String client_id);


    @GET("chat/messages")
    Call<chat_mesage_response> get_chat_message(@Query("event_id") String event_id);


    @GET("event/details")
    Call<myevents_response> get_event_details(@Query("event_id") String event_id);


    @FormUrlEncoded
    @POST("myrequests")
    Call<RequetsResponse> getmyrequests(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("getresponse")
    Call<myResponse> getmyresponse(
            @Field("request_id") int request_id
    );


    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);

}
