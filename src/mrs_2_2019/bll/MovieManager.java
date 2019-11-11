/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mrs_2_2019.be.Movie;
import mrs_2_2019.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class MovieManager
{

    private MovieDAO movieDao;

    public MovieManager()
    {
        movieDao = new MovieDAO();
    }

    public List<Movie> getAllMovies() throws IOException
    {
        return movieDao.getAllMovies();
    }

    public List<Movie> search(String query) throws IOException
    {
        List<Movie> searchBase = movieDao.getAllMovies();
        List<Movie> result = new ArrayList<>();

        for (Movie movie : searchBase)
        {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(movie);
            }
        }
        return result;
    }

}
