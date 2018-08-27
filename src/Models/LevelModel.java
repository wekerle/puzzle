/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author tibor.wekerle
 */
public class LevelModel implements Serializable{
    private String imagePath;
    private int levelNumber;
    private int levelId;
    private int nrVertical;
    private int nrHorizontal;
    
    public LevelModel(String imagePath,int levelNumber, int levelId,int nrVertical, int nrHoriztal)
    {
        this.imagePath=imagePath;
        this.levelNumber=levelNumber;
        this.levelId=levelId;
        this.nrHorizontal=nrHoriztal;
        this.nrVertical=nrVertical;
    }

    public String getImagePath() 
    {
        return imagePath;
    }

    public int getLevelId() 
    {
        return levelId;
    }

    public int getLevelNumber() 
    {
        return levelNumber;
    }

    public int getNrVertical() {
        return nrVertical;
    }

    public void setNrVertical(int nrVertical) {
        this.nrVertical = nrVertical;
    }

    public int getNrHorizontal() {
        return nrHorizontal;
    }

    public void setNrHorizontal(int nrHorizontal) {
        this.nrHorizontal = nrHorizontal;
    }
    
}
