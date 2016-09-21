package com.evan.etcweb.main.pray;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.evan.etcweb.R;
import com.evan.etcweb.entities.PrayDetail;
import com.evan.etcweb.http.ApplicationController;
import com.evan.etcweb.http.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PrayFragment extends Fragment {

    static Context context;
    private PrayAdapter prayAdapter;
    private static List<PrayDetail> prayDetailList;
    private static int churchID;

    @BindView(R.id.pray_list)
    RecyclerView mRecyclerView;

    public PrayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prayDetailList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pray, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        prayAdapter = new PrayAdapter(prayDetailList, getActivity());
        mRecyclerView.setAdapter(prayAdapter);

        try {
            Intent intent = getActivity().getIntent();
            churchID = intent.getIntExtra("churchID", 0);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(prayDetailList.isEmpty())
             getPrayers(Helpers.PRAYERS_URL1 + churchID + Helpers.PRAYERS_URL2);

        return view;
    }

    private void getPrayers(String endpoint) {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.GET,
                endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getPrayersData(new JSONArray(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getContext(), "Oops! Something went wrong", Toast.LENGTH_LONG).show();
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

    private void getPrayersData(JSONArray jsonObject) {
        PrayDetail prayDetail;
        String name;
        String date;
        String message;
        int totalPrayers;

        try {
            for (int i = 0; i < jsonObject.length(); i++) {

                    JSONObject prayJSONObject = jsonObject.getJSONObject(i);
                    name = prayJSONObject.getString("name");
                    date = prayJSONObject.getString("date");
                    message = prayJSONObject.getString("message");
                    totalPrayers = prayJSONObject.getInt("numberOfPrayers");
                    prayDetail = new PrayDetail(name, date, message, totalPrayers);
                    prayDetailList.add(prayDetail);
                }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        prayAdapter.notifyDataSetChanged();
    }


}
