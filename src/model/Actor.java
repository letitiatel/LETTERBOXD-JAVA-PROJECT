package model;

public class Actor {

    private int id_actor;

    private String name;

    private int age;
    private int numberofplays;
    private int numberofawards;

    public Actor(){}

    public Actor(int id_actor, String name, int age, int numberofplays,int numberofawards){
        this.id_actor = id_actor;
        this.name=name;
        this.age=age;
        this.numberofplays=numberofplays;
        this.numberofawards=numberofawards;
    }

    public int getIdactor(){

        return id_actor;
    }


    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public int getNumberofplays(){
        return numberofplays;
    }

    public int getNumberofawards(){
        return numberofawards;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;

    }

    public void setNumberofplays(int numberofplays){
        this.numberofplays = numberofplays;
    }

    public void setNumberofawards(int numberofawards){
        this.numberofawards = numberofawards;
    }





}
