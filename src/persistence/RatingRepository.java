package persistence;

import service.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rating;

public class RatingRepository implements GenericRepository<Rating> {
    private final DatabaseConnection db;

    public RatingRepository(DatabaseConnection db){
        this.db = db;
    }

    @Override
    public void add(Rating entity) {
        String sql = "INSERT INTO RATING (id_rating, nota) VALUES (?, ?)";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getIdrating());
            stmt.setInt(2, entity.getNota());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Rating get(int id) {
        String sql = "SELECT * FROM RATING WHERE id_rating = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id_rating = rs.getInt("id_rating");
                int nota = rs.getInt("nota");

                return new Rating(id_rating, nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Rating> getAll() {
        ArrayList<Rating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM RATING";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_rating = rs.getInt("id_rating");
                int nota = rs.getInt("nota");

                ratings.add(new Rating(id_rating, nota ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;

    }

    @Override
    public void update(Rating entity) {
        String sql = "UPDATE RATING SET nota = ? WHERE id_rating = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getNota());

            stmt.setInt(2, entity.getIdrating());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Rating entity) {
        String sql = "DELETE FROM RATING WHERE id_rating = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getIdrating());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
