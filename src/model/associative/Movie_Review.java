package model.associative;

import model.Rating;
import model.User;
import model.Movie;
import model.Review;

public class Movie_Review extends Review {

    private Movie movie;

    public Movie_Review(int id_review, String review, User user, Rating rt, Movie movie){
        super(id_review, review, user, rt);
        this.movie = movie;
    }

    public Movie getMovie(){
        return movie;
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public int getIdmovie(){
        return movie.getIdmovie();
    }
}
