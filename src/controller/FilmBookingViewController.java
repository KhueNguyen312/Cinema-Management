/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;   
import javafx.fxml.FXMLLoader;
import model.Plan_CinemasDAO;
import model.Plan_cinemas;
import model.Movies;

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
    private Plan_CinemasDAO planCinema;
    private int movieID;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void SetInfo(Movies movie){
        Image img = new Image(movie.getImg());
        posterFilm.setImage(img);
        lbName.setText(movie.getName());
        lbDirector.setText(movie.getDirector());
        lbCast.setText(movie.getCast());
        lbGenre.setText(movie.getGenre());
        lbLang.setText(movie.getLanguage());
        lbRunningTime.setText(String.valueOf(movie.getDuaration()));
        lbRDate.setText(movie.getReleaseDate().toString());
        lbRated.setText(movie.getRate());
        movieID = movie.getID();
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
        JFXDialogLayout content = new JFXDialogLayout();
	content.setHeading(new Text("Movie schedule"));
	content.setPrefSize(1200, 800);
        content.setBody(createSchedurePane());
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        JFXDialog schedureModal = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.TOP);
        JFXButton button = new JFXButton("Close");
        button.setFont(new Font(15) );
        button.setStyle("-fx-background-color: #000000;-fx-font-weight: bold");
        button.setTextFill(Color.WHITE);
        button.setPrefSize(100, 50);
        button.setOnAction((ActionEvent event1) -> {
            schedureModal.close();
        });
        content.setActions(button);
        rootbookingPanel.getChildren().add(stackPane);
        GeneralFuntion.FitChildContent(stackPane);
        schedureModal.show();
        schedureModal.setOnDialogClosed(e->{
            rootbookingPanel.getChildren().remove(rootbookingPanel.getChildren().size()-1);
        });
    }
    public VBox createSchedurePane(){
        VBox SchedurePane = new VBox();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MM-dd");
        java.util.Date date = new java.util.Date();
        
        FlowPane selectedSchedulePane = new FlowPane();
        selectedSchedulePane.setHgap(5);
        selectedSchedulePane.setVgap(5);
        String formatDate = new SimpleDateFormat("yyyyMMdd").format(date);
        planCinema = new Plan_CinemasDAO();
        List<Plan_cinemas> listPlanCinema = planCinema.getListPlan("SELECT * FROM `plan_cinemas` WHERE show_date ="+formatDate+" and movie_id = "+ movieID );
        displaySchedure(listPlanCinema, selectedSchedulePane);
        
        FlowPane contentPane = new FlowPane();
        contentPane.setHgap(5);
        contentPane.setVgap(5);
        for(int i = 0;i< 14;i++){
            JFXButton btnDate = createInfoButton(formatter.format(date), 100, 50);
            setStatusButton(btnDate);
            String formatDate_1 = new SimpleDateFormat("yyyyMMdd").format(date);
            btnDate.setOnAction((event) -> {
                selectedSchedulePane.getChildren().clear();
                for(int j =0;j<14;j++){
                    contentPane.getChildren().get(j).setDisable(false);
                }
                btnDate.setDisable(true);
                List<Plan_cinemas> listSchedureOnDayPlay = planCinema.getListPlan("SELECT * FROM `plan_cinemas` WHERE show_date ="+formatDate_1+" and movie_id = "+ movieID );
                displaySchedure(listSchedureOnDayPlay,selectedSchedulePane);
                
            });
            
            contentPane.getChildren().add(btnDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
        }
        Label label = new Label();
        label.setText("Selected schedule");
        label.setStyle("-fx-font-weight: bold");
        label.setFont(new Font(40) );
        label.setTextFill(Color.BLACK);
        
        SchedurePane.getChildren().add(contentPane);
        SchedurePane.setSpacing(50);
        SchedurePane.getChildren().add(label);
        SchedurePane.getChildren().add(selectedSchedulePane);
        
        return SchedurePane;
    }
    public void setStatusButton(JFXButton button){
        button.setStyle("-fx-background-color: #F9F6EC");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-font-weight: bold"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #F9F6EC"));
    }
    public void displaySchedure(List<Plan_cinemas> listSchedure,FlowPane pane){
        if(listSchedure.isEmpty()){
            Label notifyLabel = new Label();
            notifyLabel.setText("Sorry there is no showtime on this day, please select another day");
            notifyLabel.setStyle("-fx-font-weight: bold");
            notifyLabel.setFont(new Font(40));
            notifyLabel.setTextFill(Color.BLACK);
            pane.getChildren().add(notifyLabel);
        }
        else{
            for(Plan_cinemas plan:listSchedure){
                JFXButton button = createInfoButton(plan.getTime_begin().toString(), 120, 60);
                setStatusButton(button);
                button.setOnAction((event) -> {
                    rootbookingPanel.getChildren().remove(rootbookingPanel.getChildren().size()-1);
                    //MainViewController.getInstance().createPage(moviesPane, "/view/Layout/SeatSelectionView.fxml");
                    FXMLLoader fxmlLoader = MainViewController.getInstance().createPage(moviesPane, "/view/Layout/SeatSelectionView.fxml");
                    fxmlLoader.<SeatSelectionViewController>getController().setStatusSeat(lbName.getText(),plan.getId());
                    fxmlLoader.<SeatSelectionViewController>getController().setInfo(posterFilm.getImage(),plan);
                });
                pane.getChildren().add(button);
            }
        }
    }
    public JFXButton createInfoButton(String text,int width,int height){
        JFXButton btnInfo = new JFXButton(text);
        btnInfo.setMinSize(width, height);
        btnInfo.setMaxSize(width, height);
        btnInfo.setWrapText(true);
        btnInfo.setFont(new Font(15) );
        btnInfo.setTextFill(Color.BLACK);
        btnInfo.setStyle("-fx-background-color: #F9F6EC");
        return btnInfo;
    }
}
