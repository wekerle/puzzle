/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import Models.PuzzlePiece;
import Models.ToothHeightsModel;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author tibor.wekerle
 */
public class DataCollector {
    private Boolean[][] verticalTooths;
    private Boolean[][] horizontalTooths;
    private double nordSudToothHeight=0;
    private double estWestToothHeight=0;
    private int testCounter=1;
    
    public ArrayList<PuzzlePiece> getPuzzlePieces(Image image,int nrVertical, int nrHorizontal){
        initVerticalTooths(nrVertical,nrHorizontal);
        initOrizontalTooths(nrVertical,nrHorizontal);
                
        ArrayList<PuzzlePiece> result =new ArrayList<PuzzlePiece>();
        
        double width=image.getWidth()/nrHorizontal;
        double height=image.getHeight()/nrVertical;
        
        initNordSudToothHeight(width,height);
        initEstWestToothHeight(width,height);
               
        for(int i=0;i<nrHorizontal;i++){
            for(int j=0;j<nrVertical;j++){
                ImageView imageView = new ImageView(image);                 
                if(testCounter==5){                  
                    int x=0;
                }
                testCounter++;
                clipPuzzlePiece(imageView, width, height,i,j,nrVertical,nrHorizontal);                                                            
                PuzzlePiece puzzlePiece=new PuzzlePiece(IdGenerator.getNewId(),getToothHeights(i,j),imageView,width,height);
                
                result.add(puzzlePiece);
            }
        }
        
        return result;
    }
    
    private ToothHeightsModel getToothHeights(int i, int j){
        ToothHeightsModel result=new ToothHeightsModel();
         if(j!=0){
            if(!verticalTooths[i][j-1]){
                
            }else{
                result.setNordToothHeight(nordSudToothHeight);
            }
        }
       // if(j!=nrVertical-1){
       // }
        if(i!=0){
            if(!horizontalTooths[i-1][j]){
                
            }else{
                result.setEstToothHeight(estWestToothHeight);
            }
        }
        return result;
    }
        
    private void initVerticalTooths(int nrVertical,int nrHorizontal){
        verticalTooths=new Boolean[nrVertical][nrHorizontal-1];
        
        for(int i=0;i<nrVertical;i++){
            for(int j=0;j<nrHorizontal-1;j++){
                boolean bool = new Random().nextBoolean();
                verticalTooths[i][j]=bool;
                verticalTooths[i][j]=false;
            }
        }   
       /* verticalTooths[0][0]=true;
        verticalTooths[0][1]=true;
        
        verticalTooths[1][0]=true;
        verticalTooths[1][1]=true;
        
        verticalTooths[2][0]=true;
       verticalTooths[2][1]=true;*/
    }
    
    private void initOrizontalTooths(int nrVertical,int nrHorizontal){
        horizontalTooths=new Boolean[nrVertical-1][nrHorizontal];
        
        for(int i=0;i<nrVertical-1;i++){
            for(int j=0;j<nrHorizontal;j++){
                boolean bool = new Random().nextBoolean();
                horizontalTooths[i][j]=bool;
                horizontalTooths[i][j]=false;
            }
        }
        
      /*  horizontalTooths[0][0]=false;
        horizontalTooths[0][1]=true;
        horizontalTooths[0][2]=true;
        
        horizontalTooths[1][0]=false;
        horizontalTooths[1][1]=false;
        horizontalTooths[1][2]=true;*/
        
    }
    
    private void clipPuzzlePiece(ImageView imageView, double width, double height,int i, int j,int nrVertical, int nrHorizontal){  
        Rectangle r = new Rectangle();
        r.setWidth(width);
        r.setHeight(height);
                
        //Rectangle2D r2 = new Rectangle2D(i*width,j*height,width,height);
       //  imageView.setViewport(r2);
              
      // double toothSouthHeight=0;
      // double toothNordHeight=0;
       double toothEstHeight=0;
       double tootWestHeight=0;
       
       
        Shape shape=null;
        
       // double verticalOffset=0;       
      //  double horizontalOffset=0;
        double verticalOffset=getVerticalOffset(i,j);       
        double horizontalOffset=getHorizontalOffset(i,j);
        double addtionalHight=0;
        double addtionalWidth=0;
        if(j!=0){
            CubicCurve toothN = new CubicCurve();                       
            if(!verticalTooths[i][j-1]){    
                toothN.setStartX(width/2+width/8);
                toothN.setStartY(0.0f);
                toothN.setControlX1(width/2+width/1.75);
                toothN.setControlY1(height/2);
                toothN.setControlX2(width/2-width/1.75);
                toothN.setControlY2(height/2);
                toothN.setEndX(width/2-width/8);
                toothN.setEndY(0.0f);
                
               shape = Shape.subtract(r,toothN);               
            }else{
              //  verticalOffset=37.5;
                addtionalHight+=37.5;
                r.setX(horizontalOffset);
                r.setY(0+verticalOffset);              
                toothN.setStartX(width/2+width/8+horizontalOffset);
                toothN.setStartY(0+verticalOffset);
                toothN.setControlX1(width/2+width/1.75+horizontalOffset);
                toothN.setControlY1(-height/2+verticalOffset);
                toothN.setControlX2(width/2-width/1.75+horizontalOffset);
                toothN.setControlY2(-height/2+verticalOffset);
                toothN.setEndX(width/2-width/8+horizontalOffset);
                toothN.setEndY(0+verticalOffset);
                
                shape = Shape.union(r,toothN);               
            }                      
        }
               
        if(j!=nrVertical-1){
            CubicCurve toothS = new CubicCurve();            
            if(verticalTooths[i][j]){
                r.setX(horizontalOffset);
                toothS.setStartX(width/2+width/8+horizontalOffset);
                toothS.setStartY(height+verticalOffset);
                toothS.setControlX1(width/2+width/1.75+horizontalOffset);
                toothS.setControlY1(height-height/2+verticalOffset);
                toothS.setControlX2(width/2-width/1.75+horizontalOffset);
                toothS.setControlY2(height-height/2+verticalOffset);
                toothS.setEndX(width/2-width/8+horizontalOffset);
                toothS.setEndY(height+verticalOffset);
                
                if(shape==null){
                    shape = Shape.subtract(r, toothS);
                }else{
                    shape = Shape.subtract(shape, toothS);
                }
            }else{                 
                addtionalHight+=37.5;
                toothS.setStartX(width/2+width/8);
                toothS.setStartY(height+verticalOffset);
                toothS.setControlX1(width/2+width/1.75);
                toothS.setControlY1(height+height/2+verticalOffset);
                toothS.setControlX2(width/2-width/1.75);
                toothS.setControlY2(height+height/2+verticalOffset);
                toothS.setEndX(width/2-width/8);
                toothS.setEndY(height+verticalOffset);
                        
                if(shape==null){
                    shape = Shape.union(r, toothS);
                }else{
                    shape = Shape.union(shape, toothS);
                }
            }
        }
                        
        if(i!=0){
            CubicCurve toothE = new CubicCurve();
            if(!horizontalTooths[i-1][j]){
                toothE.setStartX(0);
                toothE.setStartY(height/2+height/8);                
                toothE.setControlX1(width/2);
                toothE.setControlY1(height/2+height/1.75);
                toothE.setControlX2(width/2);
                toothE.setControlY2(height/2-height/1.75);                
                toothE.setEndX(0);
                toothE.setEndY(height/2-height/8);

                if(shape==null){
                    shape = Shape.subtract(r, toothE);
                }else{
                    shape = Shape.subtract(shape, toothE);
                }
            }else{        
                addtionalWidth+=37.5;
              //  horizontalOffset=37.5;
                r.setX(37.5);
                toothE.setStartX(37.5);
                toothE.setStartY(height/2+height/8+verticalOffset);                
                toothE.setControlX1(-width/2+37.5);
                toothE.setControlY1(height/2+height/1.75+verticalOffset);
                toothE.setControlX2(-width/2+37.5);
                toothE.setControlY2(height/2-height/1.75+verticalOffset);                
                toothE.setEndX(37.5);
                toothE.setEndY(height/2-height/8+verticalOffset);

                if(shape==null){
                        shape = Shape.union(r, toothE);
                }else{
                    shape = Shape.union(shape, toothE);
                }
            }            
        }
        
        if(i!=nrHorizontal-1){
            CubicCurve toothV = new CubicCurve();
            if(horizontalTooths[i][j]){
               toothV.setStartX(width+horizontalOffset);
               toothV.setStartY(height/2+height/8+addtionalHight);                
               toothV.setControlX1(width/2+horizontalOffset);
               toothV.setControlY1(height/2+height/1.75+addtionalHight);
               toothV.setControlX2(width/2+horizontalOffset);
               toothV.setControlY2(height/2-height/1.75+addtionalHight);                
               toothV.setEndX(width+horizontalOffset);
               toothV.setEndY(height/2-height/8+addtionalHight);
               r.setX(horizontalOffset);
                if(shape==null){
                    shape = Shape.subtract(r, toothV);
                }else{
                    shape = Shape.subtract(shape, toothV);
                }
            }else{
               addtionalWidth+=37.5;
               toothV.setStartX(width+horizontalOffset);
               toothV.setStartY(height/2+height/8);                
               toothV.setControlX1(width+width/2+horizontalOffset);
               toothV.setControlY1(height/2+height/1.75);
               toothV.setControlX2(width+width/2+horizontalOffset);
               toothV.setControlY2(height/2-height/1.75);                
               toothV.setEndX(width+horizontalOffset);
               toothV.setEndY(height/2-height/8);
               
               if(shape==null){
                    shape = Shape.union(r, toothV);
                }else{
                    shape = Shape.union(shape, toothV);
                }
            }            
        }
      
        Rectangle2D r2 = new Rectangle2D(i*width,j*height-verticalOffset,width,height+addtionalHight);
      //  imageView.setViewport(r2);
       // Rectangle2D r3 = new Rectangle2D(i*width-horizontalOffset,j*height,width+addtionalWidth,height);
       // imageView.setViewport(r3);
        
        Rectangle2D viewPort = new Rectangle2D(i*width-horizontalOffset,j*height-verticalOffset,width+addtionalWidth,height+addtionalHight);
        imageView.setViewport(viewPort);
        
        //if(testCounter!=6)
        imageView.setClip(shape); 
    }
    
    private double getVerticalOffset(int i, int j){
        if(j!=0){
            if(verticalTooths[i][j-1]){
                return 37.5;
            }
        }
        return 0;
    }
    
    private double getHorizontalOffset(int i, int j){
        if(i!=0){
            if(horizontalTooths[i-1][j]){
                return 37.5;
            }
        }
        return 0;
    }
    
    private void initNordSudToothHeight(double width, double height) {
        CubicCurve tooth = new CubicCurve();
        tooth.setStartX(width/2+width/8);
        tooth.setStartY(0.0f);
        tooth.setControlX1(width/2+width/1.75);
        tooth.setControlY1(height/2);
        tooth.setControlX2(width/2-width/1.75);
        tooth.setControlY2(height/2);
        tooth.setEndX(width/2-width/8);
        tooth.setEndY(0.0f);
        
        nordSudToothHeight=tooth.getLayoutBounds().getHeight();
      //  nordSudToothHeight-=0.5;
    }

    private void initEstWestToothHeight(double width, double height) {
       CubicCurve tooth = new CubicCurve();
        tooth.setStartX(width);
        tooth.setStartY(height/2+height/8);                
        tooth.setControlX1(width+width/2);
        tooth.setControlY1(height/2+height/1.75);
        tooth.setControlX2(width+width/2);
        tooth.setControlY2(height/2-height/1.75);                
        tooth.setEndX(width);
        tooth.setEndY(height/2-height/8);
        
        estWestToothHeight=tooth.getLayoutBounds().getWidth();
        //estWestToothHeight=35;
    }
}
