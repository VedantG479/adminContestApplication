package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView createMatchCard, UploadCard, deleteCard;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMatchCard = findViewById(R.id.createMatchCard);
        createMatchCard.setOnClickListener(this);
        UploadCard = findViewById(R.id.UploadCard);
        UploadCard.setOnClickListener(this);
        deleteCard = findViewById(R.id.deleteCard);
        deleteCard.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createMatchCard:
                CreateMatch_Fragment createMatch_fragment = new CreateMatch_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frame_layout,createMatch_fragment).addToBackStack("home").commit();
                break;
            case R.id.deleteCard:
                UploadFragment uploadFragment = new UploadFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_layout,uploadFragment).addToBackStack("home").commit();
                break;
            case R.id.UploadCard:
                DeleteMatch_Fragment deleteMatch_fragment = new DeleteMatch_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frame_layout,deleteMatch_fragment).addToBackStack("home").commit();
                break;
        }
    }
}