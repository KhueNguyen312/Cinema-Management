/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author stari
 */
public class Movies {
    private int id;
    private String name;
    private String image;
    private int duration;
    private String director;
    private String cast;
    private String genre;
    private String language;
    private Date release_date;
    private String rated;
    private String content;
    
    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getImg(){
        return image;
    }
    public void setImg(String img){
        this.image = img;
    }
    public int getDuaration(){
        return duration;
    }
    public void setDuration(int duaration){
        this.duration = duaration;
    }
    public String getDirector (){
        return director;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public String getCast(){
        return cast;
    }
    public void setCast(String cast){
        this.cast = cast;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String language){
        this.language = language;
    }
    public Date getReleaseDate(){
        return release_date;
    }
    public void setReleaseDate(Date rDate){
        this.release_date = rDate;
    }
    public String getRate(){
        return rated;
    }
    public void setRated(String rated){
        this.rated = rated;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}
