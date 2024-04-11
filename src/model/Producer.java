package model;

public class Producer {

    private int id_producer;

    private String name;
    private int age;
    private int nbofproduces;
    private int nbofawards;

    public Producer(){}

    public Producer(int id_producer,String name, int age, int nbofproduces,int nbofawards){
        this.id_producer = id_producer;
        this.name = name;
        this.age = age;
        this.nbofproduces = nbofproduces;
        this.nbofawards = nbofawards;
    }

    public int getIdproducer(){
        return id_producer;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public int getNbofproduces(){
        return nbofproduces;
    }

    public int getNbofawards(){
        return nbofawards;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setNbofproduces(int nbofproduces){
        this.nbofproduces = nbofproduces;
    }

    public void setNbofawards( int nbofawards){
        this.nbofawards = nbofawards;
    }

}
