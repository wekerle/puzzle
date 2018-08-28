/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import Models.LevelModel;
import java.util.ArrayList;


//loveercase es trim a similarty setbe
//test similarity
/**
 *
 * @author Ronaldo
 */
public class DataCollector
{
    private ArrayList<LevelModel> levels=new ArrayList<LevelModel>();
    
    public DataCollector()
    {
        constructLevels();
    }
    
    private void constructLevels()
    {
        String imagePath1="/img/flip.jpg";
        LevelModel level1=new LevelModel(imagePath1, 1, 1,5,5);
        levels.add(level1);

        String imagePath2="/img/flip2.jpg";
        LevelModel level2=new LevelModel(imagePath2, 2, 2,3,4);
        levels.add(level2);
        
        String imagePath3="/img/flip3.jpg";
        LevelModel level3=new LevelModel(imagePath3, 3, 3,5,3);
        levels.add(level3);
        
        String imagePath4="/img/test1.jpg";
        LevelModel level4=new LevelModel(imagePath4, 4, 4,3,3);
        levels.add(level4);
        
        String imagePath5="/img/test2.jpg";
        LevelModel level5=new LevelModel(imagePath5, 5, 5,3,3);
        levels.add(level5);
        
        String imagePath6="";
        LevelModel level6=new LevelModel(imagePath6, 6, 6,3,3);
        levels.add(level6);
        
        String imagePath7="";
        LevelModel level7=new LevelModel(imagePath7, 7, 7,3,3);
        levels.add(level7);
        
        String imagePath8="";
        LevelModel level8=new LevelModel(imagePath8, 8, 8,3,3);
        levels.add(level8);
        
        String imagePath9="";
        LevelModel level9=new LevelModel(imagePath9, 9, 9,3,3);
        levels.add(level9);
        
        String imagePath10="";
        LevelModel level10=new LevelModel(imagePath10, 10, 10,3,3);
        levels.add(level10);
        
        String imagePath11="";
        LevelModel level11=new LevelModel(imagePath11, 11, 11,3,3);
        levels.add(level11);
        
        String imagePath12="";
        LevelModel level12=new LevelModel(imagePath12, 12, 12,3,3);
        levels.add(level12);
        
        String imagePath13="";
        LevelModel level13=new LevelModel(imagePath13, 13, 13,3,3);
        levels.add(level13);
        
        String imagePath14="";
        LevelModel level14=new LevelModel(imagePath14, 14, 14,3,3);
        levels.add(level14);
        
        String imagePath15="";
        LevelModel level15=new LevelModel(imagePath15, 15, 15,3,3);
        levels.add(level15);
        
        String imagePath16="";
        LevelModel level16=new LevelModel(imagePath16, 16, 16,3,3);
        levels.add(level16);
        
        String imagePath17="";
        LevelModel level17=new LevelModel(imagePath17, 17, 17,3,3);
        levels.add(level17);
        
        String imagePath18="";
        LevelModel level18=new LevelModel(imagePath18, 18, 18,3,3);
        levels.add(level18);
        
        String imagePath19="";
        LevelModel level19=new LevelModel(imagePath19, 19, 19,3,3);
        levels.add(level19);
        
        String imagePath20="";
        LevelModel level20=new LevelModel(imagePath20, 20, 20,3,3);
        levels.add(level20);                   
    }
    
    public ArrayList<LevelModel>  getLevels()
    {
        return levels;
    }
    
}
