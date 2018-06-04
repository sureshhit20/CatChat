package com.hfad.catchat.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hfad.catchat.Adapter.ContactsAdapter;
import com.hfad.catchat.Fragments.PatientProfile;
import com.hfad.catchat.Model.Contact;
import com.hfad.catchat.R;
import com.hfad.catchat.Retrofit.ApiClient;
import com.hfad.catchat.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ContactsList extends AppCompatActivity implements ContactsAdapter.ContactsAdapterListener {

    SearchView searchView;
    ContactsAdapter contactsAdapter;
    private List<Contact> contactList = new ArrayList<>();
    List<Contact> temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white,getTheme()));


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Contact>> call = apiService.getContacts();
        Log.d("TAG21", String.valueOf(call.request()));

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                temp = response.body();
                Log.d("TAG22", "number of contacts are " + temp.size());
                contactList.clear();
                contactList.addAll(temp);
                Log.d("TAG24", "number of original contacts are " + contactList.size());
                contactsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.d("TAG23", String.valueOf(t.getLocalizedMessage()));

            }
        });

        recyclerView.addItemDecoration(new MyDividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL, 36));
        contactsAdapter = new ContactsAdapter(getApplicationContext(), contactList, this);
        recyclerView.setAdapter(contactsAdapter);
        contactList.clear();
        contactList.addAll(temp);
        Log.d("TAG24", "number of original contacts are " + contactList.size());
        contactsAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                /*if (contactsAdapter == null) {
                    Log.d("TAG6", "this is null");
                    contactsAdapter = new ContactsAdapter(getApplicationContext(), contactList,this);

                };*/
                contactsAdapter.getFilter().filter(query);
                return true;
            }
        });

        return true;
    }

    @Override
    public void onContactSelected(Contact contact) {
        //Toast.makeText(getApplicationContext(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();

 /*       Bundle bundle = new Bundle();
        bundle.putString("name",contact.getName());
        bundle.putString("phone",contact.getPhone());
        bundle.putString("place",contact.getPlace());
        Fragment fragment = new PatientProfile();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.contacts_layout, fragment);
        ft.replace(R.id.totalpage, fragment);

        ft.addToBackStack(null);
        ft.commit();*/

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_ID,contact.getId());
        startActivity(intent);
    }

}
