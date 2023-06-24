package com.example.adminapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Match_Adapter extends RecyclerView.Adapter<Match_Adapter.matchViewAdapter> {
    private Context context;
    private List<Match_Data> list;

    public Match_Adapter(Context context, List<Match_Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public matchViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_layout,parent,false);
        return new matchViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull matchViewAdapter holder, int position) {
        Match_Data currentItem = list.get(position);
        holder.matchTime.setText("Scheduled Date: "+currentItem.getMatchTime());
        holder.matchDate.setText("Scheduled Time: "+currentItem.getMatchDate());
        holder.uploadTime.setText("Creation Time: "+currentItem.getUploadTime());
        holder.uploadDate.setText("Creation Date: "+currentItem.getUploadDate());
        holder.referenceID.setText("Reference ID: "+currentItem.getReferID());
        holder.RoomID.setText("Room ID: "+currentItem.getRoom_Id());
        holder.RoomPass.setText("Room Password: "+currentItem.getRoom_pass());
        holder.price.setText("Entry Amount: "+currentItem.getMatchCharge());
        holder.matchDuration.setText("Match Duration: "+currentItem.getMatchDuration());
        holder.matchCategory.setText("Match Category: "+currentItem.getMatchCategory());
        holder.reward.setText("Reward: "+currentItem.getReward());

        Picasso.get().load(currentItem.getImageUrl()).into(holder.ticketImage);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this ticket");
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Matches");
                        reference.child(currentItem.getMatchDuration()).child(currentItem.getReferID()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(v.getContext(), "Deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(), "Something Went Wrong",Toast.LENGTH_SHORT).show();
                            }
                        });
                        notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = null;
                try{
                    dialog=builder.create();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if(dialog!=null){
                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class matchViewAdapter extends RecyclerView.ViewHolder {
        private ImageView ticketImage;
        private TextView uploadDate, uploadTime,referenceID,RoomID,RoomPass,price,matchDuration,matchCategory,reward,matchDate,matchTime;
        private Button deleteBtn;

        public matchViewAdapter(@NonNull View itemView) {
            super(itemView);
            ticketImage=itemView.findViewById(R.id.ticketImage);
            uploadDate=itemView.findViewById(R.id.uploadDate);
            uploadTime=itemView.findViewById(R.id.uploadTime);
            referenceID=itemView.findViewById(R.id.referenceID);
            RoomID=itemView.findViewById(R.id.roomID);
            RoomPass=itemView.findViewById(R.id.roomPass);
            price=itemView.findViewById(R.id.price);
            matchDuration=itemView.findViewById(R.id.matchDuration);
            matchCategory=itemView.findViewById(R.id.matchCategory);
            reward=itemView.findViewById(R.id.reward);
            matchDate=itemView.findViewById(R.id.matchDate);
            matchTime=itemView.findViewById(R.id.matchTime);
            deleteBtn=itemView.findViewById(R.id.deleteButton);
        }
    }
}