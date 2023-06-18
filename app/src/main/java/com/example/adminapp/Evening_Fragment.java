package com.example.adminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Evening_Fragment extends Fragment {
    private RecyclerView eveningRecycler;
    private ProgressBar progressBar;
    private ArrayList<Match_Data> list;
    private Match_Adapter adapter;
    private DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evening_, container, false);
        eveningRecycler = view.findViewById(R.id.eveningRecycler);
        progressBar = view.findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Matches").child("Evening");
        eveningRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        eveningRecycler.setHasFixedSize(true);

        getData();
        return view;    }
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
                eveningRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}