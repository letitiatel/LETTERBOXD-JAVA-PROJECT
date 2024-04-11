package persistence;

import model.User;
import service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements GenericRepository<User>{
    private static DatabaseConnection db = null;


    public UserRepository(DatabaseConnection db){
        this.db = db;
    }

    @Override
    public void add(User entity) {
        String sql = """
                     INSERT INTO user(id_user, username, email, password)
                     VALUES (?, ?, ?, ?)
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getUsername());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, entity.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public User get(int id) {

        String sql = """
                     SELECT id_ser, username, email, password
                     FROM user
                     WHERE id_user = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    @Override
    public ArrayList<User> getAll() {
        String sql = """
                     SELECT id_user, username, email, password
                     FROM utilizator
                     """;

        ArrayList<User> users= new ArrayList<>();
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;

    }

    @Override
    public void update(User entity) {
        String sql = """
                     UPDATE user
                     SET username = ?, email = ?, password = ?
                     WHERE id_user = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getPassword());
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(User entity) {
        String sql = """
                     DELETE FROM user
                     WHERE id_user = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
