package com.saneforce.logistics.Activity_Class;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.CustomerCategory;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.Customertype;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Common_Class.clicl_listner_country;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.Enquiry_Me.Commodity;
import com.saneforce.logistics.Enquiry_Me.ContainerType;
import com.saneforce.logistics.Enquiry_Me.CountryName;
import com.saneforce.logistics.Enquiry_Me.Enquiry_Me_Success;
import com.saneforce.logistics.Enquiry_Me.Incoterm;
import com.saneforce.logistics.Enquiry_Me.PackageType;
import com.saneforce.logistics.Enquiry_Me.PortName;
import com.saneforce.logistics.Enquiry_Me.Priority;
import com.saneforce.logistics.Fragment_Activity.Fragment_Change_Password;
import com.saneforce.logistics.Fragment_Activity.Fragment_MyAccount;
import com.saneforce.logistics.Fragment_Activity.Fragment_Notifications;
import com.saneforce.logistics.Fragment_Activity.Fragment_Rate_Finder;
import com.saneforce.logistics.Fragment_Activity.Home_Fragment_Activity;
import com.saneforce.logistics.Fragment_Activity.Settings_Fragment;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Rate_finder_Fragment.Departure_Fragment;
import com.saneforce.logistics.Rate_finder_Fragment.POD_Fragment;
import com.saneforce.logistics.Rate_finder_Fragment.RatefilnderResponse;
import com.saneforce.logistics.Rate_finder_Fragment.ShippingLine_Fragment;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.UiModeManager.MODE_NIGHT_NO;
import static android.app.UiModeManager.MODE_NIGHT_YES;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, clicl_listner_country {
    FrameLayout Myaccount, homepage, changepassword, notifications, setting;
    TextView mTitle;
    NavigationView navigationView;
    Shared_Common_Pref sharedCommonPref;
    Common_Class common_class;
    ImageView logout;
    public static RelativeLayout tool_Rel;
    CollapsingToolbarLayout collapsingToolbarLayout;
    boolean doubleBackToExitPressedOnce = false;
    public static CircularImageView profileicon;
    String profileiconnn = null;
    List<CustomerMe> CustomerMeList;
    Gson gson;
    ApiInterface apiService;
    List<Commodity> Commodity;
    List<Priority> Priority;
    List<Incoterm> Incoterm;
    List<CountryName> CountryNameList;
    List<PortName> PortNameList;
    List<PackageType> PackagingTypePojoList;
    List<ContainerType> ContainerTypePojoList;
    List<RatefilnderResponse> RatefinderPojoList;
    TextView profilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        common_class = new Common_Class(MainActivity.this);
        tool_Rel = (RelativeLayout) findViewById(R.id.tool_Rel);
        logout = (ImageView) findViewById(R.id.logoutmain);
        homepage = (FrameLayout) findViewById(R.id.homepage);
        setting = (FrameLayout) findViewById(R.id.setting);
        changepassword = (FrameLayout) findViewById(R.id.changepassword);
        notifications = (FrameLayout) findViewById(R.id.notifications);
        Myaccount = (FrameLayout) findViewById(R.id.Myaccount);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        tool_Rel = (RelativeLayout) findViewById(R.id.tool_Rel);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("Animated Toolbar");
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.logout).setVisible(true);
        getFragmentManager().beginTransaction().replace(R.id.homepage, new Home_Fragment_Activity()).commit();
        homepage.setVisibility(View.VISIBLE);
        logout.setOnClickListener(this);
        mTitle.setText("Logistics");
        View header = navigationView.getHeaderView(0);
        profileicon = (CircularImageView) header.findViewById(R.id.profile_icon);
        profilename = (TextView) header.findViewById(R.id.profilename);
        sharedCommonPref = new Shared_Common_Pref(MainActivity.this);
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        gson = new Gson();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        System.out.println("TYPETOKEN_LIST" + type);
        CustomerMeList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.cards_pref), type);
        profilename.setText(CustomerMeList.get(0).getEmpName());
        if (!(common_class.NAmecheck(CustomerMeList.get(0).getFilePath()))) {
            System.out.println("ProfileImage" + CustomerMeList.get(0).getFilePath());
            profileiconnn = CustomerMeList.get(0).getFilePath();
            if (profileiconnn != null && !profileiconnn.isEmpty()) {
                System.out.println("profile_icon" + profileiconnn);

                Picasso
                        .with(MainActivity.this)
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

    }


    @Override
    public void onBackPressed() {
        System.out.println("onBackpressed" + doubleBackToExitPressedOnce);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce) {
            finish();
            super.onBackPressed();
        } else {
            doubleBackToExitPressedOnce = true;
            Common_Class common = new Common_Class(getApplicationContext());
            common.commonSnackBar("Press BACK again to Exit", this.findViewById(R.id.homepage));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.home:
                mTitle.setText("Home");
                getFragmentManager().beginTransaction().replace(R.id.homepage, new Home_Fragment_Activity()).commit();
                homepage.setVisibility(View.VISIBLE);
                tool_Rel.setVisibility(View.VISIBLE);
                changepassword.setVisibility(View.GONE);
                Myaccount.setVisibility(View.GONE);
                notifications.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                break;
            case R.id.myaccount:
                mTitle.setText("My Account");
                mTitle.setTypeface(common_class.setFontStyle("light"));
                if (!(common_class.isNetworkAvailable(getApplicationContext()))) {
                    Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                }
                Myaccount.setVisibility(View.VISIBLE);
                changepassword.setVisibility(View.GONE);
                homepage.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                notifications.setVisibility(View.GONE);


                //getFragmentManager().beginTransaction().replace(R.id.homepage, new Customer_List()).commit();

                getFragmentManager().beginTransaction().replace(R.id.Myaccount, new Fragment_MyAccount()).commit();


                break;
            case R.id.changepassword:

                tool_Rel.setVisibility(View.VISIBLE);
                mTitle.setText("Change Password");
                mTitle.setTypeface(common_class.setFontStyle("light"));
                if (!(common_class.isNetworkAvailable(getApplicationContext()))) {
                    Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                }
                changepassword.setVisibility(View.VISIBLE);
                Myaccount.setVisibility(View.GONE);
                homepage.setVisibility(View.GONE);
                notifications.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.changepassword, new Fragment_Change_Password()).commit();
                break;
            case R.id.notifications:
                mTitle.setTypeface(common_class.setFontStyle("light"));
                if (!(common_class.isNetworkAvailable(getApplicationContext()))) {
                    Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                }
                changepassword.setVisibility(View.GONE);
                homepage.setVisibility(View.GONE);
                notifications.setVisibility(View.VISIBLE);
                setting.setVisibility(View.GONE);
                Myaccount.setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.notifications, new Fragment_Notifications()).commit();
                ;
                mTitle.setText("Notifications");
                break;
            case R.id.logout:
                common_class.logoutdialog(MainActivity.this);
                break;
            case R.id.setting:
                mTitle.setTypeface(common_class.setFontStyle("light"));
                if (!(common_class.isNetworkAvailable(getApplicationContext()))) {
                    Toast.makeText(getApplicationContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                }
                changepassword.setVisibility(View.GONE);
                homepage.setVisibility(View.GONE);
                setting.setVisibility(View.VISIBLE);
                notifications.setVisibility(View.GONE);
                Myaccount.setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.setting, new Settings_Fragment()).commit();
                ;
                mTitle.setText("Settings");
                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.logoutmain:
                common_class.logoutdialog(MainActivity.this);
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Call<Enquiry_Me_Success> Callto = apiService.Enquiry_Me();
        Callto.enqueue(GetEnquiry_Me);

    }

    public Callback<Enquiry_Me_Success> GetEnquiry_Me = new Callback<Enquiry_Me_Success>() {
        @Override
        public void onResponse(Call<Enquiry_Me_Success> call, Response<Enquiry_Me_Success> response) {

            System.out.println("GetEnquiry_Me:" + response.isSuccessful());
            if (response.isSuccessful()) {
                Commodity = response.body().getCommodity();
                Incoterm = response.body().getIncoterms();
                Priority = response.body().getPriority();
                CountryNameList = response.body().getCountryName();
                PortNameList = response.body().getPortName();
                PackagingTypePojoList = response.body().getPackageTypes();
                ContainerTypePojoList = response.body().getContainerTypes();
                RatefinderPojoList = response.body().getRatefilnderResponse();
                System.out.println("GetEnquiry_Me_Commodity:" + Commodity.toString());

                String PriorityString = gson.toJson(Priority);
                sharedCommonPref.save(Shared_Common_Pref.Priority, PriorityString);
                String IncotermString = gson.toJson(Incoterm);
                sharedCommonPref.save(Shared_Common_Pref.Incoterm, IncotermString);
                String CommodityString = gson.toJson(Commodity);
                sharedCommonPref.save(Shared_Common_Pref.Commidity, CommodityString);
                String Countryname = gson.toJson(CountryNameList);
                sharedCommonPref.save(Shared_Common_Pref.CountryShared, Countryname);

                String Portname = gson.toJson(PortNameList);
                sharedCommonPref.save(Shared_Common_Pref.PortShared, Portname);

                String PKT = gson.toJson(PackagingTypePojoList);
                sharedCommonPref.save(Shared_Common_Pref.PackagingType, PKT);
                String CT = gson.toJson(ContainerTypePojoList);
                sharedCommonPref.save(Shared_Common_Pref.ContainerType, CT);
                String RF = gson.toJson(RatefinderPojoList);
                sharedCommonPref.save(Shared_Common_Pref.Ratefnder, RF);

                // System.out.println("CUSTOMERSIZE" + CT.get(0).getCusTypeName());
            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    Toast.makeText(getApplicationContext(), jObjError.getString("error_msg"), Toast.LENGTH_LONG).show();


                    System.out.println("this is responsebody error" + jObjError.getString("success"));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "catchbody error " + e.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("catchbody error " + e.toString());
                }
            }
        }

        @Override
        public void onFailure(Call<Enquiry_Me_Success> call, Throwable t) {

        }


    };

    @Override
    protected void onStart() {
        super.onStart();
        sharedCommonPref = new Shared_Common_Pref(MainActivity.this);
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        gson = new Gson();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        System.out.println("TYPETOKEN_LIST" + type);
        CustomerMeList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.cards_pref), type);
        profilename.setText(CustomerMeList.get(0).getEmpName());
        if (!(common_class.NAmecheck(CustomerMeList.get(0).getFilePath()))) {
            System.out.println("ProfileImage" + CustomerMeList.get(0).getFilePath());
            profileiconnn = CustomerMeList.get(0).getFilePath();
            if (profileiconnn != null && !profileiconnn.isEmpty()) {
                System.out.println("profile_icon" + profileiconnn);

                Picasso
                        .with(MainActivity.this)
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


    }

    @Override
    public void onClick(View view, int position, String name, String fragment_name, List<String> list) {

        if (name.equals("POL") && fragment_name.equals("POL") && list.size() > 0) {
            System.out.println("fuel_type_main" + list.get(position));
            Bundle bundle = new Bundle();
            bundle.putString("POL", list.get(position));

            Fragment_Rate_Finder add_car = new Fragment_Rate_Finder();
            add_car.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.homepage, add_car).commit();
        } else if (name.equals("POD") && fragment_name.equals("POD") && list.size() > 0) {
            System.out.println("fuel_type_main" + list.get(position));
            Bundle bundle = new Bundle();
            bundle.putString("POD", list.get(position));

            Fragment_Rate_Finder add_car = new Fragment_Rate_Finder();
            add_car.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.homepage, add_car).commit();
        } else if (name.equals("Departure") && fragment_name.equals("Departure") && list.size() > 0) {
            System.out.println("fuel_type_main" + list.get(position));
            Bundle bundle = new Bundle();
            bundle.putString("Departure", list.get(position));

            Fragment_Rate_Finder add_car = new Fragment_Rate_Finder();
            add_car.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.homepage, add_car).commit();
        } else if (name.equals("Shiping_Line") && fragment_name.equals("Shiping_Line") && list.size() > 0) {
            System.out.println("fuel_type_main" + list.get(position));
            Bundle bundle = new Bundle();
            bundle.putString("ShipingLine", list.get(position));
            Fragment_Rate_Finder add_car = new Fragment_Rate_Finder();
            add_car.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.homepage, add_car).commit();
        }
    }

    @Override
    public void onflagClick(View view, int position, String name, String fragment_name, List<CountryName> list) {
        if (name.equals("ratefinder") && fragment_name.equals("ratefinder") && list.size() > 0) {
            System.out.println("fuel_type_main" + list.get(position));
            Bundle bundle = new Bundle();
            bundle.putString("ratefinder", list.get(position).getCountryName());

            Fragment_Rate_Finder add_car = new Fragment_Rate_Finder();
            add_car.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.homepage, add_car).commit();
        }
    }
}
