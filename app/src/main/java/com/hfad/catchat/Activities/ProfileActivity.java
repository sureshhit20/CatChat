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
import com.hfad.catchat.R;
import com.hfad.catchat.Retrofit.ApiClient;
import com.hfad.catchat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "message";
    TextView name_view,phone_view,place_view,disease_view,count_view;
    private String id, name, phone, place,disease,day, age,joining_date,buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Patient Profile");


        Intent intent = getIntent();
        id = intent.getStringExtra(EXTRA_ID);

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
        Call<User> call =  apiService.getContactById(id);
        Log.d("TAG30", String.valueOf(call.request()));

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                name_view.setText(user.getName());
                phone_view.setText(user.getPhone());
                place_view.setText(user.getPlace());
                count_view.setText(user.getCount());
                disease_view.setText(user.getDisease());

                name = user.getName();
                phone = user.getPhone();
                place = user.getPlace();
                age = user.getAge();
                disease = user.getDisease();
                day = user.getDay();


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
                intent.putExtra(EditActivity.EXTRA_ID1,id);
                intent.putExtra(EditActivity.EXTRA_NAME,name);
                intent.putExtra(EditActivity.EXTRA_PHONE,phone);
                intent.putExtra(EditActivity.EXTRA_PLACE,place);
                intent.putExtra(EditActivity.EXTRA_AGE,age);
                intent.putExtra(EditActivity.EXTRA_DISEASE,disease);
                intent.putExtra(EditActivity.EXTRA_DAY,day);
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

    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        Intent setIntent = new Intent(this,ContactsList.class);
        startActivity(setIntent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);


    }
}
