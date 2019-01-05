package com.saneforce.logistics.API_interface;


import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.Add_New_Customer_Success;
import com.saneforce.logistics.Customer_Me.Customer_Me_Success;
import com.saneforce.logistics.Customer_Profile_Pojo.Name_Ofthe_Company_Success;
import com.saneforce.logistics.Enquiry_Me.Enquiry_Me_Success;


import java.util.Map;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {
    //REgister API
    @Headers({
            "Content-type:application/x-www-form-urlencoded",
            "Accept:application/json"
    })
    @FormUrlEncoded
    @POST("logistics.php?axn=login")
    Call<Customer_Me_Success> Login(
            @FieldMap Map<String, String> params
    );

    @FormUrlEncoded
    @POST("logistics.php?axn=login")
    Call<Customer_Me_Success> LoginJSON(
            @Field("data") String body
    );

    @FormUrlEncoded
    @POST("logistics.php?axn=addnewcustomer")
    Call<ResponseBody> AddcompanyDetails(
            @FieldMap Map<String, String> params
    );

    @FormUrlEncoded
    @POST("logistics.php?axn=customerprofiling")
    Call<Name_Ofthe_Company_Success> SelectNameOftheCompany(
            @Field("Emp_ID") String email
    );

    @POST("logistics.php?axn=customertype")
    Call<Add_New_Customer_Success> Addnewcustomerspinner(

    );


    @POST("logistics.php?axn=EnquiryME")
    Call<Enquiry_Me_Success> Enquiry_Me(

    );

    @FormUrlEncoded
    @POST("logistics.php?axn=AddCostomerProfile")
    Call<ResponseBody> AddProfile(
            @FieldMap Map<String, String> params
    );

    @FormUrlEncoded
    @POST("logistics.php?axn=ChangePassword")
    Call<ResponseBody> PasswordChange(
            @FieldMap Map<String, String> params
    );

    //apiService JSON ARRAy
    //https://stackoverflow.com/questions/21398598/how-to-post-raw-whole-json-in-the-body-of-a-retrofit-request
    @Multipart
    @POST("logistics.php?axn=UpdateProfile")
    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name, @Part("Username") String username,@Part("Emp_ID") String EMpId,@Part("data") String body);

    @Headers({
            "Content-type:application/x-www-form-urlencoded",
            "Accept:application/json"
    })
    @FormUrlEncoded
    @POST("logistics.php?axn=RatefinderResult")
    Call<ResponseBody> Ratefinder(
            @Field("data") String body
    );



}
