package model.associative;

import model.Rating;
import model.Serial;
import model.Review;
import model.User;


public class Serial_Review extends Review{
    private Serial serial;

    public Serial_Review(int id_review, String review, User user, Rating rt,Serial serial){
        super(id_review, review, user, rt);
        this.serial = serial;
    }

    public Serial getSerial(){
        return serial;
    }

    public void setSerial(Serial serial){
        this.serial = serial;
    }

    public int getIdserial(){
        return serial.getIdserial();
    }


}
