package persistence;

import model.Producer;
import model.Serial;
import model.associative.Serial_Producer;
import service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerialRepository implements GenericRepository<Serial>{
    private final DatabaseConnection db;

    public SerialRepository(DatabaseConnection db) {
        this.db = db;
    }


    @Override
    public void add(Serial serial) {
        String sql = """
                     INSERT INTO serial (id_serial, name,year)
                     VALUES (?, ?, ?, ?)
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, serial.getIdserial());
            stmt.setString(2, serial.getName());
            stmt.setInt(3, serial.getYear());



            stmt.execute();


            for (Producer producer : serial.getProducerlist()) {
                addserial_Producer(serial.getIdserial(), producer.getIdproducer());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Serial get(int id) {
        String sql = """
                     SELECT id_serial, name, year
                     FROM serial
                     WHERE id_serial = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Serial(
                        rs.getInt("id_serial"),
                        rs.getString("name"),
                        rs.getInt("year"),



                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ArrayList<Serial> getAll() {
        ArrayList<Serial> serials = new ArrayList<>();
        String sql = """
                     SELECT id_serial, name, year
                     FROM serial
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_serial = rs.getInt("id_serial");
                String name = rs.getString("name");
                int year = rs.getInt("year");

                List<Producer> producers = getProducerForserial(id_serial);
                serials.add(new Serial(id_serial, name, year,  producers));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return serials;

    }

    @Override
    public void update(Serial serial) {
        String sql = """
                 UPDATE serial
                 SET name = ?
                 SET year = ?                
                 WHERE id_serial = ?
                 """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, serial.getName());
            stmt.setInt(2, serial.getIdserial());
            stmt.setInt(3, serial.getYear());


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void delete(Serial serial) {
        String sql = "DELETE FROM serial WHERE id_serial = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, serial.getIdserial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void addserial_Producer(int id_serial, int id_producer) {
        String sql = """
                     INSERT INTO serialproducer (ID_serial, ID_PRODUCER)
                     VALUES (?, ?)
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id_serial);
            stmt.setInt(2, id_producer);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Producer> getProducerForserial(int id_serial) {
        List<Producer> producers = new ArrayList<>();
        String sql = """
                 SELECT p.id_producer, p.name, p.age, p.nbofproduces, p.nbofawards
                 FROM producer p
                 JOIN serialproducer ba ON a.id_producer = ba.id_producer
                 WHERE ba.serial_id = ?
                 """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id_serial);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producer producer = new Producer(
                        rs.getInt("id_producer"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getInt("nbofproduces"),
                        rs.getInt("nbofawards")

                );
                producers.add(producer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return producers;
    }


}

