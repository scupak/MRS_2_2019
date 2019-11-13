/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.be;

/**
 *
 * @author pgn
 */
public class Rating
{

    private final int movieId;
    private final int userId;
    private int rating;

    public Rating(int movieId, int userId, int rating)
    {
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public int getMovieId()
    {
        return movieId;
    }

    public int getUserId()
    {
        return userId;
    }

}
