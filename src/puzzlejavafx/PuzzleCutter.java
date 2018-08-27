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
public class PuzzleCutter {
    private Boolean[][] verticalTooths;
    private Boolean[][] horizontalTooths;
    private double nordSudToothHeight=0, estWestToothHeight=0;
    private int nrVertical,nrHorizontal;
    
    public PuzzleCutter(int nrVertical, int nrHorizontal){
        this.nrHorizontal=nrHorizontal;
        this.nrVertical=nrVertical;
        initVerticalTooths(nrVertical,nrHorizontal);
        initOrizontalTooths(nrVertical,nrHorizontal);
    }
    
    public ArrayList<PuzzlePiece> getPuzzlePieces(Image image){                      
        ArrayList<PuzzlePiece> result =new ArrayList<PuzzlePiece>();
        
        double width=image.getWidth()/nrHorizontal;
        double height=image.getHeight()/nrVertical;
        
        initNordSudToothHeight(width,height);
        initEstWestToothHeight(width,height);
               
        for(int i=0;i<nrHorizontal;i++){
            for(int j=0;j<nrVertical;j++){
                ImageView imageView = new ImageView(image);                 
                
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
        if(i!=0){
            if(!horizontalTooths[i-1][j]){
                
            }else{
                result.setEstToothHeight(estWestToothHeight);
            }
        }
        return result;
    }
        
    private void initVerticalTooths(int nrVertical,int nrHorizontal){
        verticalTooths=new Boolean[nrHorizontal][nrVertical];
        
        for(int i=0;i<nrHorizontal;i++){
            for(int j=0;j<nrVertical;j++){
                boolean bool = new Random().nextBoolean();
                verticalTooths[i][j]=bool;
            }
        }   
    }
    
    private void initOrizontalTooths(int nrVertical,int nrHorizontal){
        horizontalTooths=new Boolean[nrHorizontal][nrVertical];
        
        for(int i=0;i<nrHorizontal;i++){
            for(int j=0;j<nrVertical;j++){
                boolean bool = new Random().nextBoolean();
                horizontalTooths[i][j]=bool;
            }
        }
    }
    
    private void clipPuzzlePiece(ImageView imageView, double width, double height,int i, int j,int nrVertical, int nrHorizontal){  
        Rectangle r = new Rectangle();
        r.setWidth(width);
        r.setHeight(height);
            
        Shape shape=null;

        double verticalOffset=getVerticalOffset(i,j);       
        double horizontalOffset=getHorizontalOffset(i,j);
        double addtionalHight=0;
        double addtionalWidth=0;
        if(j!=0){
            CubicCurve toothN = new CubicCurve();                       
            if(!verticalTooths[i][j-1]){    
                r.setX(horizontalOffset);
                toothN.setStartX(width/2+width/8+horizontalOffset);
                toothN.setStartY(0.0f);
                toothN.setControlX1(width/2+width/1.75+horizontalOffset);
                toothN.setControlY1(height/2);
                toothN.setControlX2(width/2-width/1.75+horizontalOffset);
                toothN.setControlY2(height/2);
                toothN.setEndX(width/2-width/8+horizontalOffset);
                toothN.setEndY(0.0f);
                
               shape = Shape.subtract(r,toothN);               
            }else{
                addtionalHight+=nordSudToothHeight;
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
                addtionalHight+=nordSudToothHeight;
                r.setX(horizontalOffset);
                toothS.setStartX(width/2+width/8+horizontalOffset);
                toothS.setStartY(height+verticalOffset);
                toothS.setControlX1(width/2+width/1.75+horizontalOffset);
                toothS.setControlY1(height+height/2+verticalOffset);
                toothS.setControlX2(width/2-width/1.75+horizontalOffset);
                toothS.setControlY2(height+height/2+verticalOffset);
                toothS.setEndX(width/2-width/8+horizontalOffset);
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
                toothE.setStartY(height/2+height/8+verticalOffset);                
                toothE.setControlX1(width/2);
                toothE.setControlY1(height/2+height/1.75+verticalOffset);
                toothE.setControlX2(width/2);
                toothE.setControlY2(height/2-height/1.75+verticalOffset);                
                toothE.setEndX(0);
                toothE.setEndY(height/2-height/8+verticalOffset);

                if(shape==null){
                    shape = Shape.subtract(r, toothE);
                }else{
                    shape = Shape.subtract(shape, toothE);
                }
            }else{        
                addtionalWidth+=estWestToothHeight;
                r.setX(horizontalOffset);
                toothE.setStartX(horizontalOffset);
                toothE.setStartY(height/2+height/8+verticalOffset);                
                toothE.setControlX1(-width/2+horizontalOffset);
                toothE.setControlY1(height/2+height/1.75+verticalOffset);
                toothE.setControlX2(-width/2+horizontalOffset);
                toothE.setControlY2(height/2-height/1.75+verticalOffset);                
                toothE.setEndX(horizontalOffset);
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
               toothV.setStartY(height/2+height/8+verticalOffset);                
               toothV.setControlX1(width/2+horizontalOffset);
               toothV.setControlY1(height/2+height/1.75+verticalOffset);
               toothV.setControlX2(width/2+horizontalOffset);
               toothV.setControlY2(height/2-height/1.75+verticalOffset);                
               toothV.setEndX(width+horizontalOffset);
               toothV.setEndY(height/2-height/8+verticalOffset);
               r.setX(horizontalOffset);
                if(shape==null){
                    shape = Shape.subtract(r, toothV);
                }else{
                    shape = Shape.subtract(shape, toothV);
                }
            }else{
               addtionalWidth+=estWestToothHeight;
               toothV.setStartX(width+horizontalOffset);
               toothV.setStartY(height/2+height/8+verticalOffset);                
               toothV.setControlX1(width+width/2+horizontalOffset);
               toothV.setControlY1(height/2+height/1.75+verticalOffset);
               toothV.setControlX2(width+width/2+horizontalOffset);
               toothV.setControlY2(height/2-height/1.75+verticalOffset);                
               toothV.setEndX(width+horizontalOffset);
               toothV.setEndY(height/2-height/8+verticalOffset);
               
               if(shape==null){
                    shape = Shape.union(r, toothV);
                }else{
                    shape = Shape.union(shape, toothV);
                }
            }            
        }
        
        Rectangle2D viewPort = new Rectangle2D(i*width-horizontalOffset,j*height-verticalOffset,width+addtionalWidth,height+addtionalHight);
        imageView.setViewport(viewPort);
        
        imageView.setClip(shape); 
    }
    
    private double getVerticalOffset(int i, int j){
        if(j!=0){
            if(verticalTooths[i][j-1]){
                return nordSudToothHeight;
            }
        }
        return 0;
    }
    
    private double getHorizontalOffset(int i, int j){
        if(i!=0){
            if(horizontalTooths[i-1][j]){
                return estWestToothHeight;
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
    }
}
