/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mrs_2_2019.be.Movie;
import mrs_2_2019.dal.DalException;
import mrs_2_2019.dal.IMovieDao;

/**
 *
 * @author pgn
 */
public class MovieDBDAO implements IMovieDao
{

    public static void main(String[] args) throws DalException
    {
        MovieDBDAO movieDao = new MovieDBDAO();

        List<Movie> allMovies = movieDao.getAllMovies();
        for (Movie allMovy : allMovies)
        {
            System.out.println(allMovy);
        }

    }

    @Override
    public List<Movie> getAllMovies() throws DalException
    {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName("MRS_2019");
        dataSource.setUser("CSe19A_40");
        dataSource.setPassword("CSe19A_40"); //Is this good for public GitHub?
        dataSource.setServerName("10.176.111.31");

        try (Connection con = dataSource.getConnection())
        {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Movie> allMovies = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                Movie mov = new Movie(id, title, year);
                allMovies.add(mov);
            }
            return allMovies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMovie(Movie movie) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeAllMovies(List<Movie> allMovies, String fileName) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie createMovie(String title, int year) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
