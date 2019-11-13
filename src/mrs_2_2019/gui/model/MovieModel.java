/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mrs_2_2019.be.Movie;
import mrs_2_2019.bll.MovieManager;
import mrs_2_2019.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class MovieModel
{

    private ObservableList<Movie> allMovies;
    
    private MovieManager movieManager;

    public MovieModel() throws IOException
    {
        movieManager = new MovieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
    }

    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }

    public void search(String query) throws IOException
    {
        if (query.isEmpty())
        {
            allMovies.clear();
            allMovies.addAll(movieManager.getAllMovies());
        } else
        {
            allMovies.clear();
            allMovies.addAll(movieManager.search(query));
        }
    }

}
