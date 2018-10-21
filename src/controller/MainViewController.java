/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class MainViewController implements Initializable {

    @FXML
    private AnchorPane btnNowShowing;
    @FXML
    private AnchorPane rootPanel;
    @FXML
    private Circle avatarCircle;
    @FXML
    private AnchorPane btnComingSoon;
    @FXML
    private AnchorPane btnAccountMng;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image("/Imgs/avatar_1.JPG");
	avatarCircle.setFill(new ImagePattern(img));
        try {
            loadForTheFirst();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void loadForTheFirst() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Layout/NowShowingView.fxml"));
        FitChildContent(pane);
	rootPanel.getChildren().setAll(pane);
    }
    @FXML
    private void loadNowShowing(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Layout/NowShowingView.fxml"));
        FitChildContent(pane);
	rootPanel.getChildren().setAll(pane);
    }

    @FXML
    private void loadComingSoon(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Layout/ComingSoonView.fxml"));
	rootPanel.getChildren().setAll(pane);
    }

    @FXML
    private void loadAccountMng(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Layout/AccountManagement.fxml"));
	rootPanel.getChildren().setAll(pane);
    }
    private void FitChildContent(Node child){
        AnchorPane.setBottomAnchor(child, 0.0);
        AnchorPane.setTopAnchor(child, 0.0);
        AnchorPane.setLeftAnchor(child, 0.0);
        AnchorPane.setRightAnchor(child, 0.0);
    }
}
