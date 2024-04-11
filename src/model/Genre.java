package model;

public class Genre {

    private int id_genre;
    private String name;

    public Genre(){}

    public Genre(int id_genre,String name){
        this.id_genre = id_genre;
        this.name = name;
    }

    public int getIdgenre(){
        return id_genre;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }



}
