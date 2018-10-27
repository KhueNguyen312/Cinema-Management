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
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class MainViewController implements Initializable {

    @FXML
    private AnchorPane btnNowShowing;
    @FXML
    public AnchorPane rootPanel;
    @FXML
    private Circle avatarCircle;
    @FXML
    private AnchorPane btnComingSoon;
    @FXML
    private AnchorPane btnAccountMng;
    @FXML
    private Label labelUsername;
    
    public AnchorPane home;
    private static MainViewController instance;
    public MainViewController(){
        instance = this;
    }
    public static MainViewController getInstance(){
        return instance;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image("/Imgs/avatar_1.JPG");
	avatarCircle.setFill(new ImagePattern(img));
        createPage(home, "/view/Layout/NowShowingView.fxml");
    }    
    public void SetUser(String userID){
        this.labelUsername.setText(userID);
    }
    public AnchorPane getPanel(){
        return this.rootPanel;
    }
    @FXML
    private void loadNowShowing(MouseEvent event) throws IOException {
        if(MainViewController.getInstance().rootPanel.getChildren().get(0) == null)
            MainViewController.getInstance().createPage(home, "/view/Layout/NowShowingView.fxml");
        else
            MainViewController.getInstance().rootPanel.getChildren().get(0).toFront();
    }

    @FXML
    private void loadComingSoon(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Layout/ComingSoonView.fxml"));
	rootPanel.getChildren().setAll(pane);
    }

    @FXML
    private void loadAccountMng(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Layout/AccountManagement.fxml"));
	rootPanel.getChildren().setAll(pane);
    }
    public void setNode(Node node){
      //rootPanel.getChildren().remove(rootPanel.getChildren().size()-1);
        rootPanel.getChildren().add((Node)node);
        GeneralFuntion.FitChildContent(node);
        //effection
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    public FXMLLoader createPage(AnchorPane pane,String loc){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loc));
        try {
            pane = fxmlLoader.load();
            setNode(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader;
    }
}
