package model;

import java.util.List;

public class Movie {

    private int id_movie;

    private String name;
    private int year;

    private List<Producer> producerlist;



    public Movie(){}

    public Movie(int id_movie, String name , int year, List<Producer> producerlist){
        this.id_movie = id_movie;
        this.name = name;
        this.year= year;

        this.producerlist = producerlist;


    }

    public int getIdmovie(){
        return id_movie;
    }

    public String getName(){
        return name;
    }

    public int getYear(){
        return year;
    }



    public List<Producer> getProducerlist(){
        return producerlist;
    }



    public void getName(String name){
        this.name = name;
    }

    public void getYear(int year){
        this.year = year;

    }

    public void addProducer(Producer producer){
        producerlist.add(producer);
    }

    public void removeProducer(Producer producer){
        producerlist.remove(producer);
    }


}
