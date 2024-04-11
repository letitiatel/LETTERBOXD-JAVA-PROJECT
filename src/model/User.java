package model;

public class User {

    private String username;
    private int id_user;
    private String email;
    private String password;

    public User(int idUser, String username, String email, String password){}

    public User(String username, int id_user,String email,String password){
        this.username = username;
        this.id_user = id_user;
        this.email = email;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public int getId(){
        return id_user;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setEmail(String Email){
        this.email=email;
    }





}
