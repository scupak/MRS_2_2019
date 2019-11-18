/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.gui;

import java.io.IOException;
import mrs_2_2019.be.Movie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mrs_2_2019.gui.model.MovieModel;

/**
 *
 * @author pgn
 */
public class FXMLDocumentController implements Initializable
{
    private MovieModel movieModel;
    
    @FXML
    private Label label;
    @FXML
    private ListView<Movie> lstView;
    @FXML
    private TextField txtMovieSearch;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            movieModel = new MovieModel();
            lstView.setItems(movieModel.getAllMovies());
        }
    }    

    @FXML
    private void handleMovieSearch(KeyEvent event)
    {
        try
        {
            String query = txtMovieSearch.getText().trim();
            movieModel.search(query);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
