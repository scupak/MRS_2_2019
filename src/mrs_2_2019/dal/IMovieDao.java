/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.dal;

import java.io.IOException;
import java.util.List;
import mrs_2_2019.be.Movie;

/**
 *
 * @author pgn
 */
public interface IMovieDao
{
    Movie createMovie(String title, int year);
    
    void deleteMovie(Movie movie) throws IOException;

    List<Movie> getAllMovies() throws IOException;

    void updateMovie(Movie movie) throws IOException;

    void writeAllMovies(List<Movie> allMovies, String fileName) throws IOException, ClassNotFoundException;
    
}
