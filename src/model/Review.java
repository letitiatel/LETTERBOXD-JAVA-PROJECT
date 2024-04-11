package model;

public class Review {

    private int id_review;
    private String review;
    private User user;
    private Rating rt;

    public Review(){}

    public Review(int id_review, String review, User user, Rating rt){
        this.id_review = id_review;
        this.review = review;
        this.user = user;
        this.rt = rt;
    }

    public int getIdreview(){
        return id_review;
    }

    public String getReview(){
        return review;
    }

    public User getUser(){
        return user;
    }

    public Rating getRating(){
        return rt;
    }

    public void setReview(String review){
        this.review = review;
    }

    public void setIdreview(int id_review) {
        this.id_review = id_review;
    }



}
