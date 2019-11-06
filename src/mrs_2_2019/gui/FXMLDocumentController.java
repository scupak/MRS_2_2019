/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrs_2_2019.gui;

import mrs_2_2019.be.Movie;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
        ArrayList<Movie> allMovies = new ArrayList<>();
        
        allMovies.add(new Movie(1, "Det forsømte forår", 1983));
        
        //GET DATA FROM FILE
        
        ObservableList<Movie> obsAllMovies =  FXCollections.observableArrayList(allMovies);
        lstView.setItems(obsAllMovies);
    }    
    
}
