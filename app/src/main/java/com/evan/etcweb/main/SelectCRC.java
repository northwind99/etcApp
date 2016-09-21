package com.evan.etcweb.main;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.evan.etcweb.R;
import com.evan.etcweb.SectionsActivity;
import com.evan.etcweb.entities.Church;
import com.evan.etcweb.entities.Contact;
import com.evan.etcweb.entities.ServiceTime;
import com.evan.etcweb.http.ApplicationController;
import com.evan.etcweb.http.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCRC extends Activity {

    static String selection;
    public ArrayList<Church> churches = new ArrayList<>();
    public ArrayList<String> church_name_List = new ArrayList<>();
    public ArrayList<String> church_city_List = new ArrayList<>();

    public HashMap<String, String> church_name_city = new HashMap<>();
    public static HashMap<String, Double> church_name_lat = new HashMap<>();
    public static HashMap<String, Double> church_name_long = new HashMap<>();
    public static HashMap<String, String> church_address = new HashMap<>();
    public static HashMap<String, String> church_postalCode = new HashMap<>();
    public static HashMap<String, String> church_phone = new HashMap<>();
    public static HashMap<String, String> church_fax = new HashMap<>();
    public static HashMap<String, String> church_website = new HashMap<>();
    public static HashMap<String, String> church_email = new HashMap<>();
    public static HashMap<String, Integer> church_id = new HashMap<>();

    public static String church_name_selected;

    public static String churchName;
    public static String churchCity;

    @BindView(R.id.select_cancel)
    TextView select_cancel;

    @BindView(R.id.search_crc)
    AutoCompleteTextView search_crc;

    @BindView(R.id.church_name)
    TextView church_name;

    @BindView(R.id.church_location)
    TextView church_location;

    @BindView(R.id.church_info_btn)
    ImageView church_info_btn;

    @BindView(R.id.show_church)
    RelativeLayout show_church;

    @BindView(R.id.map_text)
    TextView map_text;

    @BindView(R.id.icon_find_crc)
    ImageView find_crc_on_map;

    @BindView(R.id.search_by_location_id)
    TextView search_by_location_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_crc);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){


        getChurches(Helpers.CHURCHES_URL);

        select_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, church_name_List);
        search_crc.setAdapter(adapter);

        search_crc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selection = (String)parent.getItemAtPosition(position);
                church_name.setText(selection);
                church_name_selected = church_name.getText().toString();
                church_location.setText(church_name_city.get(selection));
                church_info_btn.setImageResource(R.drawable.ic_info_outline_black_24dp);

                map_text.setText("Having trouble find a CRC?");
                find_crc_on_map.setImageResource(R.drawable.ic_room_black_24dp);
                search_by_location_text.setText("Search by Location");
                search_by_location_text.setTextColor(getResources().getColor(R.color.light_green1));

                churchName = church_name.getText().toString();
                churchCity = church_location.getText().toString();
            }
        });

        show_church.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!church_name.getText().toString().isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), ChurchDetailActivity.class);
                    intent.putExtra("churchName", churchName);
                    intent.putExtra("churchAddress", church_address.get(selection));
                    intent.putExtra("churchCity", churchCity);
                    intent.putExtra("churchPhone", church_phone.get(selection));
                    intent.putExtra("churchFax", church_fax.get(selection));
                    intent.putExtra("churchWebsite", church_website.get(selection));
                    intent.putExtra("churchEmail", church_email.get(selection));
                    intent.putExtra("churchID", church_id.get(selection));
                    startActivity(intent);
                }
            }
        });


        search_by_location_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChurchMapActivity.class);
                intent.putExtra("churchName", churchName);
                intent.putExtra("churchAddress", church_address.get(selection));
                intent.putExtra("churchCity", churchCity);
                intent.putExtra("churchPhone", church_phone.get(selection));
                intent.putExtra("churchFax", church_fax.get(selection));
                intent.putExtra("churchWebsite", church_website.get(selection));
                intent.putExtra("churchEmail", church_email.get(selection));
                intent.putExtra("churchID", church_id.get(selection));
                startActivity(intent);
            }
        });


    }

    public void getChurches(String endpoint) {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.GET,
                endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getChurchData(new JSONArray(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };
        ApplicationController.getInstance().addToRequestQueue(jsonObjRequest);
    }

    public void getChurchData(JSONArray jsonObject) {
        Church church;
        int id;
        String name;
        String description;
        String address1;
        String address2;
        String city;
        String postalCode;
        String province;
        Double latitude;
        Double longitude;
        String phone;
        String fax;
        String website;
        ArrayList<String> serviceTime = new ArrayList<>();

        Contact contact;

        int contact_id;
        String contact_name;
        String contact_title;
        String contact_phone;
        String contact_email;
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            for (int i = 0; i < jsonObject.length(); i++) {

                JSONObject churchJSONObject = jsonObject.getJSONObject(i);
                id = churchJSONObject.getInt("id");
                name = churchJSONObject.getString("name");
                description = churchJSONObject.getString("description");
                address1 = churchJSONObject.getString("address1");
                address2 = churchJSONObject.getString("address2");
                city = churchJSONObject.getString("city");
                postalCode = churchJSONObject.getString("postalCode");
                province = churchJSONObject.getString("province");
                latitude = churchJSONObject.getDouble("latitude");
                longitude = churchJSONObject.getDouble("longitude");
                phone = churchJSONObject.getString("phone");
                fax = churchJSONObject.getString("fax");
                website = churchJSONObject.getString("website");
                JSONArray serviceTimeArray = churchJSONObject.getJSONArray("serviceTimes");
                for (int k = 0; k < serviceTimeArray.length(); k++){
                    serviceTime.add(serviceTimeArray.get(k).toString());
                }
                JSONArray contactsArray = churchJSONObject.getJSONArray("contacts");
                for (int j = 0; j < contactsArray.length(); j++){
                    contact_id = contactsArray.getJSONObject(j).getInt("id");
                    contact_name = contactsArray.getJSONObject(j).getString("name");
                    contact_title = contactsArray.getJSONObject(j).getString("title");
                    contact_phone = contactsArray.getJSONObject(j).getString("phone");
                    contact_email = contactsArray.getJSONObject(j).getString("email");
                    contact = new Contact(contact_id, contact_name,contact_title, contact_phone, contact_email);
                    contacts.add(contact);
                }
                Log.v("contacts", contacts.get(1).getName());
                church = new Church(id, name, description, address1, address2, city, postalCode,province, latitude, longitude, phone, fax, website, serviceTime, contacts);
                churches.add(church);
                Log.v("wtf", churches.toString());
                church_name_List.add(churches.get(i).getName());
                church_city_List.add(churches.get(i).getCity() + ", " + churches.get(i).getProvince());

                church_name_city.put(churches.get(i).getName(), churches.get(i).getCity() + ", " + churches.get(i).getProvince() );
                church_name_lat.put(churches.get(i).getName(), churches.get(i).getLatitude());
                church_name_long.put(churches.get(i).getName(), churches.get(i).getLongitude());

                church_address.put(churches.get(i).getName(), churches.get(i).getAddress1());
                church_postalCode.put(churches.get(i).getName(), churches.get(i).getPostalCode());
                church_phone.put(churches.get(i).getName(), churches.get(i).getPhone());
                church_fax.put(churches.get(i).getName(), churches.get(i).getFax());
                church_website.put(churches.get(i).getName(), churches.get(i).getWebsite());
                church_id.put(churches.get(i).getName(), churches.get(i).getId());

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("church_name", church_name_List.toString());
    }

}
