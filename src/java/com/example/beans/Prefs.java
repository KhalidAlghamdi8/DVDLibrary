/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author khali
 */
@Named
@SessionScoped
public class Prefs implements Serializable{
    private boolean title;
    private boolean releaseyear;
    private boolean genre;

    public Prefs() {
        this.title = true;
        this.releaseyear = true;
        this.genre = true;
    }

    public boolean isTitle() {
        return title;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public boolean isReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(boolean releaseyear) {
        this.releaseyear = releaseyear;
    }

    public boolean isGenre() {
        return genre;
    }

    public void setGenre(boolean genre) {
        this.genre = genre;
    }
    
}
