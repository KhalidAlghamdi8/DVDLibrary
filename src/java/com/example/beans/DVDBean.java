/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import com.example.entities.Item;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import com.example.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author khali
 */
@Named(value = "dvdBean") 
@SessionScoped
public class DVDBean implements Serializable {

    private String title="";
    private Long releaseyear;
    private String genre="";
    private Item item;
    
    @Inject
    ItemEJB itembean;
    

    
    /**
     * Creates a new instance of DVDBean
     */
    public DVDBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Long releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ItemEJB getItembean() {
        return itembean;
    }

    public void setItembean(ItemEJB itembean) {
        this.itembean = itembean;
    }
    
    
    
    public String addDVD() throws PreexistingEntityException, Exception{
    item = new Item (itembean.count()+1,title,releaseyear.toString(),genre);
        System.out.println("Count:" +itembean.count());
        itembean.addItem(item);
        title="";
        genre="";
        return "index";
    }
}
