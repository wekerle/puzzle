/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import Listeners.PuzzlePositionChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import puzzlejavafx.DataCollector;

/**
 *
 * @author tibor.wekerle
 */
public class GameSessionView extends BorderPane implements PuzzlePositionChangeListener{
    
    private HashMap<Integer,PuzzlePiece> puzzlePiecesMap=new HashMap<Integer,PuzzlePiece>();
        
    private void populateContent()
    {       
       this.setCenter(getCenterContent());
       this.setBottom(getBottomContent());   
    }
    
    private HBox getBottomContent(){      
        HBox footer=new HBox();
        ImageView previousButton=new ImageView(new Image("img/previous.png"));                       
        Pane puzzlePiecesContainer=new Pane();

        int i=0;
        int j=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()) {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();
            puzzle.setTranslateX(i*110);
           
            puzzle.setCorrectX(j*100+200);
            puzzle.setCorrectY(i*100-300);
            
            i++;
            if(i==3){
               j++;
               i=0;
            }
            puzzlePiecesContainer.getChildren().add(puzzle);
        }
        
        footer.getChildren().add(previousButton);
        footer.getChildren().add(puzzlePiecesContainer);
        
        footer.setPadding(new Insets(20,0, 10, 20));
        footer.getStyleClass().add("footer");
        
        return footer;
    }
        
    private GridPane getCenterContent() 
    {
        GridPane grid = new GridPane();              
        grid.setPadding(new Insets(20, 10, 10, 20));
    
        Image image=new Image("/img/flip.jpg");
        initPuzzlePieces(image);
        
        Pane pane = new Pane();
        pane.setMinWidth(image.getWidth());
        pane.setMinHeight(image.getHeight());
        
        int i=0;
        int j=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet())
        {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();            
            //puzzle.setTranslateY(i*100);
            //puzzle.setTranslateX(j*100);           
            //pane.getChildren().add(puzzle);
            
           // puzzle.setCorrectX(j*100);
            //puzzle.setCorrectY(i*100);
                                 
            i++;
            
            if(i==3){
             j++;
             i=0;
            }
        }
        
        
        //pane.getChildren().addAll(pieces.get(4));
        grid.add(pane,0,0);
                
        pane.getStyleClass().add("mainBoard");
        return grid;
    }

    private void initPuzzlePieces(Image image){
        ArrayList<PuzzlePiece> puzzlePieces=new DataCollector().getPuzzlePieces(image);

        for(PuzzlePiece puzzle : puzzlePieces)
        {
           puzzle.setPuzzlePositionChangeListener(this);
           this.puzzlePiecesMap.put(new Integer(puzzle.getPuzzleId()), puzzle);
        }
    }
    
    private boolean puzzleIsOutFromFooter(PuzzlePiece puzzle){
        boolean result=false;
        HBox footer=(HBox)(puzzle.getParent().getParent());
        Bounds footerBoundsInScene = footer.getBoundsInParent();
        Bounds puzzleBoundsInFooter =  puzzle.getBoundsInParent();
        Insets footerPadding=footer.getPadding();
        
        double foooterInSceneMinY=footerBoundsInScene.getMinY(); 
        double puzzleInSceneMaxY=foooterInSceneMinY + puzzleBoundsInFooter.getMaxY()+footerPadding.getTop();
        
        if(puzzleInSceneMaxY>=foooterInSceneMinY){
            result=true;
        }
        
        return result;
    }
    
    @Override
    public void positionChanged(PuzzlePiece puzzle) {        
        boolean puzzleIsOutFromFooter=puzzleIsOutFromFooter(puzzle);
        //if not left completly the footer bounds
        if(puzzleIsOutFromFooter){
            puzzle.setTranslateX(puzzle.getOrgTranslateX());
            puzzle.setTranslateY(puzzle.getOrgTranslateY());  
        }     
    }
    
    public GameSessionView()
    {
        populateContent();       
    }   
}
