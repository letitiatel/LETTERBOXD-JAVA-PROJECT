package model;

import java.util.List;

public class Serial {

    public int id_serial;

    private String name;
    private int year;

    private List<Producer> producerlist;



    public Serial(){}

    public Serial(int id_serial,String name , int year, List<Producer> producerlist){
        this.id_serial = id_serial;
        this.name = name;
        this.year= year;
        this.producerlist = producerlist;


    }

    public int getIdserial(){
        return id_serial;
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
