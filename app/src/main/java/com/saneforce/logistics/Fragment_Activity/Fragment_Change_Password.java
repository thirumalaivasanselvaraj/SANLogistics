package com.saneforce.logistics.Fragment_Activity;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.Activity_Class.Add_Customer_Activity;
import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.Enquiry_Me.Enquiry_Me_Success;
import com.saneforce.logistics.R;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.xwray.passwordview.PasswordView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Change_Password extends Fragment implements View.OnClickListener {
    View v;
    Common_Class common_class;
    Shared_Common_Pref shared_common_pref;
    Button changepasswordsave;
    TextView changepasswordcancel;
    TextInputEditText changeoldpassword, changenewpassword, changerepassword;
    List<CustomerMe> CustomerMeList;
    Gson gson;
    ApiInterface apiService;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_changepassword, container, false);

        Declaration_View(v);
        return v;
    }

    private void Declaration_View(View v) {
        //swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        shared_common_pref = new Shared_Common_Pref(getActivity());
        common_class = new Common_Class(getActivity());
        gson = new Gson();
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        System.out.println("TYPETOKEN_LIST" + type);
        CustomerMeList = gson.fromJson(shared_common_pref.getvalue(Shared_Common_Pref.cards_pref), type);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setFocusableInTouchMode(false);
        changeoldpassword = (TextInputEditText) v.findViewById(R.id.changeoldpassword);
        changenewpassword = (TextInputEditText) v.findViewById(R.id.changenewpassword);
        changerepassword = (TextInputEditText) v.findViewById(R.id.changerepassword);
        changepasswordsave = (Button) v.findViewById(R.id.changepasswordsave);
        changepasswordcancel = (TextView) v.findViewById(R.id.changepasswordcancel);
        changepasswordsave.setOnClickListener(this);
        changepasswordcancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changepasswordsave:
                if (vali()) {
                    progressBar.setVisibility(View.VISIBLE);
                    apiService = ApiClient.getClient().create(ApiInterface.class);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Emp_ID", CustomerMeList.get(0).getEmpID());
                    map.put("Emp_Code", CustomerMeList.get(0).getEmpCode());
                    map.put("Oldpassword", changeoldpassword.getText().toString());
                    map.put("Newpassword", changenewpassword.getText().toString());

                    Call<ResponseBody> Callto = apiService.PasswordChange(map);
                    Callto.enqueue(ChangePassword);
                }

                // common_class.CommonIntentwithFinish(Profile_Activity.class);
                break;
            case R.id.changepasswordcancel:
                //common_class.CommonIntentwithFinish(Enquiry_Activity.class);
                break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    public boolean vali() {

        if (changeoldpassword.getText().toString().equalsIgnoreCase("")) {
            changeoldpassword.setError("Enter The Old Password");
            changeoldpassword.requestFocus();
            return false;
        }
        if (changenewpassword.getText().toString().equalsIgnoreCase("")) {
            changenewpassword.setError("Enter the New Password");
            changenewpassword.requestFocus();
            return false;
        }
        if (changerepassword.getText().toString().equalsIgnoreCase("")) {
            changerepassword.setError("Enter the Re Password");
            changerepassword.requestFocus();
            return false;
        }
        if (!(changerepassword.getText().toString().equals(changenewpassword.getText().toString()))) {
            changerepassword.setError("Password and Confirm Password Must be same");
            changerepassword.requestFocus();
            return false;
        }

        if (!((shared_common_pref.getvalue(Shared_Common_Pref.Password) != null) && (changeoldpassword.getText().toString().equals(shared_common_pref.getvalue(Shared_Common_Pref.Password))))) {
            changeoldpassword.setError("Check Your Old Password");
            changeoldpassword.requestFocus();
            return false;
        }
        return true;
    }

    public Callback<ResponseBody> ChangePassword = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            System.out.println("checkUser is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                JSONObject jsonObject = null;
                String jsonData = null;
                try {
                    jsonData = response.body().string();
                    jsonObject = new JSONObject(jsonData);
                    if (jsonObject.getString("success").equals("true")) {
                        progressBar.setVisibility(View.GONE);

                        common_class.showToastMSG(getActivity(), "Sucessfully Updated", 1);
                        shared_common_pref.save(Shared_Common_Pref.Password, changenewpassword.getText().toString());
                        common_class.CommonIntentwithNEwTask(MainActivity.class);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        common_class.showToastMSG(getActivity(), jsonObject.getString("msg"), 3);
                    }
                    Log.d("TAG", "jsonObject: " + jsonObject);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    Toast.makeText(getActivity(), jObjError.getString("error_msg"), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                    System.out.println("this is responsebody error" + jObjError.getString("success"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "catchbody error " + e.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("catchbody error " + e.toString());
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }


    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
}
