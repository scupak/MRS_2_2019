/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import mrs_2_2019.be.Rating;

/**
 *
 * @author pgn
 */
public class RatingDAO
{

    private static final String RATING_SOURCE = "data/ratings_rf.dat";

    public int getRating(int userId, int movieId) throws IOException
    {
        File ratingsSource = new File(RATING_SOURCE);
        try (RandomAccessFile raf = new RandomAccessFile(ratingsSource, "r"))
        {
            for (int i = 0; i < raf.length(); i = i + 12)
            {
                raf.seek(i);
                int mvId = raf.readInt();
                int usId = raf.readInt();
                if (userId == usId && movieId == mvId)
                {
                    return raf.readInt();
                }
            }
            throw new IllegalArgumentException("No rating found for userId " + userId + " and movieId " + movieId);
        }
    }

    public void createRating(int movieId, int userId, int rating) throws IOException
    {
        File ratingsSource = new File(RATING_SOURCE);
        try (RandomAccessFile raf = new RandomAccessFile(ratingsSource, "rw"))
        {
            long indexToWriteAt = getPositionForNewMovie(movieId, userId);
            byte[] restOfDocument = new byte[(int)(raf.length() - indexToWriteAt)];
            raf.seek(indexToWriteAt);
            raf.read(restOfDocument);
            raf.seek(indexToWriteAt);
            raf.writeInt(movieId);
            raf.writeInt(userId);
            raf.writeInt(rating);
            raf.write(restOfDocument);
        }
    }

    public static void main(String[] args) throws IOException
    {
        RatingDAO ratingdao = new RatingDAO();

        //mitigateFromTxtToBinary(ratingdao); ONLY call this method once!
        
        long oldTime = System.nanoTime();
        int rating = ratingdao.getRating(61810,13326);
        long newTime = System.nanoTime();
        
        long diff = newTime - oldTime;
        
        System.out.println("Time to do slow search: " + diff + " nano seconds");

        //TODO Implement better search.
        System.out.println("Rating is done");
    }

    private static void mitigateFromTxtToBinary(RatingDAO ratingdao) throws IOException, NumberFormatException
    {
        File ratings = new File("data/ratings.txt");
        String line = null;
        List<Rating> allRatings = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(ratings.toPath()))
        {
            while ((line = br.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    String[] arrRating = line.split(",");
                    int movieId = Integer.parseInt(arrRating[0].trim());
                    int userId = Integer.parseInt(arrRating[1].trim());
                    int rating = Integer.parseInt(arrRating[2].trim());
                    Rating r = new Rating(movieId, userId, rating);
                    allRatings.add(r);
                }
            }
        }

        allRatings.sort(new Comparator<Rating>()
        {

            @Override
            public int compare(Rating o1, Rating o2)
            {
                int dif = o1.getMovieId() - o2.getMovieId();
                if (dif == 0)
                {
                    return o1.getUserId() - o2.getUserId();
                } else
                {
                    return dif;
                }
            }

        });

        for (Rating rating : allRatings)
        {
            ratingdao.createRating(rating.getMovieId(), rating.getUserId(), rating.getRating());
        }
    }

    private long getPositionForNewMovie(int movieId, int userId)
    {
        //TODO Do this
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
