/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import ViewModels.PuzzlePiece;
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
    private Boolean[][] verticalLineTooths;
    private Boolean[][] orizontalLineTooths;
    
    public ArrayList<PuzzlePiece> getPuzzlePieces(Image image,int nrVertical, int nrHorizontal){
        initVerticalTooths(nrVertical,nrHorizontal);
        initOrizontalTooths(nrVertical,nrHorizontal);
        
        ArrayList<PuzzlePiece> result =new ArrayList<PuzzlePiece>();
        
        double width=image.getWidth()/nrHorizontal;
        double height=image.getHeight()/nrVertical;
        
        for(int i=0;i<nrHorizontal;i++){
            for(int j=0;j<nrVertical;j++){
                ImageView imageView = new ImageView(image);
                
                clipPuzzlePiece(imageView, width, height,i,j,nrVertical,nrHorizontal);
                
                Rectangle r = new Rectangle();
                r.setX(i-1);
                r.setY(j-1);
                r.setWidth(width+1);
                r.setHeight(height+1);
                
              /*  CubicCurve cubic = new CubicCurve();
                cubic.setStartX(width/2+width/8);
                cubic.setStartY(0.0f);
                cubic.setControlX1(width/2+width/1.75);
                cubic.setControlY1(height/2);
                cubic.setControlX2(width/2-width/1.75);
                cubic.setControlY2(height/2);
                cubic.setEndX(width/2-width/8);
                cubic.setEndY(0.0f);
                
                CubicCurve cubic2 = new CubicCurve();
                cubic2.setStartX(0.0f);
                cubic2.setStartY(height/2+height/8);                
                cubic2.setControlX1(width/2);
                cubic2.setControlY1(height/2+height/1.75);
                cubic2.setControlX2(width/2);
                cubic2.setControlY2(height/2-height/1.75);                
                cubic2.setEndX(0.0f);
                cubic2.setEndY(height/2-height/8);
                                              
                Rectangle2D r2 = new Rectangle2D(i*width,j*height,width,height);
                imageView.setViewport(r2);
              
                Shape shapeTest = Shape.subtract(r, cubic);  
                Shape shapeTest2 = Shape.union(shapeTest, cubic2);
                imageView.setClip(shapeTest2);  */
              
               /* CubicCurve cubic = new CubicCurve();
                cubic.setStartX(width/2+width/8);
                cubic.setStartY(0.0f);
                cubic.setControlX1(width/2+width/1.75);
                cubic.setControlY1(height/2);
                cubic.setControlX2(width/2-width/1.75);
                cubic.setControlY2(height/2);
                cubic.setEndX(width/2-width/8);
                cubic.setEndY(0.0f);
                                
                CubicCurve cubic2 = new CubicCurve();
                cubic2.setStartX(width);
                cubic2.setStartY(height/2+height/8);                
                cubic2.setControlX1(width+width/2);
                cubic2.setControlY1(height/2+height/1.75);
                cubic2.setControlX2(width+width/2);
                cubic2.setControlY2(height/2-height/1.75);                
                cubic2.setEndX(width);
                cubic2.setEndY(height/2-height/8);
                                              
                Rectangle2D r2 = new Rectangle2D(i*width,j*height,207.25,height);
                imageView.setViewport(r2);
              
                Shape shapeTest = Shape.subtract(r, cubic);  
                Shape shapeTest2 = Shape.union(shapeTest, cubic2);
                imageView.setClip(shapeTest2); 
                
                System.out.println("bounds = " + shapeTest2.getLayoutBounds());
                System.out.println("width  = " + shapeTest2.getLayoutBounds().getWidth());
                System.out.println("height = " + shapeTest2.getLayoutBounds().getHeight());
               // shapeTest2.get*/
                                                            
                PuzzlePiece puzzlePiece=new PuzzlePiece(IdGenerator.getNewId(),0, 0, 0, 0,imageView,width,height);
                
                result.add(puzzlePiece);
            }
        }
        
        return result;
    }
    
    private void initVerticalTooths(int nrVertical,int nrHorizontal){
        verticalLineTooths=new Boolean[nrVertical][nrHorizontal-1];
        
        for(int i=0;i<nrVertical;i++){
            for(int j=0;j<nrHorizontal-1;j++){
                boolean bool = new Random().nextBoolean();
                //verticalLineTooths[i][j]=bool;
                verticalLineTooths[i][j]=false;
            }
        }
    }
    
    private void initOrizontalTooths(int nrVertical,int nrHorizontal){
        orizontalLineTooths=new Boolean[nrVertical-1][nrHorizontal];
        
        for(int i=0;i<nrVertical-1;i++){
            for(int j=0;j<nrHorizontal;j++){
                boolean bool = new Random().nextBoolean();
               // orizontalLineTooths[i][j]=bool;
                orizontalLineTooths[i][j]=false;
            }
        }
    }
    
    private void clipPuzzlePiece(ImageView imageView, double width, double height,int i, int j,int nrVertical, int nrHorizontal){       
        Rectangle r = new Rectangle();
        //r.setX(i);
       // r.setY(55);
        r.setWidth(width);
        r.setHeight(height);
                
        CubicCurve toothE = new CubicCurve();
        toothE.setStartX(width);
        toothE.setStartY(height/2+height/8);                
        toothE.setControlX1(width+width/2);
        toothE.setControlY1(height/2+height/1.75);
        toothE.setControlX2(width+width/2);
        toothE.setControlY2(height/2-height/1.75);                
        toothE.setEndX(width);
        toothE.setEndY(height/2-height/8);
                        
        CubicCurve toothV = new CubicCurve();
        toothV.setStartX(width);
        toothV.setStartY(height/2+height/8);                
        toothV.setControlX1(width+width/2);
        toothV.setControlY1(height/2+height/1.75);
        toothV.setControlX2(width+width/2);
        toothV.setControlY2(height/2-height/1.75);                
        toothV.setEndX(width);
        toothV.setEndY(height/2-height/8);
        
        //Rectangle2D r2 = new Rectangle2D(i*width,j*height,width,height);
         //imageView.setViewport(r2);
        
        Shape shape=null;
        double testOffset=0;
        if(j!=0){
            CubicCurve toothN = new CubicCurve();                       
            if(!verticalLineTooths[i][j-1]){    
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
                testOffset=35;
                r.setY(0+testOffset);
                toothN.setStartX(width/2+width/8);
                toothN.setStartY(0+testOffset);
                toothN.setControlX1(width/2+width/1.75);
                toothN.setControlY1(-height/2+testOffset);
                toothN.setControlX2(width/2-width/1.75);
                toothN.setControlY2(-height/2+testOffset);
                toothN.setEndX(width/2-width/8);
                toothN.setEndY(0+testOffset);
                
                shape = Shape.union(r,toothN); 
            }
            
            Rectangle2D r2 = new Rectangle2D(i*width,j*height-testOffset,width,height+testOffset);
            imageView.setViewport(r2);
        }
               
        if(j!=nrVertical-1){
            CubicCurve toothS = new CubicCurve();            
            if(verticalLineTooths[i][j]){
                toothS.setStartX(width/2+width/8);
                toothS.setStartY(height+testOffset);
                toothS.setControlX1(width/2+width/1.75);
                toothS.setControlY1(height-height/2+testOffset);
                toothS.setControlX2(width/2-width/1.75);
                toothS.setControlY2(height-height/2+testOffset);
                toothS.setEndX(width/2-width/8);
                toothS.setEndY(height+testOffset);
                
                if(shape==null){
                    shape = Shape.subtract(r, toothS);
                }else{
                    shape = Shape.subtract(shape, toothS);
                }
            }else{


                 //this if-else is for test               
                if(i==0 && j==1){
                    Rectangle2D r4 = new Rectangle2D(i*width,j*height,width,height+35);
                    imageView.setViewport(r4);
                     r.setY(0);
                     toothS.setStartX(width/2+width/8);
                     toothS.setStartY(height);
                    toothS.setControlX1(width/2+width/1.75);
                    toothS.setControlY1(height+height/2);
                    toothS.setControlX2(width/2-width/1.75);
                    toothS.setControlY2(height+height/2);
                    toothS.setEndX(width/2-width/8);
                    toothS.setEndY(height);
                }else{
                                    Rectangle2D r3 = new Rectangle2D(i*width,j*height-35,width,height+70);
                imageView.setViewport(r3);
                                r.setY(35);
                                toothS.setStartX(width/2+width/8);
                toothS.setStartY(height+35);
                toothS.setControlX1(width/2+width/1.75);
                toothS.setControlY1(height+height/2+35);
                toothS.setControlX2(width/2-width/1.75);
                toothS.setControlY2(height+height/2+35);
                toothS.setEndX(width/2-width/8);
                toothS.setEndY(height+35);
                }
                

                
                
                if(shape==null){
                    shape = Shape.union(r, toothS);
                }else{
                    shape = Shape.union(shape, toothS);
                }
            }
        }
         
        if(shape!=null){
           // shape = Shape.union(shape, toothE);
            imageView.setClip(shape); 
        }else{
           // imageView.setClip(shape); 
        }
        
        //imageView.setClip(shape); 
    }
}
