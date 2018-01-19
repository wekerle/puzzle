/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import ViewModels.PuzzlePiece;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author tibor.wekerle
 */
public class DataCollector {
    public ArrayList<PuzzlePiece> getPuzzlePieces(){
        ArrayList<PuzzlePiece> result =new ArrayList<PuzzlePiece>();
        Image image= new Image("/img/flip.jpg");
        
        double width=image.getWidth()/3;
        double height=image.getHeight()/3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ImageView imageView = new ImageView(new Image("/img/flip.jpg"));
                
                Rectangle r = new Rectangle();
                r.setX(i*width);
                r.setY(j*height);
                r.setWidth(width);
                r.setHeight(height);
                imageView.setClip(r);
                
                PuzzlePiece puzzlePiece=new PuzzlePiece(0, 0, 0, 0,imageView);
                
                result.add(puzzlePiece);
            }
        }
        
        return result;
    }
}
