package hello;

import java.util.Random;

public class Greeting {

    private long id;
    private String content;
    private long numToGuess;
    private long userGuess;
    private long totalGuesses;
    private long randNum;


    Random rand = new Random(); //instance of random class
    long inClassNumToGuess = rand.nextInt(101);


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public long getNumToGuess() {
        return numToGuess;
    }

    public void setNumToGuess(long numToGuess) {
        this.numToGuess = numToGuess;
    }

    public long getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(long userGuess) {
        this.userGuess = userGuess;
    }

    public long getTotalGuesses() {
        return totalGuesses;
    }

    public void setTotalGuesses(long totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public long getRandNum() {
        return randNum;
    }

    public void setRandNum() {
        this.randNum = inClassNumToGuess;
    }

    public void resetAll() {
    this.userGuess = 0;
    this.numToGuess = 0;
    this.id = 0;
    this.content = "0";
    this.totalGuesses = 0;
        Random rand2 = new Random(); //instance of random class
        this.randNum = rand2.nextInt(101);
    }



    @Override
    public String toString() {
        return "\n Greeting{" +
                "id= " + id +
                ", content=' " + content +
                " numToGuess= " + numToGuess +
                " userGuess= " + userGuess +
                " totalGuesses= " + totalGuesses+
                " randNum= " + randNum+

                '\'' +
                '}';
    }
}