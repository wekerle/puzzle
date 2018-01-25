/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 *
 * @author tibor.wekerle
 */
public class PuzzlePiece extends HBox{
    private ImageView puzzle;
    private int puzzleId;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    
    public PuzzlePiece(int id, int toothN, int toothS, int toothW, int toothE,ImageView image){
        this.puzzle=image;
        this.puzzleId=id;
        this.getChildren().add(puzzle);
        
       this.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                orgSceneX=event.getSceneX();
                orgSceneY=event.getSceneY();
                orgTranslateX = ((PuzzlePiece)(event.getSource())).getTranslateX();
                orgTranslateY = ((PuzzlePiece)(event.getSource())).getTranslateY();
                System.out.println(orgSceneX+","+orgSceneY+","+orgTranslateX+","+orgTranslateY);
            }
       });
       
        this.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((PuzzlePiece)(event.getSource())).setTranslateX(newTranslateX);
                ((PuzzlePiece)(event.getSource())).setTranslateY(newTranslateY);
                 System.out.println(offsetX+","+offsetY+","+newTranslateX+","+newTranslateY);
            }
       });

    }
    
    public int getPuzzleId(){
        return this.puzzleId;
    }
}
