package com.example.finalprojectmobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    FloatingActionButton c,b;
    private  int code= ((int)(Math.random()*998+1000)),clicked=0;
    EditText edit,edname;
    private  boolean exCode=false;
    private static  int id=0;
    private firebase firebase;
    private User player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        c=findViewById(R.id.login_btn);
        b=findViewById(R.id.accept_btn);
        LottieAnimationView lottie = findViewById(R.id.joker);
        lottie.setSpeed(0.3f);
        lottie.resumeAnimation();
        firebase=new firebase();
        edit=findViewById(R.id.enter_Code);
        edname=findViewById(R.id.enter_name);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked++;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_TEXT,"code is   "+String.valueOf(code));
                firebase.writeNewUser(String.valueOf(id++),"codes",code);

                startActivity(intent);


            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.writeNewUser(edname.getText().toString(),"users",0);
                Log.d( "onClick: ",edit.getText().toString());
                checkCode(edit.getText().toString());


            }

        });
        cleanBackground(edit);
        cleanBackground(edname);
    }
    private void  cleanBackground(EditText editText)
    {


        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
           /* touchCount++;
            if (touchCount == 1) {*/
                editText.setText("");

                return false;
            }
        });

    }
    public void openGame()
    {
        Intent OPENintent= new Intent(this, GameActivity.class);
        Bundle b=new Bundle();
        b.putString("str",edname.getText().toString());
        OPENintent.putExtras(b);
        startActivity(OPENintent);
    }
    private void  enterCode()
    {

            Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show();
            openGame();

    }
    public void checkCode(String code)
    {
        firebase.mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.child("codes").getChildren()) {
                    String field = childSnapshot.getKey();
                    Object value = childSnapshot.getValue();
                    Log.d( "onDataChange: ",value.toString());
                    if(code.equalsIgnoreCase(value.toString()))
                    {
                        exCode=true;
                        Log.d( "exCodee: ",""+exCode);
                        enterCode();

                        return;
                    }
                }


                        }


            ;
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }

private void checkArrayList(int value,ArrayList<Integer>arrayList)
{

}

}