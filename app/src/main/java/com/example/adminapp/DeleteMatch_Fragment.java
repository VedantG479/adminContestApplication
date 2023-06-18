package com.example.adminapp;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeleteMatch_Fragment extends Fragment implements View.OnClickListener {
    private CardView morningCard,afternoonCard,eveningCard;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_delete_match_, container, false);
        morningCard = view.findViewById(R.id.morningCard);
        morningCard.setOnClickListener(this);
        afternoonCard = view.findViewById(R.id.afternoonCard);
        afternoonCard.setOnClickListener(this);
        eveningCard = view.findViewById(R.id.eveningCard);
        eveningCard.setOnClickListener(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.morningCard:
                Morning_Fragment morning_fragment = new Morning_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frame_layout2,morning_fragment).addToBackStack("delete").commit();
                break;
            case R.id.afternoonCard:
                Afternoon_Fragment afternoon_fragment = new Afternoon_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frame_layout2,afternoon_fragment).addToBackStack("delete").commit();
                break;
            case R.id.eveningCard:
                Evening_Fragment evening_fragment = new Evening_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frame_layout2,evening_fragment).addToBackStack("delete").commit();
                break;
        }
    }
}