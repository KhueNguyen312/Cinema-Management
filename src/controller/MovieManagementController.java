/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Movies;
import model.MoviesDAO;

/**
 *
 * @author MyPC
 */
public class MovieManagementController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfImage;
    @FXML
    private JFXTextField tfDuration;
    @FXML
    private JFXTextField tfDirector;
    @FXML
    private JFXTextField tfLanguage;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField tfLinkTrailer;
    @FXML
    private JFXTextField tfCast;
    @FXML
    private JFXTextField tfGenre;
    @FXML
    private JFXDatePicker DatePickerRelease;
    @FXML
    private JFXTextField tfRate;
    @FXML
    private JFXTextField tfContent;
    @FXML
    private TableColumn<Movies, Integer> colSerial;
    @FXML
    private TableColumn<Movies, String> colName;
    @FXML
    private TableColumn<Movies, String> colImage;

    @FXML
    private TableColumn<Movies, Integer> colDuration;
    @FXML
    private TableColumn<Movies, String> colDirector;
    @FXML
    private TableColumn<Movies, String> colGenre;
    @FXML
    private TableColumn<Movies, String> colLanguage;
    @FXML
    private TableColumn<Movies, Date> colRelease;
    @FXML
    private TableColumn<Movies, String> colRate;
    @FXML
    private TableColumn<Movies, String> colContent;
    @FXML
    private TableView<Movies> tbMovies;
 @FXML
    private TableColumn<Movies, String> colCast;

 

   
    private MoviesDAO moviesDAO;
    ObservableList<Movies> list = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moviesDAO = new MoviesDAO();
        list = moviesDAO.getMovies();
        setValueFactory();
        tbMovies.setItems(list);
        autoFitTable(tbMovies);

    }

    void setValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<Movies, String>("name"));
        colImage.setCellValueFactory(new PropertyValueFactory<Movies, String>("image"));
        colDuration.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("duration"));
        colDirector.setCellValueFactory(new PropertyValueFactory<Movies, String>("director"));
        colCast.setCellValueFactory(new PropertyValueFactory<Movies, String>("cast"));
        colGenre.setCellValueFactory(new PropertyValueFactory<Movies, String>("genre"));
        colLanguage.setCellValueFactory(new PropertyValueFactory<Movies, String>("language"));
        colRate.setCellValueFactory(new PropertyValueFactory<Movies, String>("rated"));
        colContent.setCellValueFactory(new PropertyValueFactory<Movies, String>("content"));
        colRelease.setCellValueFactory(new PropertyValueFactory<Movies, Date>("release_date"));
    }
    // auto size in tableview
    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void autoFitTable(TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            @Override
            public void onChanged(Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
     @FXML
    private void TableClick(MouseEvent e) {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
            Movies movies = tbMovies.getSelectionModel().getSelectedItem();
            tfID.setText(String.valueOf(movies.getId()));
            tfName.setText(movies.getName());
            tfImage.setText(movies.getImage());
            tfDuration.setText(String.valueOf(movies.getDuration()));
            tfLinkTrailer.setText(movies.getLinkTrailer());
            tfDirector.setText(movies.getDirector());
            tfCast.setText(movies.getCast());
            tfGenre.setText(movies.getGenre());
            tfLanguage.setText(movies.getLanguage());
           tfRate.setText(movies.getRated());
           tfContent.setText(movies.getContent());
           DatePickerRelease.setValue(LocalDate.parse(movies.getRelease_date().toString()));
             int row = tbMovies.getSelectionModel().getSelectedIndex();
        }
    }
    
       @FXML
    private void btnDeleteClick(ActionEvent event) {
        Movies movies = tbMovies.getSelectionModel().getSelectedItem();
        moviesDAO.DeleteEmployee(movies.getId());
        int row = tbMovies.getSelectionModel().getSelectedIndex();
        moviesDAO = new MoviesDAO();
        list = moviesDAO.getMovies();
        setValueFactory();
        tbMovies.setItems(list);
           autoFitTable(tbMovies);
    }

    @FXML
    private void BtnNewClick(ActionEvent event) {
        tfID.setText(null);
        tfID.setDisable(true);
        tfName.setText("");
        tfImage.setText("");
        tfDuration.setText("");
        tfLinkTrailer.setText("");
        tfDirector.setText("");
        tfCast.setText("");
        tfGenre.setText("");
        tfLanguage.setText("");
        tfRate.setText("");
        tfContent.setText("");
        DatePickerRelease.setValue(LocalDate.now());
    }

    @FXML
    private void btnSaveClick(ActionEvent event) {
        Movies movies = new Movies();
        int id = 0;
        movies.setName(tfName.getText());
        movies.setImage(tfImage.getText());
        movies.setDuration(Integer.valueOf(tfDuration.getText()));
        movies.setLinkTrailer(tfLinkTrailer.getText());
        movies.setDirector(tfDirector.getText());
        movies.setCast(tfCast.getText());
        movies.setGenre(tfGenre.getText());
        movies.setLanguage(tfLanguage.getText());
        movies.setRated(tfRate.getText());
        movies.setContent(tfContent.getText());
        movies.setRelease_date(Date.valueOf(DatePickerRelease.getValue()));
        if (tfID.getText() == null) {
            id = list.size() + 1;
            movies.setId(id);
            moviesDAO.AddMovies(movies);
            list.add(movies);
        } else {
            id = Integer.parseInt(tfID.getText());
            movies.setId(id);
            moviesDAO.UpdateMovies(movies);
            for (Movies movie : list) {
                id = movie.getId();
                movie = movies;
            }
        }
        moviesDAO = new MoviesDAO();
        list = moviesDAO.getMovies();
        setValueFactory();
        tbMovies.setItems(list);
        autoFitTable(tbMovies);
    }
}
