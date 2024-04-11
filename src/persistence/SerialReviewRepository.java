package persistence;


import model.Review;
import model.Serial;
import model.User;
import service.DatabaseConnection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerialReviewRepository implements GenericRepository<Review> {
    private final DatabaseConnection db;

    public SerialReviewRepository(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void add(Review entity) {
        String sql = "INSERT INTO Serial_Review (id_serial, id_review) VALUES (?, ?)";

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, ((model.associative.Serial_Review) entity).getIdserial());
            stmt.setInt(2, entity.getIdreview());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Review get(int id) {

        return null;
    }

    @Override
    public ArrayList<Review> getAll() {
        return null;
    }

    @Override
    public void update(Review Entity) {

    }

    @Override
    public void delete(Review entity) {
        String sql = "DELETE FROM SerialReview WHERE id_review = ?";

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdreview());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
