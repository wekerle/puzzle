/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author tibor.wekerle
 */
public class PuzzlePiece extends HBox{
    public ImageView puzzle;
    
    public PuzzlePiece(int toothN, int toothS, int toothW, int toothE,ImageView image){
        this.puzzle=image;
        this.getChildren().add(puzzle);
    }
}
