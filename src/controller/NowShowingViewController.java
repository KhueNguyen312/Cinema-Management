/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Movies;
import model.MoviesDAO;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class NowShowingViewController implements Initializable {

    @FXML
    private ScrollPane pnShowInfo;
    @FXML
    private AnchorPane rootPanel;

    /**
     * Initializes the controller class.
     */
    private MoviesDAO movies;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        movies = new MoviesDAO();
        List<Movies> listMovies = movies.getMovies();
        FlowPane container = new FlowPane();
        for( Movies movie:listMovies){
            VBox sampleFilm = CreateInfoFilm(movie.getName(), movie.getImg());
            container.setVgap(20);
            container.setHgap(50);
            container.setPrefWidth(pnShowInfo.getPrefWidth());
            container.getChildren().add(sampleFilm);
        }   
        //VBox sampleFilm2 = CreateInfoFilm("YOUR NAME", "/imgs/YourName.jpg");
        //container.getChildren().add(sampleFilm2);
        pnShowInfo.setContent(container);
        pnShowInfo.setPannable(true);
        
    }
    public VBox CreateInfoFilm(String name,String imgUrl){
        VBox InfoPanel = new VBox();
        Image img = new Image(imgUrl);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(250);
        imgView.setFitWidth(200);
        imgView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Layout/FilmBookingView.fxml"));
                AnchorPane pane = fxmlLoader.load();
                FilmBookingViewController nextView = fxmlLoader.<FilmBookingViewController>getController();
                nextView.SetInfo(imgUrl, name);
                GeneralFuntion.FitChildContent(pane);
                rootPanel.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(NowShowingViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        
        Label label = new Label(name);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", 18));
        InfoPanel.setPrefSize(200, 400);
        InfoPanel.getChildren().addAll(imgView,label);
        return InfoPanel;
    }
}
