package model;

public class Rating {
    private int id_rating;
    private int nota;

    public Rating(){}

    public Rating(int id_rating, int nota){
        this.id_rating = id_rating;
        this.nota = nota;
    }

    public int getIdrating(){
        return id_rating;
    }

    public int getNota(){
        return nota;

    }

    public void setNota(int nota){
        this.nota = nota;
    }

}
