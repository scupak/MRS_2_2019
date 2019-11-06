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
public class Movie
{

    private final int id;
    private String title;
    private int year;

    public Movie(int id, String title, int year)
    {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    /**
     * Get the value of year
     *
     * @return the value of year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Set the value of year
     *
     * @param year new value of year
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId()
    {
        return id;
    }

}
