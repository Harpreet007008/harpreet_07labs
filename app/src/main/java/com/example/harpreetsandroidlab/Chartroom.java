package com.example.harpreetsandroidlab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Chartroom extends AppCompatActivity  {


    ArrayList<ChatMessage> messages = new ArrayList<>();
    ChatAdapter adt  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load a layout:
        setContentView(R.layout.chatlayout);


        EditText messageTyped = findViewById(R.id.messageEdit);
        Button send = findViewById(R.id.sendbutton);
        RecyclerView chatList = findViewById(R.id.myrecycler);

        adt = new ChatAdapter() ;

        chatList.setAdapter(adt);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        chatList.setLayoutManager(layoutManager);


        send.setOnClickListener( click -> {
                    ChatMessage nextMessage = new ChatMessage(messageTyped.getText().toString());
                    messages.add(nextMessage);//adds to array list
                    //clear the edittext:
                    messageTyped.setText("");
                    //refresh the list:
                    adt.notifyItemInserted( messages.size() - 1 );
                }
        );
    }

    private class MyRowViews<Chatroom> extends RecyclerView.ViewHolder{

        TextView rowMessage;

        public MyRowViews(View itemView) {
            super(itemView);

            itemView.setOnClickListener(  clik ->
            {
                AlertDialog.Builder builder = new AlertDialog.Builder( Chartroom.this);
                builder.setMessage( "Do you want to delete this?" )
                        .setTitle("Title").setPositiveButton("Yes", (dlg, clic) -> {

                    int row = getAbsoluteAdapterPosition();
                    messages.remove(row);
                    adt.notifyItemRemoved(row); })
                        .setNegativeButton("No", (dlg, clic) -> {  })
                        .create()
                        .show();

            });

            rowMessage = itemView.findViewById(R.id.message);
        }

        private int getAbsoluteAdapterPosition() {
            return 0;
        }
    }



    private class ChatAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View constraintLayout =  inflater.inflate(R.layout.sent_message, parent, false);

            return new MyRowViews(  constraintLayout  );
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            MyRowViews thisRowLayout = (MyRowViews)holder;
            thisRowLayout.rowMessage.setText("This is row " + position);
            //set the date text:
        }

        @Override
        public int getItemCount() {
            return  messages.size();
        }
    }

    private static class ChatMessage {
        public String message;
        public int sendOrReceive;
        public String timeSent;

        public ChatMessage(String s) {
            message = s;
        }

        public ChatMessage(String message, int sendOrReceive, String timeSent) {
            this.message = message;
            this.sendOrReceive = sendOrReceive;
            this.timeSent = timeSent;
        }


        String getMessage() {
            return message;
        }

        int getSendOrReceive() {
            return sendOrReceive;
        }

        String getTimeSent() {
            return timeSent;
        }
    }

}
