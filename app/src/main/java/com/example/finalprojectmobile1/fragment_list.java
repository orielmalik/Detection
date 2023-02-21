package com.example.finalprojectmobile1;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class fragment_list extends Fragment {

    private MaterialTextView list_LBL_title;
    private String quest;
    public boolean clicked=false;
    private boolean []places;
    public String[]q;
private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
       // list_LBL_title=new MaterialTextView(view.getContext());
        findViews(view);
        initViews();

        places=new boolean[90];
        quest = null;
      //  question(getArguments().getString("str"));
        //changeTitle("List Page");
        return view;
    }

    public void question(String s) {
        if (s != null)

            list_LBL_title.setText(s);

        clicked=true;
    }






    private void findViews(View view) {

        list_LBL_title=view.findViewById(R.id.question);
    }


    public MaterialTextView getList_LBL_title() {
        return list_LBL_title;
    }

    public void setList_LBL_title(MaterialTextView list_LBL_title) {
        this.list_LBL_title = list_LBL_title;

    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getQuest() {
        return quest;
    }

    private void initViews() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user1Clicked();
            }
        };
        list_LBL_title.setOnClickListener(onClickListener);

    }

    public void setQ(String[] q) {
        this.q = q;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public String[] getQ() {
        return q;
    }
}

