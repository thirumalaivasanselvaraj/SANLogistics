package com.saneforce.logistics.Fragment_Activity;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.Activity_Class.Add_Customer_Activity;
import com.saneforce.logistics.Activity_Class.Login_Activity;
import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Customer_Me.Customer_Me_Success;
import com.saneforce.logistics.Pojo_Class.Ratefinder_Save_previous;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Rate_finder_Fragment.CountryName_Fragment;
import com.saneforce.logistics.Rate_finder_Fragment.Departure_Fragment;
import com.saneforce.logistics.Rate_finder_Fragment.POD_Fragment;
import com.saneforce.logistics.Rate_finder_Fragment.POL_Fragment;
import com.saneforce.logistics.Rate_finder_Fragment.RatefilnderResponse;
import com.saneforce.logistics.Rate_finder_Fragment.Ratefinder_Recycler_view_Adapter;
import com.saneforce.logistics.Rate_finder_Fragment.ShippingLine_Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.saneforce.logistics.Activity_Class.MainActivity.tool_Rel;

public class Fragment_Rate_Finder extends Fragment implements View.OnClickListener {
    Common_Class common_class;
    Bundle bundle;
    Gson gson;
    ProgressBar progressBar;
    Shared_Common_Pref shared_common_pref;
    RelativeLayout countryselect, vehicleyear, fuelselectlayout, relMaker, relModel, pollayout, podlayout, departurelayout, shippinglayout, footer;
    TextView countyname, textviewpol, textpod, texteparture, textviewshipingline, cancelbutton, ratefindertitle;
    EditText addvehiclename, addvehiclecolor, addvehiclefueltank;
    ImageView back_arrow, back_arrowresult;
    View v;
    Ratefinder_Save_previous add_car_pojo;
    List<Ratefinder_Save_previous> add_car_list;
    String Strigcounteyname = null, POLString = null, PODString = null, DepartureString = null, ShipngLineString = null;
    public static int fuel_val = 1;
    Button checkrate;
    ApiInterface apiService;
    private static RecyclerView recyclerVieww;
    private RecyclerView.LayoutManager layoutManager;
    private static Ratefinder_Recycler_view_Adapter adapter;
    LinearLayout selectfield_lineralayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rate_finder, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Declaration_View(v);
        return v;
    }

    private void Declaration_View(View v) {
        common_class = new Common_Class(getActivity());
        gson = new Gson();
        shared_common_pref = new Shared_Common_Pref(getActivity());

        add_car_list = new ArrayList<>();
        countryselect = (RelativeLayout) v.findViewById(R.id.countryselect);
        pollayout = (RelativeLayout) v.findViewById(R.id.pollayout);
        podlayout = (RelativeLayout) v.findViewById(R.id.podlayout);
        departurelayout = (RelativeLayout) v.findViewById(R.id.departurelayout);
        shippinglayout = (RelativeLayout) v.findViewById(R.id.shippinglayout);
        footer = (RelativeLayout) v.findViewById(R.id.footer);
        selectfield_lineralayout = (LinearLayout) v.findViewById(R.id.selectfield_lineralayout);
        checkrate = (Button) v.findViewById(R.id.checkrate);
        tool_Rel.setVisibility(View.GONE);
        back_arrow = (ImageView) v.findViewById(R.id.back_arrow);

        back_arrowresult = (ImageView) v.findViewById(R.id.back_arrowresult);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        countyname = (TextView) v.findViewById(R.id.countyname);
        textviewpol = (TextView) v.findViewById(R.id.pol);
        ratefindertitle = (TextView) v.findViewById(R.id.ratefindertitle);
        ratefindertitle.setText("Schedule Finder");
        textpod = (TextView) v.findViewById(R.id.pod);
        texteparture = (TextView) v.findViewById(R.id.departure);
        textviewshipingline = (TextView) v.findViewById(R.id.shiplingline);
        cancelbutton = (TextView) v.findViewById(R.id.cancelbutton);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        back_arrow.setOnClickListener(this);
        pollayout.setOnClickListener(this);
        countryselect.setOnClickListener(this);
        podlayout.setOnClickListener(this);
        departurelayout.setOnClickListener(this);
        shippinglayout.setOnClickListener(this);
        cancelbutton.setOnClickListener(this);
        checkrate.setOnClickListener(this);

        back_arrowresult.setOnClickListener(this);
        recyclerVieww = (RecyclerView) v.findViewById(R.id.Raterecyclerview);
        recyclerVieww.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerVieww.setLayoutManager(layoutManager);
        recyclerVieww.setItemAnimator(new DefaultItemAnimator());

        if (getArguments() != null && getArguments().getString("ratefinder") != null) {
            Strigcounteyname = getArguments().getString("ratefinder");
            countyname.setText(Strigcounteyname);

        } else if (getArguments() != null && getArguments().getString("POL") != null) {
            POLString = getArguments().getString("POL");
            textviewpol.setText(POLString);
            System.out.println("POL_SELECT_FROM_ADAPTER" + POLString);
        } else if (getArguments() != null && getArguments().getString("POD") != null) {
            PODString = getArguments().getString("POD");
            textpod.setText(PODString);
            System.out.println("POD_SELECT_FROM_ADAPTER" + PODString);
        } else if (getArguments() != null && getArguments().getString("Departure") != null) {
            DepartureString = getArguments().getString("Departure");
            texteparture.setText(DepartureString);
        } else if (getArguments() != null && getArguments().getString("ShipingLine") != null) {
            ShipngLineString = getArguments().getString("ShipingLine");
            textviewshipingline.setText(ShipngLineString);
        }

        if (fuel_val == 1) {

            String add_car = shared_common_pref.getvalue(Shared_Common_Pref.Vehicle_list_pref);
            if (add_car != null && !add_car.equals("")) {
                add_car_list = gson.fromJson(add_car, new TypeToken<List<Ratefinder_Save_previous>>() {
                }.getType());

                System.out.println("ADDCAR_PREVIOUS_after" + add_car_list);
                if (add_car_list.size() > 0 && add_car_list != null) {
                    System.out.println("ADDCAR_PREVIOUS_setter" + add_car_list.get(0).getCountry());
                    if (Strigcounteyname == null) {
                        countyname.setText(add_car_list.get(0).getCountry());
                    }
                    if (POLString == null) {
                        textviewpol.setText(add_car_list.get(0).getPol());
                    }
                    if (PODString == null) {
                        textpod.setText(add_car_list.get(0).getPod());
                    }
                    if (DepartureString == null) {
                        texteparture.setText(add_car_list.get(0).getDeparture());
                    }
                    if (ShipngLineString == null) {
                        textviewshipingline.setText(add_car_list.get(0).getShipingline());
                    }
                   /* textpod.setText(add_car_list.get(0).getPod());
                    texteparture.setText(add_car_list.get(0).getDeparture());
                    textviewshipingline.setText(add_car_list.get(0).getDeparture());*/
                    System.out.println("ADDCAR_PREVIOUS_model_name" + add_car_list.get(0).getCountry() + ":" + add_car_list.get(0).getPol() + ",:" + add_car_list.get(0).getPod());


                }

            }
          /*  if (add_car_list.size() > 0 && add_car_list != null) {
                System.out.println("ADDCAR_PREVIOUS_setter" + add_car_list.size());
                countyname.setText(add_car_list.get(0).getCountry());
                textviewpol.setText(add_car_list.get(0).getPol());
                textpod.setText(add_car_list.get(0).getPod());
                texteparture.setText(add_car_list.get(0).getDeparture());
                textviewshipingline.setText(add_car_list.get(0).getDeparture());
                System.out.println("ADDCAR_PREVIOUS_model_name" + add_car_list.get(0).getCountry() + ":" + add_car_list.get(0).getPol() + ",:" + add_car_list.get(0).getPod());


            }*/


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow:
                tool_Rel.setVisibility(View.VISIBLE);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, new Home_Fragment_Activity()).commit();
                break;
            case R.id.back_arrowresult:

                ratefindertitle.setText("Schedule Finder");
                cancelbutton.setVisibility(View.VISIBLE);
                checkrate.setVisibility(View.VISIBLE);
                System.out.println("BACK_ARROW_IS_WORKING");
                back_arrow.setVisibility(View.VISIBLE);
                selectfield_lineralayout.setVisibility(View.VISIBLE);
                recyclerVieww.setVisibility(View.GONE);
                back_arrowresult.setVisibility(View.GONE);
                break;

            case R.id.cancelbutton:
                tool_Rel.setVisibility(View.VISIBLE);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, new Home_Fragment_Activity()).commit();

                break;

            case R.id.countryselect:
                Bundle data = new Bundle();
                data.putString("model", "");
                data.putString("selected_value", countyname.getText().toString());
                CountryName_Fragment countryname = new CountryName_Fragment();
                countryname.setArguments(data);
                textviewpol.setText("");
                textpod.setText("");
                texteparture.setText("");
                textviewshipingline.setText("");


                getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, countryname).addToBackStack(null).commit();

                PreviousData();
                break;

            case R.id.pollayout:
                if (!(countyname.getText().toString().equals("") || countyname.getText().toString() == null)) {
                    Bundle datad = new Bundle();
                    datad.putString("model", "");
                    datad.putString("selected_value", textviewpol.getText().toString());
                    datad.putString("country_name", countyname.getText().toString());
                    POL_Fragment POLBUNDLE = new POL_Fragment();
                    POLBUNDLE.setArguments(datad);

                    getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, POLBUNDLE).addToBackStack(null).commit();

                    textpod.setText("");
                    texteparture.setText("");
                    textviewshipingline.setText("");
                    PreviousData();
                } else {
                    common_class.showToastMSG(getActivity(), "Select Country Name", 2);
                }


                break;

            case R.id.podlayout:
                if (!(countyname.getText().toString().equals("") || countyname.getText().toString() == null)) {


                    if (!(textviewpol.getText().toString().equals("") || textviewpol.getText().toString() == null)) {
                        Bundle datad = new Bundle();
                        datad.putString("model", "");
                        datad.putString("selected_value", textpod.getText().toString());

                        datad.putString("Select_Poll", textviewpol.getText().toString());
                        POD_Fragment POLBUNDLE = new POD_Fragment();
                        POLBUNDLE.setArguments(datad);


                        getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, POLBUNDLE).addToBackStack(null).commit();
                        texteparture.setText("");
                        textviewshipingline.setText("");
                        PreviousData();

                    } else {
                        common_class.showToastMSG(getActivity(), "Select Poll", 2);
                    }


                } else {
                    common_class.showToastMSG(getActivity(), "Select Country Name", 2);
                }


                break;

            case R.id.departurelayout:

                if (!(countyname.getText().toString().equals("") || countyname.getText().toString() == null)) {


                    if (!(textviewpol.getText().toString().equals("") || textviewpol.getText().toString() == null)) {
                        Bundle datad = new Bundle();
                        datad.putString("model", "");
                        datad.putString("selected_value", texteparture.getText().toString());

                        datad.putString("Select_POD", textpod.getText().toString());
                        Departure_Fragment POLBUNDLE = new Departure_Fragment();
                        POLBUNDLE.setArguments(datad);


                        getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, POLBUNDLE).addToBackStack(null).commit();
                        textviewshipingline.setText("");
                        PreviousData();

                    } else {
                        common_class.showToastMSG(getActivity(), "Select Port", 2);
                    }


                } else {
                    common_class.showToastMSG(getActivity(), "Select Country Name", 2);
                }


                break;


            case R.id.shippinglayout:
                if (!(countyname.getText().toString().equals("") || countyname.getText().toString() == null)) {


                    if (!(textviewpol.getText().toString().equals("") || textviewpol.getText().toString() == null)) {
                        Bundle datad = new Bundle();
                        datad.putString("model", "");
                        datad.putString("selected_value", textviewshipingline.getText().toString());

                        datad.putString("Select_Departure", texteparture.getText().toString());
                        ShippingLine_Fragment POLBUNDLE = new ShippingLine_Fragment();
                        POLBUNDLE.setArguments(datad);


                        getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, POLBUNDLE).addToBackStack(null).commit();
                        PreviousData();

                    } else {
                        common_class.showToastMSG(getActivity(), "Select Port", 2);
                    }


                } else {
                    common_class.showToastMSG(getActivity(), "Select Country Name", 2);
                }

                break;
            case R.id.checkrate:

                if (vali()) {
                    progressBar.setVisibility(View.VISIBLE);

                    JSONObject paramObject = new JSONObject();
                    try {
                        paramObject.put("countryname", countyname.getText().toString());
                        paramObject.put("poll", textviewpol.getText().toString());
                        paramObject.put("pod", textpod.getText().toString());
                        paramObject.put("departure", texteparture.getText().toString());
                        paramObject.put("shipingline", textviewshipingline.getText().toString());
                        System.out.println("JSON Expections" + paramObject.toString());
                        Call<ResponseBody> Callto = apiService.Ratefinder(paramObject.toString());
                        Callto.enqueue(ratefinderresponse);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("JSON Expections" + paramObject.toString());

                    }
                }
                break;

        }
    }

    public void PreviousData() {

        add_car_list.clear();
        add_car_pojo = new Ratefinder_Save_previous();
        add_car_pojo.setCountry(countyname.getText().toString());
        add_car_pojo.setPol(textviewpol.getText().toString());
        add_car_pojo.setPod(textpod.getText().toString());
        add_car_pojo.setDeparture(texteparture.getText().toString());
        add_car_pojo.setShipingline(textviewshipingline.getText().toString());

        add_car_list.add(add_car_pojo);
        gson = new Gson();
        String add_car_pojo = gson.toJson(add_car_list);
        System.out.println("ADDCAR_PREVIOUS_TEXT" + add_car_pojo);
        shared_common_pref.save(Shared_Common_Pref.Vehicle_list_pref, add_car_pojo);

    }

    public boolean vali() {

        if (countyname.getText().toString().equalsIgnoreCase("")) {
            common_class.showToastMSG(getActivity(), "Select Country Name", 2);

            return false;
        }


        if (textviewpol.getText().toString().equalsIgnoreCase("")) {

            //TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordinput);

            //  loginpassword.setError("Please enter the Password");
            common_class.showToastMSG(getActivity(), "Select Poll", 2);

            return false;
        }

        return true;

    }

    public Callback<ResponseBody> ratefinderresponse = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            System.out.println("checkUser is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                JSONObject jsonObject = null;
                String jsonData = null;
                try {
                    jsonData = response.body().string();
                    jsonObject = new JSONObject(jsonData);

                    System.out.println("WHETHER_TRUE_OR_FALSE" + jsonObject);
                    if (jsonObject.getString("success").equals("true")) {
                        progressBar.setVisibility(View.GONE);
                        ArrayList<RatefilnderResponse> PortPojoList = new ArrayList<RatefilnderResponse>();
                        ratefindertitle.setText("RESULT");
                        JSONArray jsonArray = jsonObject.getJSONArray("RateFInder_Result");
                        cancelbutton.setVisibility(View.GONE);
                        checkrate.setVisibility(View.GONE);
                        back_arrowresult.setVisibility(View.VISIBLE);
                        back_arrow.setVisibility(View.GONE);
                        System.out.println("JSONRESPONSE_RATE_FINDER" + jsonArray.toString());
                        RatefilnderResponse n = new RatefilnderResponse();

                        if (jsonArray != null && jsonArray.length() > 1) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                // String userName=jsonArray.getJSONObject(i).getString("LINE");
                                n.set1TS(jsonArray.getJSONObject(i).getString("LINE"));
                                n.setPOL(jsonArray.getJSONObject(i).getString("POL"));
                                n.setDEP(jsonArray.getJSONObject(i).getString("DEP"));
                                n.setMODE(jsonArray.getJSONObject(i).getString("MODE"));
                                n.set1TS(jsonArray.getJSONObject(i).getString("1TS"));
                                n.setSVC2(jsonArray.getJSONObject(i).getString("SVC1"));
                                n.set2TS(jsonArray.getJSONObject(i).getString("2TS"));
                                n.setSVC2(jsonArray.getJSONObject(i).getString("SVC2"));
                                n.setFINALPOD(jsonArray.getJSONObject(i).getString("FINAL_POD"));
                                n.setARR(jsonArray.getJSONObject(i).getString("ARR"));
                                n.setTOTTRANSIT(jsonArray.getJSONObject(i).getString("TOT_TRANSIT"));
                                n.setCountryCode(jsonArray.getJSONObject(i).getString("Country_Code"));
                                n.setCountryName(jsonArray.getJSONObject(i).getString("Country_Name"));
                                n.setREMARK(jsonArray.getJSONObject(i).getString("REMARK"));
                                PortPojoList.add(n);
                            }

                            adapter = new Ratefinder_Recycler_view_Adapter(PortPojoList);
                            recyclerVieww.setAdapter(adapter);
                            recyclerVieww.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            selectfield_lineralayout.setVisibility(View.GONE);

                        } else {
                            common_class.showToastMSG(getActivity(), "Nothing Is Found", 1);
                        }


                        //common_class.CommonIntentwithNEwTask(MainActivity.class);
                    } else {
                        ArrayList<RatefilnderResponse> PortPojoList;
                        Type port = new TypeToken<List<RatefilnderResponse>>() {
                        }.getType();
                        PortPojoList = gson.fromJson(shared_common_pref.getvalue(Shared_Common_Pref.Ratefnder), port);

                        /*ratefindertitle.setText("RESULT");
                        cancelbutton.setVisibility(View.GONE);
                        checkrate.setVisibility(View.GONE);
                        back_arrowresult.setVisibility(View.VISIBLE);
                        back_arrow.setVisibility(View.GONE);
                        adapter = new Ratefinder_Recycler_view_Adapter(PortPojoList);
                        recyclerVieww.setAdapter(adapter);
                        recyclerVieww.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        selectfield_lineralayout.setVisibility(View.GONE);
                        common_class.showToastMSG(getActivity(), "Under Progress", 4);*/
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
}