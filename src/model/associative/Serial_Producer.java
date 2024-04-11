package model.associative;

public class Serial_Producer {

    private int idserial;
    private int idproducer;

    public Serial_Producer(int idserial, int idproducer){
        this.idserial = idserial;
        this.idproducer = idproducer;
    }
    public int getIdserial(){
        return idserial;
    }

    public int getIdproducer(){
        return idproducer;
    }

    public void setIdserial(int idserial){
        this.idserial = idserial;
    }

    public void setIdproducer(int idproducer){
        this.idproducer = idproducer;
    }
}
