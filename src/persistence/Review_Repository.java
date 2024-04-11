package persistence;

import model.Rating;
import model.User;
import service.DatabaseConnection;
import model.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Review_Repository implements GenericRepository<Review> {
    private final DatabaseConnection db;

    public Review_Repository(DatabaseConnection db){
        this.db = db;
    }



    @Override
    public void add(Review entity) {
        String sql = "INSERT INTO review (id_review,review, id_user, id_rating) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, entity.getIdreview());
            stmt.setString(2, entity.getReview());
            stmt.setInt(3, entity.getUser().getId());
            stmt.setInt(4, entity.getRating().getIdrating());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setIdreview(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Review get(int id) {
        String sql = "SELECT * FROM review WHERE id_review = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id_review = rs.getInt("id_review");
                String review = rs.getString("review");
                int id_user = rs.getInt("id_user");
                int id_rating = rs.getInt("id_rating");

                UserRepository userRepository = new UserRepository(db);
                User user = userRepository.get(id_user);

                RatingRepository ratingRepository = new RatingRepository(db);
                Rating rating = ratingRepository.get(id_rating);



                return new Review(id_review, review, user, rating);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Review> getAll() {
        String sql = "SELECT * FROM review";

        ArrayList<Review> reviews = new ArrayList<>();
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_review = rs.getInt("id_review");
                String review = rs.getString("review");
                int id_user= rs.getInt("id_user");
                int id_rating = rs.getInt("id_rating");

                UserRepository userRepository = new UserRepository(db);
                User user = userRepository.get(id_user);

                RatingRepository ratingRepository = new RatingRepository(db);
                Rating rating = ratingRepository.get(id_rating);



                Review revi = new Review(id_review, review, user, rating);
                reviews.add(revi);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviews;

    }

    @Override
    public void update(Review entity) {
        String sql = "UPDATE review SET review = ?, id_user = ?, id_rating = ? WHERE id_review = ?";

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);

            stmt.setString(2, entity.getReview());
            stmt.setInt(3, entity.getUser().getId());
            stmt.setInt(4, entity.getRating().getIdrating());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Review entity) {
        String sql = "DELETE FROM review WHERE id_review = ?";

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdreview());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
