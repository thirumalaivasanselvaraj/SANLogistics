package com.saneforce.logistics.Fragment_Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.API_interface.ServerResponse;
import com.saneforce.logistics.Activity_Class.Enquiry_Activity;
import com.saneforce.logistics.Activity_Class.Login_Activity;
import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.Activity_Class.Profile_Activity;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_MyAccount extends Fragment implements View.OnClickListener {
    View v;
    Common_Class common_class;
    Shared_Common_Pref shared_common_pref;
    Button customerprofilebutton, enquirybutton;
    String profileiconnn = null;
    List<CustomerMe> CustomerMeList;
    Gson gson;
    public CircularImageView profileicon;
    EditText settingusername, settingemail, settingmobilenumber, dob, settingaddress;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    String mediaPath = null;
    private static final int PICK_FROM_GALLERY = 1;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    TextView Update;
    ApiInterface apiService;
    MultipartBody.Part fileToUpload;
    RequestBody filename;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_setting_, container, false);

        Declaration_View(v);
        return v;
    }

    private void Declaration_View(View v) {
        //swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        shared_common_pref = new Shared_Common_Pref(getActivity());
        apiService = ApiClient.getClient().create(ApiInterface.class);

        common_class = new Common_Class(getActivity());
        common_class.hidekeyboard(getActivity());
        settingusername = (EditText) v.findViewById(R.id.settingusername);
        settingemail = (EditText) v.findViewById(R.id.settingemail);
        settingmobilenumber = (EditText) v.findViewById(R.id.settingmobilenumber);
        dob = (EditText) v.findViewById(R.id.dob);
        settingaddress = (EditText) v.findViewById(R.id.settingaddress);
        Update = (TextView) v.findViewById(R.id.Update);
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setFocusableInTouchMode(false);
        gson = new Gson();
        profileicon = (CircularImageView) v.findViewById(R.id.profile_iconmyaccount);
        Update.setOnClickListener(this);
        CustomerMeList = gson.fromJson(shared_common_pref.getvalue(Shared_Common_Pref.cards_pref), type);
        if (!(common_class.NAmecheck(CustomerMeList.get(0).getFilePath()))) {
            System.out.println("ProfileImage" + CustomerMeList.get(0).getEmpEmail());
            profileiconnn = CustomerMeList.get(0).getFilePath();
            settingusername.setText(CustomerMeList.get(0).getEmpName());
            settingemail.setText(CustomerMeList.get(0).getEmpEmail());
            settingmobilenumber.setText(CustomerMeList.get(0).getEmpMobile());
            dob.setText((CustomerMeList.get(0).getEmpDOB().getDate()));
            settingaddress.setText(CustomerMeList.get(0).getEmpContactAddOne());

            if (profileiconnn != null && !profileiconnn.isEmpty()) {
                System.out.println("profile_icon" + profileiconnn);

                Picasso
                        .with(getActivity())
                        .load(profileiconnn)
                        .placeholder(R.mipmap.logo) // can also be a drawable
                        .into(profileicon);
            } else {
                System.out.println("profile_icon" + profileiconnn);
                profileicon.setImageResource(R.mipmap.logo);
                profileicon.setBorderColor(getResources().getColor(R.color.leuf_blue));
                profileicon.setBorderWidth(1);
            }
        }

        profileicon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.profile_iconmyaccount:
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PICK_FROM_GALLERY);
                } else {
                    showPictureDialog();
                }

                break;
            case R.id.Update:
                System.out.println("MEDIAPATH" + mediaPath);
                if (mediaPath == null) {
                    RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");

                    fileToUpload = MultipartBody.Part.createFormData("attachment", "", attachmentEmpty);
                } else {
                    File file = new File(mediaPath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                    filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                }


                // Parsing any Media type file


                JSONObject paramObject = new JSONObject();

                JSONObject jj = new JSONObject();
                try {
                    paramObject.put("Username", settingusername.getText().toString());
                    paramObject.put("Email", settingemail.getText().toString());
                    paramObject.put("Mobile", settingmobilenumber.getText().toString());
                    paramObject.put("DOB", dob.getText().toString());
                    paramObject.put("Address", settingaddress.getText().toString());
                    JSONArray j = new JSONArray();
                    j.put(paramObject);

                    jj.put("DATA", j);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (vali()) {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<ServerResponse> Callto = apiService.uploadFile(fileToUpload, filename, settingusername.getText().toString(), CustomerMeList.get(0).getEmpID(), paramObject.toString());
                    Callto.enqueue(UpdateProfile);
                }

                break;
        }
    }


    public Callback<ServerResponse> UpdateProfile = new Callback<ServerResponse>() {
        @Override
        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

            System.out.println("Update checkUser is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                JSONObject jsonObject = null;
                String jsonData = null;
                ServerResponse serverResponse = response.body();
                if (serverResponse.getSuccess()) {
                    progressBar.setVisibility(View.GONE);
                    System.out.println("MESSAGE_SERVER_EMP_CODE" + CustomerMeList.get(0).getEmpID());
                    System.out.println("MESSAGE_SERVER" + serverResponse.getMessage());


                    common_class.CustomerMe(getActivity());
                    common_class.showToastMSG(getActivity(), serverResponse.getMessage(), 1);

                } else {
                    Toast.makeText(getActivity(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Log.d("TAG", "jsonObject: " + jsonObject);

            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    Toast.makeText(getActivity(), jObjError.getString("error_msg"), Toast.LENGTH_LONG).show();

                    System.out.println("this is responsebody error" + jObjError.getString("success"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "catchbody error " + e.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("catchbody error " + e.toString());
                }
            }
        }

        @Override
        public void onFailure(Call<ServerResponse> call, Throwable t) {

        }


    };


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Saneforce Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mediaPath = cursor.getString(columnIndex);
            // Set the Image in ImageView for Previewing the Media
            System.out.println("MEDIA_PATH" + mediaPath);
            profileicon.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            cursor.close();
            //android.graphics.Bitmap@6d7a797

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profileicon.setImageBitmap(thumbnail);
            mediaPath = String.valueOf(thumbnail);
            String path = saveImage(thumbnail);
            mediaPath = path;
            System.out.println("CAMERA_PATHH" + path);
            //android.graphics.Bitmap@72701b7
            //Uri tempUri = getImageUri(getApplicationContext(), photo);
            //  saveImage(thumbnail);
            //  Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    public boolean vali() {

        if (settingusername.getText().toString().equalsIgnoreCase("")) {
            settingusername.setError("Please enter the  UserName");
            settingusername.requestFocus();


            return false;
        }


        if (settingemail.getText().toString().equalsIgnoreCase("")) {
            settingemail.setError("Enter the  Email");
            settingemail.requestFocus();
            //TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordinput);

            //  loginpassword.setError("Please enter the Password");

            return false;
        }


        if (!(common_class.isValidEmailId(settingemail.getText().toString()))) {
            settingemail.setError("Enter the Email Valid Email");
            settingemail.requestFocus();
            //TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordinput);

            //  loginpassword.setError("Please enter the Password");

            return false;
        }

        if (dob.getText().toString().equalsIgnoreCase("")) {
            dob.setError("Enter the DOB");
            dob.requestFocus();
            //TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordinput);

            //  loginpassword.setError("Please enter the Password");

            return false;
        }
        if (settingaddress.getText().toString().equalsIgnoreCase("")) {
            settingmobilenumber.setError("Enter The Address");
            settingmobilenumber.requestFocus();
            //TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordinput);

            //  loginpassword.setError("Please enter the Password");

            return false;
        }
        return true;

    }
}




