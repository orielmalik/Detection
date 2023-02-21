package com.example.finalprojectmobile1;

class Node {
    private  int data;
    private String question=null;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setRight(Node right) {
        this.right = right;
    }
public void CheckNode()
{
    if(this.getRight()==this)
    {
        this.setRight(null);
    }
    if(this.getLeft()==this)
    {
        this.setLeft(null);
    }
}
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

