/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

//import com.dvdlibrary.model.DVDItem;
import com.example.entities.Item;
import com.example.exceptions.ItemException;
import com.example.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khalid
 */
@Named(value= "dvd")
@SessionScoped
public class DVDLibraryBean implements Serializable{
    private String title="";
    private Long releaseyear;
    private String genre="";
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    private  ArrayList genreList = null;
    private String newGenre;
    private List dvdCollection; 
    private boolean DVDCollectionUpdated;

    public boolean isDVDCollectionUpdated() {
        return DVDCollectionUpdated;
    }

    public void setDVDCollectionUpdated(boolean DVDCollectionUpdated) {
        this.DVDCollectionUpdated = DVDCollectionUpdated;
    }


    
    @Inject
    ItemEJB itembean;
    private Item item;
    
 public DVDLibraryBean (){}

    public ArrayList getGenreList() {
        if (genreList == null) {
            genreList = new ArrayList <String>();
            List freshGenres = itembean.getGenres();
            Iterator g = freshGenres.iterator();
                while (g.hasNext()){
                String item = (String) g.next();
                SelectItem n = new SelectItem(item, item);
                genreList.add(n);
                }
            }
        return genreList;
}
 
    public void addGenre (String newGenre){
    if ( newGenre.equals(""))
        return ;
    Iterator g = genreList.iterator();
    while (g.hasNext()){
        SelectItem item = (SelectItem)g.next();
        if (item.getValue().equals(newGenre))
            return;
    }
        SelectItem newItem = new SelectItem(newGenre, newGenre);
        genreList.add(newItem);
    }
 public String login(){

 return "home";
 }

    public Long getCurrentYear() {
        Calendar rightNow = Calendar.getInstance();
        Long x = new Long(rightNow.get(Calendar.YEAR));
        return x;
    }
    
    public List getDvdCollection (){
    if (this.dvdCollection == null){
    this.dvdCollection = itembean.getAllItems();
    }
    return dvdCollection;
    }
    
    public int getSize (){
    return getDvdCollection().size();
    }

    public String getNewGenre() {
        return newGenre;
    }

    public void setNewGenre(String newGenre) {
        this.newGenre = newGenre;
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
    
    public String addDVD() throws PreexistingEntityException, Exception {
        if (!newGenre.equals("")){
            addGenre(newGenre);
            genre =newGenre;
        }


        
        item = new Item (itembean.count()+1,title,releaseyear.toString(),genre);
        System.out.println("Count:" +itembean.count());
       // boolean result = addDVD(item);
         if (itembean.addItem(item)) {
            setDVDCollectionUpdated(true);
            this.dvdCollection = null;
        }
        title="";
        genre="";
        releaseyear = getCurrentYear();
        setNewGenre("");
        return "index";
        
//            item = new Item (itembean.count()+1,title,releaseyear.toString(),genre);
//        System.out.println("Count:" +itembean.count());
//        itembean.addItem(item);
//        title="";
//        genre="";
//        return "index";
//    }
    
}
}

