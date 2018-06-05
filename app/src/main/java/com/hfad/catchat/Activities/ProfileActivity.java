package com.hfad.catchat.Activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.catchat.Model.User;
import com.hfad.catchat.Model.UserExtra;
import com.hfad.catchat.R;
import com.hfad.catchat.Retrofit.ApiClient;
import com.hfad.catchat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "message";
    TextView name_view,phone_view,place_view,disease_view,count_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Patient Profile");


        Intent intent = getIntent();
        String intent_id = intent.getStringExtra(EXTRA_ID);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Obtaining details..");
        progressDialog.show();

        name_view = (TextView) findViewById(R.id.profile_name);
        phone_view = (TextView) findViewById(R.id.profile_phone);
        place_view = (TextView) findViewById(R.id.profile_place);
        disease_view = (TextView) findViewById(R.id.profile_disease);
        count_view = (TextView) findViewById(R.id.profile_count);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserExtra> call =  apiService.getContactById(intent_id);
        Log.d("TAG30", String.valueOf(call.request()));

        call.enqueue(new Callback<UserExtra>() {
            @Override
            public void onResponse(Call<UserExtra> call, Response<UserExtra> response) {
                UserExtra userExtra = response.body();
                name_view.setText(userExtra.getName());
                phone_view.setText(userExtra.getPhone());
                place_view.setText(userExtra.getPlace());
                count_view.setText(userExtra.getCount());
                disease_view.setText(userExtra.getDisease());

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<UserExtra> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("TAG31",t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
}
