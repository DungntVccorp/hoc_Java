package com.dung.ep1;

public class filmOBJ {
    
    
    public String filmName = "";
    public String filmDescription = "";
    
    
    
    
    public filmOBJ(String filmName,String filmDesctiption){
        // giống hàm init trong objective C
        this.filmName = filmName;
        this.filmDescription = filmDesctiption;
    }
    
    public filmOBJ(){
        this.filmName = "test";
        this.filmDescription = "test";
    }
    
}
