/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class MainViewController implements Initializable {

    @FXML
    private JFXButton btnNowShowing;
    @FXML
    public AnchorPane rootPanel;
    @FXML
    private Circle avatarCircle;
    @FXML
    private JFXButton btnComingSoon;
    @FXML
    private Label labelUsername;
    
    public AnchorPane home;
    private static MainViewController instance;
    @FXML
    private JFXButton btnAccountMng1;
    @FXML
    private JFXNodesList nodeListMng;
    private int expander = 0;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
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
        CreateManagementMenu();
    }    
    public void SetUser(String userName,int userID){
        this.labelUsername.setText(userName);
        this.userID = userID;
    }
    public AnchorPane getPanel(){
        return this.rootPanel;
    }

    public void setNode(Node node){
        rootPanel.getChildren().add((Node)node);
        GeneralFuntion.FitChildContent(node);
        //effection
//        FadeTransition ft = new FadeTransition(Duration.millis(1000));
//        ft.setNode(node);
//        ft.setFromValue(0.1);
//        ft.setToValue(1);
//        ft.setCycleCount(1);
//        ft.setAutoReverse(false);
//        ft.play();
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
    public void CreateManagementMenu(){
        JFXButton btnManagement = CreateButton("Management");
        btnManagement.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView mngIcon = new MaterialDesignIconView(MaterialDesignIcon.BRIEFCASE_DOWNLOAD);
        mngIcon.setId(".glyph-icon");
        btnManagement.setGraphic(mngIcon);
        btnManagement.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(expander == 0)
                {
                    MaterialDesignIconView mngIcon = new MaterialDesignIconView(MaterialDesignIcon.BRIEFCASE_UPLOAD);
                    mngIcon.setId(".glyph-icon");
                    btnManagement.setGraphic(mngIcon);
                    expander = 1;
                }
                else{
                    MaterialDesignIconView mngIcon = new MaterialDesignIconView(MaterialDesignIcon.BRIEFCASE_DOWNLOAD);
                    mngIcon.setId(".glyph-icon");
                    btnManagement.setGraphic(mngIcon);
                    expander = 0;
                }
            }
        });
   
        JFXButton btnMovieManagement = CreateButton("Movie Management");
        btnMovieManagement.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView movieMngIcon = new MaterialDesignIconView(MaterialDesignIcon.FILM);
        movieMngIcon.setId(".glyph-icon");
        btnMovieManagement.setGraphic(movieMngIcon);
        
        JFXButton btnAccountManagement = CreateButton("Account Management");
        btnAccountManagement.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView accountMngIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_BOX);
        accountMngIcon.setId(".glyph-icon");
        btnAccountManagement.setGraphic(accountMngIcon);
        
        JFXButton btnBillManagement = CreateButton("Bill Management");
        btnBillManagement.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView billIcon = new MaterialDesignIconView(MaterialDesignIcon.CLIPBOARD_TEXT);
        billIcon.setId(".glyph-icon");
        btnBillManagement.setGraphic(billIcon);
        
        JFXButton btnRoomManagement = CreateButton("Room Management");
        btnRoomManagement.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView roomIcon = new MaterialDesignIconView(MaterialDesignIcon.PRESENTATION_PLAY);
        roomIcon.setId(".glyph-icon");
        btnRoomManagement.setGraphic(roomIcon);
        
        nodeListMng.addAnimatedNode(btnManagement);
        nodeListMng.addAnimatedNode(btnMovieManagement);
        nodeListMng.addAnimatedNode(btnAccountManagement);
        nodeListMng.addAnimatedNode(btnBillManagement);
        nodeListMng.addAnimatedNode(btnRoomManagement);
        
    }
    public JFXButton CreateButton(String text){
        JFXButton btn = new JFXButton(text);
        btn.setMinSize(300, 70);
        btn.setMaxSize(300, 70);
        btn.setAlignment(Pos.BASELINE_LEFT);
        btn.setWrapText(true);
        btn.setFont(new Font(20) );
        btn.setTextFill(Color.WHITE);
        return btn;
    }
    @FXML
    private void loadNowShowing(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Layout/NowShowingView.fxml"));
	rootPanel.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }

    @FXML
    private void loadComingSoon(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Layout/ComingSoonView.fxml"));
	rootPanel.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }

    @FXML
    private void loadAccountMng(ActionEvent event) throws IOException {
        //AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Layout/AccountManagement.fxml"));
	//rootPanel.getChildren().setAll(pane);
    }
}
