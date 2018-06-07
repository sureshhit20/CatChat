package com.hfad.catchat.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hfad.catchat.Model.Result;
import com.hfad.catchat.Model.User;
import com.hfad.catchat.R;
import com.hfad.catchat.Retrofit.ApiClient;
import com.hfad.catchat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText pat1_name, pat1_phone, pat1_place, pat1_age, pat1_disease ;
    private TextInputLayout inputLayoutFullName1, inputLayoutPhone1, inputLayoutPlace1,inputLayoutAge1,inputLayoutDisease1;
    private Button signupBtn1;
    public static final String EXTRA_ID1 = "ids", EXTRA_NAME = "name", EXTRA_PLACE = "place", EXTRA_PHONE = "phone",
                                EXTRA_AGE = "age",EXTRA_DISEASE = "disease", EXTRA_DAY ="day";

    String day,final_id;
    boolean checked = false;
    RadioButton b_sun, b_mon, b_tues, b_wed, b_thurs,b_fri,b_sat;
    private String name,place,disease,dr,phone,age;
    RadioGroup radio_group1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Patient details");
        initializeWidgets();
    }


    public void initializeWidgets() {
        inputLayoutAge1 = (TextInputLayout) findViewById(R.id.inputLayoutAge);
        inputLayoutDisease1 = (TextInputLayout) findViewById(R.id.inputLayoutDisease);
        inputLayoutFullName1 = (TextInputLayout) findViewById(R.id.inputLayoutFullName);
        inputLayoutPhone1 = (TextInputLayout) findViewById(R.id.inputLayoutPhone);
        inputLayoutPlace1 = (TextInputLayout) findViewById(R.id.inputLayoutPlace);
        //layout.findViewById(R.id.input`)

        pat1_age = (EditText) findViewById(R.id.pat_age);
        pat1_disease = (EditText) findViewById(R.id.pat_disease);
        pat1_name = (EditText) findViewById(R.id.pat_name);
        pat1_phone = (EditText) findViewById(R.id.pat_phone);
        pat1_place = (EditText) findViewById(R.id.pat_place);

        signupBtn1 = (Button) findViewById(R.id.signUpBtn);
        radio_group1 = (RadioGroup) findViewById(R.id.radio_group);


        Intent intent = getIntent();
        final_id =intent.getStringExtra(EXTRA_ID1);
        pat1_name.setText(intent.getStringExtra(EXTRA_NAME));
        pat1_phone.setText(intent.getStringExtra(EXTRA_PHONE));
        pat1_place.setText(intent.getStringExtra(EXTRA_PLACE));
        pat1_age.setText(intent.getStringExtra(EXTRA_AGE));
        pat1_disease.setText(intent.getStringExtra(EXTRA_DISEASE));
        day = intent.getStringExtra(EXTRA_DAY);

        switch (day){
            case "Sunday":
                radio_group1.check(R.id.radio_sun);
                checked = true;
                break;
            case "Monday":
                radio_group1.check(R.id.radio_mon);
                checked = true;
                break;
            case "Tuesday":
                radio_group1.check(R.id.radio_tues);
                checked = true;
                break;
            case "Wednesday":
                radio_group1.check(R.id.radio_wed);
                checked = true;
                break;
            case "Thursday":
                radio_group1.check(R.id.radio_thurs);
                checked = true;
                break;
            case "Friday":
                radio_group1.check(R.id.radio_fri);
                checked = true;
                break;
            case "Saturday":
                radio_group1.check(R.id.radio_sat);
                checked = true;
                break;
        };

//        radio_group1.setOnCheckedChangeListener();

        findViewById(R.id.radio_sun).setOnClickListener(this);
        findViewById(R.id.radio_mon).setOnClickListener(this);
        findViewById(R.id.radio_tues).setOnClickListener(this);
        findViewById(R.id.radio_wed).setOnClickListener(this);
        findViewById(R.id.radio_thurs).setOnClickListener(this);
        findViewById(R.id.radio_fri).setOnClickListener(this);
        findViewById(R.id.radio_sat).setOnClickListener(this);


        signupBtn1.setOnClickListener(this);

    }

    private void setRadio(String s) {
        checked = true;
        day = s;
        Log.d("TAG1", s);
        Log.d("TAG2", String.valueOf(checked));
    }


    private void signup(){

        boolean isValid = true;
        if(pat1_name.getText().toString().isEmpty()){
            inputLayoutFullName1.setError("Name is Mandatory");
            isValid = false;
        }
        else
        {
            inputLayoutFullName1.setErrorEnabled(false);
        };

        if (pat1_place.getText().toString().isEmpty()){
            inputLayoutPlace1.setError("Place is Mandatory");
            isValid = false;

        }

        else {
            inputLayoutPlace1.setErrorEnabled(false);
        };
        if (pat1_phone.getText().toString().isEmpty()){
            inputLayoutPhone1.setError("Please enter Phone number");
            isValid = false;

        }
        else
        {
            inputLayoutPhone1.setErrorEnabled(false);
        };
        if (pat1_disease.getText().toString().isEmpty()){
            inputLayoutDisease1.setError("Patient Disease needs to be Entered");
            isValid = false;

        }
        else
        {
            inputLayoutDisease1.setErrorEnabled(false);
        };
        if (pat1_age.getText().toString().isEmpty()){
            inputLayoutAge1.setError("Age is Required");
            isValid = false;

        }
        else {
            inputLayoutAge1.setErrorEnabled(false);
        };


        if (!checked){
            Toast.makeText(getApplicationContext(),"Please select a day",Toast.LENGTH_SHORT).show();
        };

        if (isValid & checked)
        {
            // Toast.makeText(getContext(),"Success!",Toast.LENGTH_SHORT).show();
            editInDB();
            Log.d("TAG3","done");
        };
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUpBtn:
                signup();
                break;
            case R.id.radio_sun:
                setRadio("Sunday");
                break;
            case R.id.radio_mon:
                setRadio("Monday");
                break;
            case R.id.radio_tues:
                setRadio("Tuesday");
                break;
            case R.id.radio_wed:
                setRadio("Wednesday");
                break;
            case R.id.radio_thurs:
                setRadio("Thursday");
                break;
            case R.id.radio_fri:
                setRadio("Friday");
                break;
            case R.id.radio_sat:
                setRadio("Saturday");
                break;
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
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_ID,final_id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_ID,final_id);
        return super.onOptionsItemSelected(item);

    }


    private void editInDB() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        name = pat1_name.getText().toString().trim();
        place = pat1_place.getText().toString().trim();
        phone = pat1_phone.getText().toString().trim();
        disease = pat1_disease.getText().toString().trim();
        age = pat1_age.getText().toString().trim();
        dr = "chari";

        User user = new User(name, phone, place, age, disease, day,dr,final_id);

        Call<Result> call = apiService.editContact(user.getName(),user.getPhone(),
                user.getPlace(),user.getAge(),user.getDisease(),user.getDay(),user.getDr(),user.getUid());
        Log.d("TAG42", String.valueOf(call.request()));
        Log.d("TAG43", String.valueOf(user.getDay()));
        Log.d("TAG44", String.valueOf(user.getUid()));
        Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //    if(!response.body().getError()){
//                    Fragment fragment1 = new PatientProfile();
//                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    ft.replace(R.id.content_frame, fragment1);
//                    ft.addToBackStack(null);
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    ft.commit();

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_ID,final_id);
                startActivity(intent);
                //   }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("TAG4",t.getMessage());

            }
        });
    }

}
