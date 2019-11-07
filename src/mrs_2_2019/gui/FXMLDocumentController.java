/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.gui;

import java.io.IOException;
import mrs_2_2019.be.Movie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import mrs_2_2019.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class FXMLDocumentController implements Initializable
{
    
    @FXML
    private Label label;
    @FXML
    private ListView<Movie> lstView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            MovieDAO movieDao = new MovieDAO();
            List<Movie> allMovies = movieDao.getAllMovies();    
            ObservableList<Movie> obsAllMovies =  FXCollections.observableArrayList(allMovies);
            lstView.setItems(obsAllMovies);
        } catch (IOException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
