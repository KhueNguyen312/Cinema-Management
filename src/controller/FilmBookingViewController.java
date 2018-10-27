/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Date;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class FilmBookingViewController implements Initializable {

    @FXML
    private JFXButton btnBack;
    @FXML
    private AnchorPane rootbookingPanel;
    @FXML
    private ImageView posterFilm;
    @FXML
    private Label lbName;
    @FXML
    private JFXButton btnBooking;
    @FXML
    private Label lbDirector;
    @FXML
    private Label lbCast;
    @FXML
    private Label lbGenre;
    @FXML
    private Label lbRDate;
    @FXML
    private Label lbRunningTime;
    @FXML
    private Label lbLang;
    @FXML
    private Label lbRated;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void SetInfo(String imgPath,String name,String director,String cast,String genre,Date rDate,int runningTime,String lang,String rated){
        Image img = new Image(imgPath);
        posterFilm.setImage(img);
        lbName.setText(name);
        lbDirector.setText(director);
        lbCast.setText(cast);
        lbGenre.setText(genre);
        lbLang.setText(lang);
        lbRunningTime.setText(String.valueOf(runningTime));
        lbRDate.setText(rDate.toString());
        lbRated.setText(rated);
    }
    public AnchorPane moviesPane;    
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        AnchorPane tmp = MainViewController.getInstance().rootPanel;
        tmp.getChildren().remove(1, tmp.getChildren().size());
        if(tmp.getChildren().get(0) == null)
            MainViewController.getInstance().createPage(moviesPane, "/view/Layout/NowShowingView.fxml");
        else
            tmp.getChildren().get(0).toFront();
    }

    @FXML
    private void loadSchedure(ActionEvent event) throws IOException {
        MainViewController.getInstance().createPage(moviesPane, "/view/Layout/SeatSelectionView.fxml");
    }
}
