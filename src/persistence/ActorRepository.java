package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Actor;
import service.DatabaseConnection;

public class ActorRepository implements GenericRepository<Actor>{
    private final DatabaseConnection db;

    public ActorRepository(DatabaseConnection db){
        this.db = db;
    }



    @Override
    public void add(Actor entity) {
        String sql = "INSERT INTO ACTOR (id_actor, name, age, numberofplays, numberofawards) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getIdactor());
            stmt.setString(2, entity.getName());
            stmt.setInt(3, entity.getAge());
            stmt.setInt(4, entity.getNumberofplays());
            stmt.setInt(5, entity.getNumberofawards());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Actor get(int id) {
        String sql = "SELECT * FROM ACTOR WHERE id_actor = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id_actor = rs.getInt("id_actor");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int numberofplays = rs.getInt("numberofplays");
                int numberofawards = rs.getInt("numberofawards");

                return new Actor(id_actor, name, age, numberofplays, numberofawards);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Actor> getAll() {
        ArrayList<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM ACTOR";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_actor = rs.getInt("id_actor");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int numberofplays = rs.getInt("numberofplays");
                int numberofawards = rs.getInt("numberofawards");
                actors.add(new Actor(id_actor, name, age, numberofplays, numberofawards));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;


    }

    @Override
    public void update(Actor entity) {
        String sql = "UPDATE ACTOR SET name = ?, age = ?, numberofplays = ?, numberofawards = ? HERE id_actor = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getAge());
            stmt.setInt(3, entity.getNumberofplays());
            stmt.setInt(4, entity.getNumberofawards());
            stmt.setInt(5, entity.getIdactor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Actor entity) {
        String sql = "DELETE FROM ACTOR WHERE id_actor = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getIdactor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
