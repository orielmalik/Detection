package com.example.finalprojectmobile1;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class firebase {
    public  static   int userId=0;
    private int code,specificScore;
    boolean checkId=false;
    public  static  int cuId=0,numofUsers=1;
    public ArrayList<String>Names;
    public DatabaseReference mDatabase;
    public ArrayList<Integer> ids;
    public StorageReference storageRef ;
    private User use;

    private Map<String, Object> updates ;

    // ...
    public firebase()
    {
        use =new User(0,null);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        updates = new HashMap<>();
Names=new ArrayList<>();
ids=new ArrayList<>();
        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://finalmobile1-646d5.appspot.com");
    }
    public User writeNewUser( String name,String child,int code) {//or new code
        userId++;
        readData(null,child);
        if (checkId==false)
        {
            userId+=2;
        }
        cuId=userId;
        if(child.equalsIgnoreCase("users")) {
            User user = new User(0, name);
            user.setId(userId);
            mDatabase.child(child).child(String.valueOf(userId)).setValue(user);
            use=user;
            return user;
        }
        else
        {
            // Create a map to store the new values
            if(code>999) {
                updates.put(String.valueOf(userId), code);
            }
            mDatabase.child(child).updateChildren(updates);
            return  null;
        }
    }
    public  void readData(String value,String child)
    {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                for (DataSnapshot childSnapshot : dataSnapshot.child(child).getChildren()) {
                    String field = childSnapshot.getKey();
                    if(field.equalsIgnoreCase(value)&&child.equalsIgnoreCase("codes"))
                    {
                        checkId=false;
                    }
                    else
                    {
                        checkId=true;

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w( "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);
    }

public void StoreScore()
{
    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            int e=1;
            Double sum=0.0;
            for (DataSnapshot childSnapshot : snapshot.child("users").getChildren()) {
                if (childSnapshot.child("score").getValue()!=null) {
               Double value=   childSnapshot.child("score").getValue(Double.class);
                    Log.i( "s: ",""+value);
                    sum+=value;

e++;

                }
            }
            mDatabase.child("TotalScore").setValue(sum.doubleValue());//
            Log.i( "gs: ",""+sum.doubleValue());
            mDatabase.child("numberIds").setValue(e);//
            Log.i( "gs: ",""+ e);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}
    public void RemoveAll(String path)
    {
        mDatabase.child(path).removeValue();
    }

    public int getCode() {
        return code;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StorageReference getStorageRef() {
        return storageRef;
    }

    public User getUse() {
        return use;
    }

    public void setUse(User use) {
        this.use = use;
    }
}