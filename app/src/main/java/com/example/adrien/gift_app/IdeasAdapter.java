package com.example.adrien.gift_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class IdeasAdapter extends ArrayAdapter<Idea> implements Filterable {

    private DatabaseReference mDatabase;
    private Idea idea;
    private ArrayList<Idea> ideasArray;
    private ArrayList<Idea> ideasArrayFilter;

    public IdeasAdapter(Context context, ArrayList<Idea> ideas){

        super(context, 0, ideas);
        this.ideasArray = ideas;
        this.ideasArrayFilter = ideas;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("IdeasAdapter", "Coucou de getView");

        idea = getItem(position);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_idea, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.id_imageView);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.id_ideatitle);
        TextView tvRecipient = (TextView)convertView.findViewById(R.id.id_idearecipient);
        TextView tvDate = (TextView)convertView.findViewById(R.id.id_ideadate);
        TextView tvUrl = (TextView)convertView.findViewById(R.id.id_ideaurl);
        TextView tvPrice = (TextView)convertView.findViewById(R.id.id_ideaprice);
        TextView tvDescription = (TextView)convertView.findViewById(R.id.id_ideadescription);

        tvTitle.setText(idea.getTitle());
        tvDate.setText(idea.getForWhen());
        tvUrl.setText(idea.getUrl());
        tvRecipient.setText(idea.getRecipient());
        tvPrice.setText(idea.getPrice());
        tvDescription.setText(idea.getDescription());

        return convertView;
    }

    @Nullable
    @Override
    public Idea getItem(int position) {
        return ideasArray.get(position);
    }

    @Override
    public int getCount() {
        return ideasArray.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Log.d("IdeasAdapter", "Coucou de getFilter performFiltering");
                FilterResults results = new FilterResults();
                ArrayList<Idea> FilteredArrayIdeas = new ArrayList<Idea>();

                if(constraint == null || constraint.length() == 0){
                    results.count = ideasArrayFilter.size();
                    results.values = ideasArrayFilter;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for(int i=0; i < ideasArray.size(); i++){
                        Log.d("IdeasAdapter", "Coucou de getFilter performFiltering loop");
                        Idea ideaCursor = ideasArray.get(i);
                        if(ideaCursor.getTitle().toLowerCase().startsWith(constraint.toString())){
                            FilteredArrayIdeas.add(ideaCursor);
                        }
                    }
                    results.count = FilteredArrayIdeas.size();
                    results.values = FilteredArrayIdeas;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.d("IdeasAdapter", "Coucou de getFilter publishResults");
                if(results.count != 0){

                    ideasArray = (ArrayList<Idea>)results.values;
                    notifyDataSetChanged();
                }

            }
        };

    }
}
