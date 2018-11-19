/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.util.Date;
import java.sql.Date;

/**
 *
 * @author stari
 */
public class Movies {
    private int id;
    private String name;
    private String image;
    private int duration;
    private String linkTrailer;
    private String director;
    private String cast;
    private String genre;
    private String language;
    private Date release_date;
    private String rated;
    private String content;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getDuration() {
        return duration;
    }

    public String getLinkTrailer() {
        return linkTrailer;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public String getRated() {
        return rated;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLinkTrailer(String linkTrailer) {
        this.linkTrailer = linkTrailer;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setContent(String content) {
        this.content = content;
    }
   
}
