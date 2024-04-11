package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Producer;

public class ProducerRepository implements GenericRepository<Producer> {
    private final Connection connection;

    public ProducerRepository(Connection connection){
        this.connection = connection;
    }


    @Override
    public void add(Producer producer) {
        String sql = "INSERT INTO PRODUCER (id_producer, name, age,nbofproduces, nbofawards) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, producer.getIdproducer());
            stmt.setString(2, producer.getName());
            stmt.setInt(3, producer.getAge());
            stmt.setInt(4, producer.getNbofproduces());
            stmt.setInt(5, producer.getNbofawards());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Producer get(int id) {
        String sql = "SELECT * FROM PRODUCER WHERE id_producer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int producerId = rs.getInt("id_producer");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int nbofproduces = rs.getInt("nbofproduces");
                int nbofawards = rs.getInt("nbofawards");

                return new Producer(producerId, name, age, nbofproduces, nbofawards);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Producer> getAll() {
        ArrayList<Producer> producers = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCER";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int producerId = rs.getInt("id_producer");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int nbofproduces = rs.getInt("nbofproduces");
                int nbofawards = rs.getInt("nbofawards");
                producers.add(new Producer(producerId, name, age, nbofproduces, nbofawards));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producers;

    }

    @Override
    public void update(Producer producer) {
        String sql = "UPDATE PRODUCER SET name = ?, age = ?, nbofproduces = ?, nbofawards = ? WHERE id_producer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, producer.getName());
            stmt.setInt(2, producer.getAge());
            stmt.setInt(3, producer.getNbofproduces());
            stmt.setInt(4, producer.getNbofawards());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Producer producer) {
        String sql = "DELETE FROM PRODUCER WHERE id_producer= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, producer.getIdproducer());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
