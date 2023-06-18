package com.example.adminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Morning_Fragment extends Fragment {
    private RecyclerView morningRecycler;
    private ProgressBar progressBar;
    private ArrayList<Match_Data> list;
    private Match_Adapter adapter;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_morning_, container, false);
        morningRecycler = view.findViewById(R.id.morningRecycler);
        progressBar = view.findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Matches").child("Morning");
        morningRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        morningRecycler.setHasFixedSize(true);

        getData();
        return view;
    }
    private void getData(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Match_Data data = snapshot1.getValue(Match_Data.class);
                    list.add(0,data);
                }
                adapter = new Match_Adapter(getContext(),list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                morningRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}