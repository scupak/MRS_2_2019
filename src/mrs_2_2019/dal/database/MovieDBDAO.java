/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mrs_2_2019.be.Movie;
import mrs_2_2019.dal.DalException;
import mrs_2_2019.dal.IMovieDao;
import java.sql.PreparedStatement;

/**
 *
 * @author pgn
 */
public class MovieDBDAO implements IMovieDao
{

    private DatabaseConnector dbCon;

    public MovieDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }

    public static void main(String[] args) throws Exception
    {
        try
        {
        MovieDBDAO movieDao = new MovieDBDAO();
/*
        String txtInputTitle = "Frozen II',1983);DELETE FROM Movies;--";
        int inputYear = 2019;

        Movie movie = movieDao.createMovie(txtInputTitle, inputYear);
        System.out.println("Done inserting");
        */
        
        movieDao.deleteMovie(new Movie(4, "lol", 1999));
        

        List<Movie> allMovies = movieDao.getAllMovies();
        for (Movie allMovy : allMovies)
        {
            System.out.println(allMovy + " ID: " + allMovy.getId());
        }
        System.out.println("Done done!!");
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        
        
    }

    @Override
    public List<Movie> getAllMovies() throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Movies;";
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
                try (Connection con = dbCon.getConnection())
        {
            String sql = "DELETE FROM Movies WHERE ID =" + movie.getId();
            Statement st = con.createStatement();
            int affectedRows = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            
            if (affectedRows == 1)
            {
                
            }
            else{
            throw new DalException();
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
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
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Movies VALUES (?,?);";
            PreparedStatement st = con.prepareStatement(sql);
            
            
            
            
            
            int affectedRows = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 1)
            {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) // <-- Remember to do this!!
                {
                    int id = rs.getInt(1);
                    Movie mov = new Movie(id, title, year);
                    return mov;
                }
            }
            throw new DalException();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }

}
