package persistence;

import model.Producer;
import model.Movie;
import model.associative.Movie_Producer;
import service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements GenericRepository<Movie>{
    private final DatabaseConnection db;

    public MovieRepository(DatabaseConnection db) {
        this.db = db;
    }


    @Override
    public void add(Movie movie) {
        String sql = """
                     INSERT INTO movie (id_movie, name,year)
                     VALUES (?, ?, ?)
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, movie.getIdmovie());
            stmt.setString(2, movie.getName());
            stmt.setInt(3, movie.getYear());



            stmt.execute();


            for (Producer producer : movie.getProducerlist()) {
                addMovie_Producer(movie.getIdmovie(), producer.getIdproducer());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Movie get(int id) {
        String sql = """
                     SELECT id_movie, name, year 
                     FROM movie
                     WHERE id_movie = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Movie(
                        rs.getInt("id_movie"),
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
    public ArrayList<Movie> getAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = """
                     SELECT id_movie, name, year, genre
                     FROM movie
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_movie = rs.getInt("id_movie");
                String name = rs.getString("name");
                int year = rs.getInt("year");
                List<Producer> producers = getProducerForMovie(id_movie);
                movies.add(new Movie(id_movie, name, year, producers));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;

    }

    @Override
    public void update(Movie movie) {
        String sql = """
                 UPDATE movie
                 SET name = ?
                 SET year = ?
                 WHERE id_movie = ?
                 """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getIdmovie());
            stmt.setInt(3, movie.getYear());


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void delete(Movie movie) {
        String sql = "DELETE FROM MOVIE WHERE id_movie = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(sql)) {
            stmt.setInt(1, movie.getIdmovie());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void addMovie_Producer(int id_movie, int id_producer) {
        String sql = """
                     INSERT INTO movieproducer (ID_MOVIE, ID_PRODUCER)
                     VALUES (?, ?)
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id_movie);
            stmt.setInt(2, id_producer);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Producer> getProducerForMovie(int id_movie) {
        List<Producer> producers = new ArrayList<>();
        String sql = """
                 SELECT p.id_producer, p.name, p.age, p.nbofproduces, p.nbofawards
                 FROM producer p
                 JOIN movieproducer ba ON a.id_producer = ba.id_producer
                 WHERE ba.movie_id = ?
                 """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id_movie);
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
