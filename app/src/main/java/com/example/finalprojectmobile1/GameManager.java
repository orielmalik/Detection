package com.example.finalprojectmobile1;

//data
public class GameManager {
    private Node tree;
    private String[] questions;
    private String type;
    private int count = 0;

    public GameManager() {
        type = null;
        tree = new Node(0);
        questions = new String[99];
        init();
    }

    public void openNewLevel(boolean answer, boolean clicked, boolean time) {

        int temp = tree.getData();

        Node n;
        type = this.findType(this.treeUpdateQuestion());
        if (clicked) {
            count++;
            if (type.equalsIgnoreCase("feelings")) {
                n = new Node(temp++);

                if (answer) {
                    n = new Node(temp++);
                    n.setQuestion(this.getQuestions()[(int) (Math.random() * 24 + 98-24)]);
                    tree.setRight(n);
                } else {
                    n = new Node(temp--);
                    n.setQuestion(this.getQuestions()[(int) (Math.random() * 98)]);

                    tree.setLeft(n);
                }

            } else if (type.equalsIgnoreCase("performance")) {
                if (answer) {
                    n = new Node(temp);
                    n.setQuestion(this.getQuestions()[(int) (Math.random() * 7 + 23)]);
                    tree.setRight(n);
                } else {
                    n = new Node(temp);
                    n.setQuestion(this.getQuestions()[(int) (Math.random() * 22)]);
                    tree.setLeft(n);
                }
            } else {
                if (answer) {
                    n = new Node(temp += 3);
                    n.setQuestion(this.getQuestions()[(int) (Math.random() * 76 + 22)]);

                    tree.setRight(n);
                } else {
                    n = new Node(temp -= 2);
                    n.setQuestion(this.getQuestions()[(int) (Math.random() * 98)]);

                    tree.setLeft(n);
                }
            }

            tree.setQuestion(n.getQuestion());
            if (time) {
                tree.setData(temp);
            }
            if(count>40)
            {
                n.setQuestion(this.getQuestions()[(int) (Math.random() * questions.length)]);

                tree.setLeft(n);
            }
        }
    }

    public void init() {
        //israelian
        questions[30] = "Are you tired";
        questions[31] = "Are you Normal";
        questions[32] = "Do you love pizza";
        questions[33] = "Do you listen Hanan Ben Ari";
        questions[34] = "Are you king at your home";
        questions[35] = "Did you see Friends sequence";
        questions[36] = "Do you hava games console";
        questions[37] = "Are you from Givat Shmuel";
        questions[38] = "Are you from Netanya ";
        questions[79] = "Are you from Ashdod";
        questions[10] = "Are you from  TLV";
        questions[11] = " Are you traditionalist";
        questions[12] = "Do you listen Eyal Golan";
        questions[13] = "Were you at Estadio Santiago Bernabeu";
        questions[14] = "Did you travel around the world";
        questions[15] = "Were you at Hawai";
        questions[16] = "Are you cheater";
        questions[17] = "Do you love strength";
        questions[18] = "Are you Gym mate";
        questions[19] = "Are you Normal";
        questions[20] = "Are you aiming high?";
        questions[21] = "Are you right";
        questions[22] = "DO you love Israel";

//performance
        questions[23] = "Is there room for growth within our department";
        questions[24] = "Are there goals should you work toward?";
        questions[25] = " can you help world to succeed?";
        questions[26] = " What would make me a candidate for a promotion";
        questions[27] = "Are you meeting your expectations?";
        questions[28] = " How much are you measuring my progress?";
        questions[29] = "Do you have skills should tp improve to grow in this company?";
        questions[0] = "Are there any opportunities for professional development?";
        //feeling
        questions[1] = "Have you ever stolen a roommate or co-worker’s food?";
        questions[2] = "Are you a good driver?";
        questions[3] = "Have you ever given a statement to the police?";
        questions[4] = "Do you aim to be a funny guy/girl";
        questions[5] = "Do you like to travel?";
        questions[6] = "Have you been to many foreign countries?";
        questions[7] = " Do you believe in vengeance?";
        questions[8] = " Do you like to read?";
        questions[39] = " Do you enjoy re-watching good movies or series?";
        questions[40] = "Are you a trusting person?";
        questions[41] = "Are you more of a pessimist or an optimist?";
        questions[42] = " Do you like your job?";
        questions[43] = "Would you rather be doing something else with your life right now?";
        questions[44] = " Do first impressions matter?";
        questions[45] = " Are you willing to change your opinion if proven wrong?";
        questions[46] = " Can you admit your mistakes?";
        questions[47] = "Is money really important to you?";
        questions[48] = "Do you try to have as much free time as possible?";
        questions[49] = "Do you believe in a higher power?";
        questions[50] = "Have you ever lied about yourself?";
        questions[51] = "Are you good with secrets?";
        questions[52] = "Do you tend to meddle in people’s private business?";
        questions[53] = "Have you ever stepped on your principles?";
        questions[54] = " Is it important to be true to yourself?";
        questions[55] = " Have you ever laughed so hard you got dizzy?";
        questions[56] = "Do you believe in magic?";
        questions[57] = "Do you ever try on clothes you know you won’t buy?";
        questions[58] = "Do you enjoy spending time alone?";
        questions[59] = " Have you ever been majorly disappointed by a friend or loved one?";
        questions[60] = "Is breakfast for dinner acceptable?";
        questions[61] = "Should you put on your pants before your socks?";
        questions[62] = "Is the thermostat setting a reason for arguing?";
        questions[64] = "Are caterpillars better than butterflies?";
        questions[65] = "Do you enjoy eating?";
        questions[66]=  " Are you an extrovert or introvert?";
        questions[67]= "Do you have a strong sense of empathy?";
        questions[68]= " Are you easily annoyed by others?";
        questions[69]= "Do you have a tendency to hold grudges?";
        questions[70]= "Are you confident in social situations?";
        questions[71]= "Do you like to plan ahead or go with the flow?";
        questions[72]= "Are you a perfectionist?";
        questions[73]="Are you comfortable speaking in public?";
        questions[74]="Are you a good listener?";
        questions[75]="Do you have a strong sense of humor?";
        questions[76]= "Are you sensitive to criticism?";
        questions[77]= "Do you like to take risks?";
        questions[78]= "Are you comfortable with change?";
        questions[9]= "Do you have strong leadership skills?";
        questions[80]= "Are you an optimist or pessimist?";
        questions[81]= "Do you often worry about what others think of you?";
        questions[82]= "Are you organized or do you prefer a more spontaneous approach?";
        questions[83]= "Do you have a strong sense of responsibility?";
        questions[84]= "Do you like to take control of situations?";
        questions[85]= "Are you open-minded?";
        questions[86]= "Do you often feel overwhelmed by emotions?";
        questions[87]=         "Are you easy-going?";
        questions[88]=         "Do you have a strong work ethic?";
        questions[89]= "Are you assertive?";
        questions[90]=         "Do you have a tendency to be negative?";
        questions[91]=         "Are you a good communicator?";
        questions[92]= "Are you a good problem solver?";
        questions[93]= "Are you independent or do you prefer to be part of a group?";
        questions[94]= "Are you a good decision maker?";
        questions[95]=        "Do you have strong self-discipline?";
        questions[96]=        "Are you competitive?";
        questions[97]=         "Do you have a strong sense of creativity?";
        questions[98]=         "Are you a good negotiator?";

    }

    public String treeUpdateQuestion() {
        if (null == tree.getQuestion()) {
            tree.setQuestion(questions[(int) (Math.random() * (questions.length-1))]);
        }

        return tree.getQuestion();
    }

    public int getDate() {
        return tree.getData();
    }

    public void pointerToQuestions(String question, boolean check) {
        Node yes = new Node(0);
        Node no = new Node(0);
        tree.setRight(yes);
        tree.setLeft(no);
        if (check) {
            yes.setQuestion(question);
            tree = yes;
        } else {
            no.setQuestion(question);
            tree = no;
        }
        tree.CheckNode();
    }

    public String findType(String s) {
        for (int i = 0; i < questions.length; i++) {
            if (s.equals(questions[i])&&s!=null) {
                if (i > 31 && i < 23 + 31) {
                    return "feelings";
                } else if (i > -22 && i < 31) {
                    return "performance";
                }

            }
        }
        return "other";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public int CountType() {


        if (type.equalsIgnoreCase("feelings")) {
            return count*3+12;

        } else if (type.equalsIgnoreCase("performance")) {
            return count+12;

        } else {
            return count+tree.getData();

        }

    }
}
