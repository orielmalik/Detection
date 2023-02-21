package com.example.finalprojectmobile1;
//visual
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class GameActivity extends AppCompatActivity {
    private fragment_list fragment_list;
    private GameManager manager;
    private firebase firebase;
    private  TextView name;
    private  boolean clicked=false,answer=true,time=false;
    private  boolean[] places;
    private int count=0,cq=0;
    TextView f;
    private ExtendedFloatingActionButton yes;
    private ExtendedFloatingActionButton no;
    private  Bundle bundle;
    private MyTimer timer;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fragment_list = new fragment_list();
        manager = new GameManager();
        places = new boolean[manager.getQuestions().length];
        for (int i = 0; i < manager.getQuestions().length; i++) {
            places[i] = false;
        }
        bundle = new Bundle();
        //yes = new ExtendedFloatingActionButton(this);
        yes = findViewById(R.id.YES);
        // no = new ExtendedFloatingActionButton(this);
        no = findViewById(R.id.NO);

        MakeActionYes();
        MakeActionNo();
        name = findViewById(R.id.positionLBL);
        firebase = new firebase();
       /* LottieAnimationView lottie = findViewById(R.id.animation_view);
        lottie.resumeAnimation();*/
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, fragment_list).commit();
        timer = new MyTimer();
        LottieAnimationView lottie = findViewById(R.id.car_view);
        lottie.resumeAnimation();

    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timer.handler.postDelayed(this, 0); //do it again in a second.
            manager.openNewLevel(answer,clicked,time);
            changQuestion();
            Log.d( "onResume: ",""+timer.getMinutes());
            clicked=false;
            EndGame();
        }


    };

    private void fillName()
    {

        firebase.getmDatabase().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText( snapshot.child("users").child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });;
    }
    private void EndGame()
    {
        if(timer.getMinutes()>0) {
            time=true;
            Intent intent = new Intent(this, ResultActivity.class);
            Bundle b= new Bundle();
            Log.d( "EndGame: ",""+manager.CountType());
            // firebase.readData("score","users");
            firebase.getmDatabase().child("users").child(String.valueOf(firebase.cuId)).child("score").setValue((int)manager.CountType());

           firebase.StoreScore();
           firebase.mDatabase.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   b.putInt("result",(int)(snapshot.child("TotalScore").getValue(Double.class)/snapshot.child("numberIds").getValue(Double.class)));
                   Log.d( "did: ",""+b.getInt("result"));
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });


            intent.putExtras(b);
            timer.stopTimer();
            startActivity(intent);
        }
    }

 private static String previous="";
    private  void changQuestion()
    {
        if(previous!=null) {
            if (!previous.equalsIgnoreCase(manager.treeUpdateQuestion())) {
                cq++;
                firebase.mDatabase.child("questions").child(String.valueOf(cq)).setValue(manager.treeUpdateQuestion() + "  " + answer + " \n ");
                previous = manager.treeUpdateQuestion();
            }
        }
        bundle.putString("str", manager.treeUpdateQuestion());
        Log.d("make: ", "" + manager.treeUpdateQuestion());
        // manager.setType(manager.findType(manager.treeUpdateQuestion()));
        manager.pointerToQuestions(manager.treeUpdateQuestion(), answer);
        fragment_list.setArguments(bundle);
        fragment_list.question(fragment_list.getArguments().getString("str"));
        bundle.putString("str", manager.treeUpdateQuestion());

    }






    @Override
    protected void onResume() {
        super.onResume();
        timer.setRunnable(runnable);
        // Log.d( "onResume: ",String.valueOf(clicked));
name.setText(getIntent().getExtras().getString("str"));
        timer.startTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.stopTimer();

    }

    private void make()
    {

        bundle.putString("str", manager.treeUpdateQuestion());
        Log.d("make: ", "" + manager.treeUpdateQuestion());
        // manager.setType(manager.findType(manager.treeUpdateQuestion()));
        manager.pointerToQuestions(manager.treeUpdateQuestion(), answer);
        fragment_list.setArguments(bundle);


    }
    private void  MakeActionYes()
    {
        yes.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       clicked=true;
                                       Log.d( "cli: ",""+clicked);
                                       answer=true;
                                       Log.d( "ans: ",""+answer);

                                   }
                               }

        );
    }
    private void  MakeActionNo()
    {
        no.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      clicked=true;
                                      answer=false;
                                      Log.d( "clic: ",""+clicked);
                                  }
                              }

        );
    }

    private void showQuestions(String b) {
        fragment_list.setQ(manager.getQuestions());

    }











}





