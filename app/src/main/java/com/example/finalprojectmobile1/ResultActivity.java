package com.example.finalprojectmobile1;

import static java.nio.file.Files.readAllBytes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    private ImageView change;
    private MyTimer show;
    private firebase firebase;
    private MaterialButton secrets;
    private MaterialTextView m;
    private String strUri = null;
    private ImageView[] end;
    String arr[];
    private User u;
    private String info;
    private CallBack_Uri callBack_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        change = findViewById(R.id.change);
        m = findViewById(R.id.end_text);
        end = new ImageView[13];
        arr = new String[end.length];

        secrets = findViewById(R.id.secrets);
        firebase = new firebase();
        show = new MyTimer();
    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            show.handler.postDelayed(r, 1000);
            if (show.getSeconds() > 10) {
                load();
                conflict(end);
            }
        }

    };

    @Override
    protected void onResume() {
        super.onResume();

        show.setRunnable(r);
        show.startTimer();
        secrets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeSecrets();
            }
        });
        callBack_uri = new CallBack_Uri() {
            @Override
            public void SetUri() {
                try {
                    Download_File();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        }

        @Override
        protected void onPause () {
            super.onPause();
            firebase.RemoveAll("questions");
            //  show.stopTimer();
            firebase.RemoveAll("users");
        }

        private void load () {
            for (int i = 0; i < 13; i++) {
                end[i] = new ImageView(this);
            }
            end[11].setImageResource(R.drawable.princess);
            end[1].setImageResource(R.drawable.dragon);
            end[2].setImageResource(R.drawable.ic_smurf);
            end[4].setImageResource(R.drawable.rabbit);
            end[0].setImageResource(R.drawable.zombie);
            end[5].setImageResource(R.drawable.ic_genie);
            end[6].setImageResource(R.drawable.eagle);
            end[7].setImageResource(R.drawable.robot);
            end[8].setImageResource(R.drawable.superheroe);
            end[3].setImageResource(R.drawable.mario);
            end[9].setImageResource(R.drawable.magican);
            end[10].setImageResource(R.drawable.ic_detective);
            end[12].setImageResource(R.drawable.luka);


        }

        int tar = 0;

        private void conflict (ImageView[]arr){
            int result = getIntent().getExtras().getInt("result");
            change = findViewById(R.id.change);
            int target = (3*result +(int)(Math.random()*100)) ;
            target = target % (end.length - 1);
            tar = target;
            change.setImageDrawable(arr[target].getDrawable());
            change.setVisibility(View.VISIBLE);
            byTarget(target);


        }

        private void byTarget ( int target){

            arr[4] = "YOU ARE LIKE A RABBIT-ONLY WANTS HIS CARROT \n change your friends";
            arr[1] = "YOU ARE LIKE A DRAGON-ANYONE SEE HIM IN EARTH \n Drop your ego";
            arr[2] = "YOU ARE LIKE A SMURF-lISTEN TO YOUR FATHER & AFRAID FROM GARGAMEL \n DONT BE AFRAID ANYONE";
            arr[3] = "YOU ARE LIKE A SUPER MARIO-ONLY FIND MUSHROOMS \n achive High Education";
            arr[0] = "YOU ARE LIKE A ZOMBIE-A DIED MAN \n change your nutrition and get in shape";
            arr[5] = "YOU ARE LIKE A ginie-only give to your friends 3 requests";
            arr[6] = "YOU ARE LIKE A EAGLE- predator & FLY IN THE SKY / decrease your profile";
            arr[7] = "YOU ARE LIKE A ROBOT-ASK YOUR WIFE OR EXGIRLFRIEND \n Develop emotional understanding";
            arr[8] = "YOU ARE LIKE A SUPERMAN-OMNIPOTENT ";
            arr[9] = "YOU ARE LIKE A MAGICIAN-DO A MAGIC TO SAVE THE WORLD";
            arr[10] = "YOU ARE LIKE A DETECTIVE -BEST TO DISCOVER EVERYTHING";
            arr[11] = "YOU ARE LIKE A PRINCESS-GET ALL WHAT YOU WANT";
            arr[12] = "YOU ARE LIKE A LUKA-THE REAL MAGIC";
            m.setText(arr[target]);
        }


        public void MakeSecrets() {
            StringBuilder str = new StringBuilder(new String());
            firebase.getmDatabase().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String fileName = "file:///Downloads/to/secrets.txt";
                    StorageReference fileRef = firebase.getStorageRef().child(fileName);
                    //fileContent();
                    str.append(snapshot.child("questions").getValue());
                    Log.e("hg", str.toString());
                    String fileContent = str.toString();
                    byte[] bytes = fileContent.getBytes();
                    UploadTask uploadTask = fileRef.putBytes(bytes);
                    File f = new File(fileName);
                    if (!f.exists()) {

                        return;
                    }
                    Uri updatedFileUri = Uri.fromFile(f);
                    Toast.makeText(ResultActivity.this, updatedFileUri.toString(), Toast.LENGTH_SHORT).show();
                    fileRef.putFile(updatedFileUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // File updated successfully
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors here
                                }
                            });

                    StorageMetadata metadata = new StorageMetadata.Builder()
                            .setCustomMetadata("access", "write")
                            .build();
                    uploadTask.addOnSuccessListener(taskSnapshot -> {
                        fileRef.updateMetadata(metadata);
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Log.e("fileContent: ", str.toString());
        }


        private void Download_File() throws IOException {
            StorageReference gsReference = firebase.getStorageRef();

            gsReference.child("file://Downloads/to/secrets.txt").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Toast.makeText(ResultActivity.this, "The "+uri.toString(), Toast.LENGTH_SHORT).show();
                }

                ;

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }
    }



