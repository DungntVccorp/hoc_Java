/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dung.ep1;

/**
 *
 * @author nguyendung
 */
public class FilmExtension extends filmOBJ {
    public String filmType;
    
    
    public FilmExtension(String fm,String fd,String ft){
        this.filmName = fm;
        this.filmDescription = fd;
        this.filmType = ft;
    }
    
    public void printFull(){
        System.out.println(this.filmName + this.filmDescription + this.filmType);
    }
    
}
