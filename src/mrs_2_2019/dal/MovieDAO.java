/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mrs_2_2019.be.Movie;

/**
 *
 * @author pgn
 */
public class MovieDAO
{

    private static final String MOVIE_SOURCE = "data/movie_titles.txt";

    public List<Movie> getAllMovies() throws FileNotFoundException, IOException
    {
        File file = new File(MOVIE_SOURCE);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<Movie> allMovies = new ArrayList<>();

        while (true)
        {
            String aLineOfText = br.readLine();
            if (aLineOfText == null)
            {
                break;
            } else
            {
                //TODO create movie from line of text!!!
                
            }
        }

        return allMovies;
    }

}
