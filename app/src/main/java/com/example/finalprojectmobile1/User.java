package com.example.finalprojectmobile1;

import java.util.ArrayList;

public class User {
    private int score,id;
    private String name;
    private ArrayList<String>questions,answers;

public User(int s, String d)
{
  score=s;
  name=d;
  questions=new ArrayList<>();
  answers=new ArrayList<>();
}


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}