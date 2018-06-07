package com.hfad.catchat.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hfad.catchat.Activities.MainActivity;
import com.hfad.catchat.Model.Result;
import com.hfad.catchat.Model.User;
import com.hfad.catchat.R;
import com.hfad.catchat.Retrofit.ApiClient;
import com.hfad.catchat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DraftsFragment extends Fragment implements View.OnClickListener{

    private EditText pat1_name, pat1_phone, pat1_place, pat1_age, pat1_disease ;
    private TextInputLayout inputLayoutFullName1, inputLayoutPhone1, inputLayoutPlace1,inputLayoutAge1,inputLayoutDisease1;
    private Button signupBtn1;
    View layout;
    String day;boolean checked = false;
    private String name,place,disease,dr,phone,age;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Patient Details");
        layout = inflater.inflate(R.layout.fragment_drafts, container, false);

        initializeWidgets();
        return layout;

    }

    public void initializeWidgets() {
        inputLayoutAge1 = (TextInputLayout) layout.findViewById(R.id.inputLayoutAge);
        inputLayoutDisease1 = (TextInputLayout) layout.findViewById(R.id.inputLayoutDisease);
        inputLayoutFullName1 = (TextInputLayout) layout.findViewById(R.id.inputLayoutFullName);
        inputLayoutPhone1 = (TextInputLayout) layout.findViewById(R.id.inputLayoutPhone);
        inputLayoutPlace1 = (TextInputLayout) layout.findViewById(R.id.inputLayoutPlace);
        //layout.findViewById(R.id.input`)

        pat1_age = (EditText) layout.findViewById(R.id.pat_age);
        pat1_disease = (EditText) layout.findViewById(R.id.pat_disease);
        pat1_name = (EditText) layout.findViewById(R.id.pat_name);
        pat1_phone = (EditText) layout.findViewById(R.id.pat_phone);
        pat1_place = (EditText) layout.findViewById(R.id.pat_place);

        signupBtn1 = (Button) layout.findViewById(R.id.signUpBtn);

//        RadioGroup radio_group1 = (RadioGroup) layout.findViewById(R.id.radio_group);
//        radio_group1.setOnCheckedChangeListener();

        layout.findViewById(R.id.radio_sun).setOnClickListener(this);
        layout.findViewById(R.id.radio_mon).setOnClickListener(this);
        layout.findViewById(R.id.radio_tues).setOnClickListener(this);
        layout.findViewById(R.id.radio_wed).setOnClickListener(this);
        layout.findViewById(R.id.radio_thurs).setOnClickListener(this);
        layout.findViewById(R.id.radio_fri).setOnClickListener(this);
        layout.findViewById(R.id.radio_sat).setOnClickListener(this);


        signupBtn1.setOnClickListener(this);

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
            Toast.makeText(getContext(),"Please select a day",Toast.LENGTH_SHORT).show();
        };

        if (isValid & checked)
        {
           // Toast.makeText(getContext(),"Success!",Toast.LENGTH_SHORT).show();
            addInDB();
            Log.d("TAG3","done");
        };
    }

    private void addInDB() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        name = pat1_name.getText().toString().trim();
        place = pat1_place.getText().toString().trim();
        phone = pat1_phone.getText().toString().trim();
        disease = pat1_disease.getText().toString().trim();
        age = pat1_age.getText().toString().trim();
        dr = "chari";

        User user = new User(name, age, place,phone, disease, day,dr);

        Call<Result> call = apiService.createUser(user.getName(),
                user.getAge(),user.getPlace(),user.getPhone(),user.getDisease(),user.getDay(),user.getDr());
        Log.d("TAG28", String.valueOf(call.request()));

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
                Log.d("TAG45", String.valueOf(response.message()));
                Toast.makeText(getContext(), "Add Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                    startActivity(intent);
             //   }
                
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("TAG4",t.getMessage());

            }
        });
    }
}

