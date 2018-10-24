/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void SetInfo(String imgPath,String name){
        Image img = new Image(imgPath);
        posterFilm.setImage(img);
        lbName.setText(name);
    }
    AnchorPane PreviousScene,NextScene;    
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        if(PreviousScene == null){
            PreviousScene = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Layout/NowShowingView.fxml"));
            rootbookingPanel.getChildren().add(PreviousScene);//change setAll to add and use the child itself
            GeneralFuntion.FitChildContent(PreviousScene);
            PreviousScene.toFront();
        }
        
    }

    @FXML
    private void loadSchedure(ActionEvent event) throws IOException {
        if(NextScene == null){
            NextScene = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Layout/SeatSelectionView.fxml"));
            rootbookingPanel.getChildren().add(NextScene);//change setAll to add and use the child itself
            GeneralFuntion.FitChildContent(NextScene);
            NextScene.toFront();
        }
    }
    
}
