package com.hfad.catchat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.hfad.catchat.Model.Contact;
import com.hfad.catchat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> implements Filterable {

    private Context context;
    private List<Contact> contactList;
    private static List<Contact> contactListFiltered;
    private ContactsAdapterListener listener;


    public ContactsAdapter(Context context, List<Contact> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;

    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView phone;
        TextView name;
        TextView place;

        public ContactViewHolder(View v) {
            super(v);
            phone = (TextView) v.findViewById(R.id.phone);
            name = (TextView) v.findViewById(R.id.name);
            place = (TextView) v.findViewById(R.id.place);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });

        }
    }

    @NonNull
    @Override
    public ContactsAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ContactViewHolder holder, int position) {
        holder.name.setText(contactListFiltered.get(position).getName());
        holder.phone.setText(contactListFiltered.get(position).getPhone());
        holder.place.setText(contactListFiltered.get(position).getPlace());

        //Picasso.get().load(contactListFiltered.get(position).getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                Log.d("TAG2", charString);
                if (charString.isEmpty()) {
                    Log.d("TAG4", "empty");
                    contactListFiltered = contactList;
                } else {
                    Log.d("TAG5", "value is " + String.valueOf(contactList.size()));
                    List<Contact> filteredList = new ArrayList<>();
                    for (Contact row : contactList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    contactListFiltered = filteredList;
                    Log.d("TAG3", String.valueOf(filteredList.size()));
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Contact>) filterResults.values;
                ContactsAdapter.this.notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Contact contact);
    }
}

