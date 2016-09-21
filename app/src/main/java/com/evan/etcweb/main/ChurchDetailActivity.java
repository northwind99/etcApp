package com.evan.etcweb.main;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evan.etcweb.MainActivity;
import com.evan.etcweb.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChurchDetailActivity extends Activity {

    public static boolean churchDetailBtn_clicked;
    static int churchID;

    @BindView(R.id.church_detail_church_name)
    TextView church_name;

    @BindView(R.id.church_detail_church_location)
    TextView church_location;

    @BindView(R.id.church_detail_phone)
    TextView church_phone;

    @BindView(R.id.church_detail_fax)
    TextView church_fax;

    @BindView(R.id.church_detail_website)
    TextView church_website;

    @BindView(R.id.church_detail_email)
    TextView church_email;

    @BindView(R.id.church_detail_select_btn)
    Button select_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_detail);
        ButterKnife.bind(this);
        initView();

    }

    private void initView(){

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                churchDetailBtn_clicked = true;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("churchID", churchID);
                intent.putExtra("churchName", church_name.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        Intent intent=getIntent();
        String churchName=intent.getStringExtra("churchName");
        String churchAddress=intent.getStringExtra("churchAddress");
        String churchCity=intent.getStringExtra("churchCity");
        String churchPhone=intent.getStringExtra("churchPhone");
        String churchFax=intent.getStringExtra("churchFax");
        String churchWebsite=intent.getStringExtra("churchWebsite");
        String churchEmail=intent.getStringExtra("churchEmail");
        churchID=intent.getIntExtra("churchID", 0);

        church_name.setText(churchName);
        church_location.setText(churchAddress + ", " + churchCity);
        church_phone.setText("Phone: " + churchPhone);
        church_fax.setText("Fax: " + churchFax);
        church_website.setText("Website: " + churchWebsite);
        church_email.setText("Email: " + churchEmail);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_cancel:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
