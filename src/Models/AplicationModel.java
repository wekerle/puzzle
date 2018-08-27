/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tibor.wekerle
 */
public class AplicationModel implements Serializable{
    private ArrayList<LevelModel> levels=new ArrayList<LevelModel>();
    private int maxSolvedLevel=13;

    public ArrayList<LevelModel> getLevels() 
    {
        return levels;
    }

    public void setLevels(ArrayList<LevelModel> levels) 
    {
        this.levels = levels;
    }

    public LevelModel getLevelById(int levelId) 
    {
        for(LevelModel level:levels)
        {
            if(level.getLevelId()==levelId)
            {
                return level;
            }
        }
        return null;
    }
    
    public int getMaxSolvedLevel()
    {
        return this.maxSolvedLevel;
    }
    
    public void setMaxSolvedLevel(int levelNr)
    {
        this.maxSolvedLevel=levelNr;
    }

    public LevelModel getLevelByNr(int levelNr) {
        for(LevelModel level:levels)
        {
            if(level.getLevelNumber()==levelNr)
            {
                return level;
            }
        }
        return null;
    }
    
}
