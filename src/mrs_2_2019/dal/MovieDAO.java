/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mrs_2_2019.be.Movie;

/**
 *
 * @author pgn
 */
public class MovieDAO implements IMovieDao
{

    private static final String MOVIE_SOURCE = "data/movie_titles.txt";

    @Override
    public List<Movie> getAllMovies() throws DalException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(MOVIE_SOURCE))))
        {
            List<Movie> allMovies = new ArrayList<>();

            while (true)
            {
                String aLineOfText = br.readLine();
                if (aLineOfText == null)
                {
                    break;
                } else if (!aLineOfText.isEmpty())
                {
                    try
                    {
                        String[] arrMovie = aLineOfText.split(",");
                        int id = Integer.parseInt(arrMovie[0].trim()); //Jeg læser ID'et.
                        int year = Integer.parseInt(arrMovie[1].trim()); //Jeg læser årstal.
                        String title = arrMovie[2].trim(); //Jeg læser titlen.
                        // Add if commas in title, includes the rest of the string:
                        for (int i = 3; i < arrMovie.length; i++) //Loop will only run if the array has a length of 3+
                        {
                            title += "," + arrMovie[i];
                        }
                        Movie mov = new Movie(id, title, year);
                        allMovies.add(mov);
                    } catch (Exception e)
                    {
                        //Skip row
                    }
                }
            }
            return allMovies;
        } catch (IOException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws DalException
    {
        try
        {
            List<Movie> allMovies = getAllMovies();
            if (allMovies.remove(movie))
            {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(MOVIE_SOURCE))))
                {
                    for (Movie mov : allMovies)
                    {
                        bw.write(mov.getId() + "," + mov.getYear() + "," + mov.getTitle());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException ex)
        {
            throw new DalException();
        }
    }

    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        List<Movie> allMovies = getAllMovies();
        if (allMovies.remove(movie))
        {
            allMovies.add(movie);
            //Maybe sort list
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(MOVIE_SOURCE))))
            {
                for (Movie mov : allMovies)
                {
                    bw.write(mov.getId() + "," + mov.getYear() + "," + mov.getTitle());
                    bw.newLine();
                }
            }
        }
    }

    @Override
    public void writeAllMovies(List<Movie> allMovies, String fileName) throws IOException, ClassNotFoundException
    {
        File listFile = new File(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(listFile)))
        {
            oos.writeObject(allMovies);
            oos.flush();
        }

//        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(listFile)))
//        {
//            List<Movie> sameListAsMovie = (List<Movie>) ois.readObject();
//            for (Movie movie : sameListAsMovie)
//            {
//                System.out.println(movie);
//            }
//        }
    }

    public static void main(String[] args)
    {
        try
        {
            MovieDAO movieDao = new MovieDAO();
            List<Movie> allMovies = movieDao.getAllMovies();

            movieDao.writeAllMovies(allMovies, "data/moviesAsObjects.txt");
        } catch (IOException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done");
    }

    @Override
    public Movie createMovie(String title, int year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
