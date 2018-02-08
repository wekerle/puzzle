/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import Listeners.PuzzlePositionChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import puzzlejavafx.DataCollector;

/**
 *
 * @author tibor.wekerle
 */
public class GameSessionView extends BorderPane implements PuzzlePositionChangeListener{
    
    private HashMap<Integer,PuzzlePiece> puzzlePiecesMap=new HashMap<Integer,PuzzlePiece>();
    private HBox footer=new HBox();
    private GridPane center=new GridPane();
    private Pane mainBoard=new Pane();
    private Image otherImage=new Image("img/refresh.png");
    private final double SPACE_BEETWEEN_PUZZLES=15;
    private int numberOfPuzzlesInFooter=4;
    private int nrVertical=3,nrHorizontal=3;
        
    private void populateContent(Image image)
    {   
        this.setCenter(this.center);
        this.setBottom(this.footer);  
        initCenterContent(image);
        initBottomContent();
    }
    
    private void initBottomContent(){      
        ImageView otherButton=new ImageView(otherImage);
        VBox otherButtonContainer=new VBox();
        Pane puzzlePiecesContainer=new Pane();

        int i=1;
        boolean isVisible=true;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()) {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();
            puzzle.setTranslateX((i-1)*(puzzle.getPuzzlePieceWidth()+SPACE_BEETWEEN_PUZZLES));
            puzzle.setIsVisible(isVisible);
            
            if(i==numberOfPuzzlesInFooter){
               isVisible=false;
               i=1;
            }
            puzzlePiecesContainer.getChildren().add(puzzle);
            i++;
        }
        
        otherButtonContainer.getChildren().add(otherButton);
        otherButtonContainer.setAlignment(Pos.CENTER);
        footer.getChildren().add(otherButtonContainer);
        footer.getChildren().add(puzzlePiecesContainer);
        
        footer.getStyleClass().add("footer");
    }
        
    private void initCenterContent(Image image) 
    {                 
        initPuzzlePieces(image);
        
        mainBoard.setMinWidth(image.getWidth());
        mainBoard.setMinHeight(image.getHeight());
        
        center.add(mainBoard,0,0);
                
        mainBoard.getStyleClass().add("mainBoard");      
        center.getStyleClass().add("center");
    }

    private void initPuzzlePieces(Image image){
        ArrayList<PuzzlePiece> puzzlePieces=new DataCollector().getPuzzlePieces(image,nrVertical,nrHorizontal);

        for(PuzzlePiece puzzle : puzzlePieces)
        {
           puzzle.setPuzzlePositionChangeListener(this);
           this.puzzlePiecesMap.put(new Integer(puzzle.getPuzzleId()), puzzle);
        }
    }
    
    private boolean puzzleIsOutFromFooter(PuzzlePiece puzzle){
        boolean result=true;
        
        Bounds footerBoundsInScene = footer.getBoundsInParent();
        Bounds puzzleBoundsInFooter =  puzzle.getBoundsInParent();
        Insets footerPadding=footer.getPadding();
        this.footer.getBoundsInParent();
        
        double foooterInSceneMinY=footerBoundsInScene.getMinY(); 
        double puzzleInSceneMaxY=foooterInSceneMinY + puzzleBoundsInFooter.getMaxY()+footerPadding.getTop();
        
        if(puzzleInSceneMaxY>=foooterInSceneMinY){
            result=false;
        }
        
        return result;
    }
    
    @Override
    public void positionChanged(PuzzlePiece puzzle) {        
        boolean puzzleIsOutFromFooter=puzzleIsOutFromFooter(puzzle);
        //if not left completly the footer bounds
        if(!puzzleIsOutFromFooter){
            puzzle.setTranslateX(puzzle.getOrgTranslateX());
            puzzle.setTranslateY(puzzle.getOrgTranslateY());  
        }else{
            for (HashMap.Entry item : puzzlePiecesMap.entrySet()){
                PuzzlePiece puzzlePiece=(PuzzlePiece)item.getValue();
                if(!puzzlePiece.isVisible()){
                    puzzlePiece.setIsVisible(true);
                    puzzlePiece.setTranslateX(puzzle.getOrgTranslateX());
                    break;
                }
            }
        }     
    }
    
    public GameSessionView(Image image)
    {
        populateContent(image);       
    }   
    
    public void initPuzzlesCorrectPosition(){
        int i=0;
        int j=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()) {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();       
            Bounds centerBounds=this.center.getBoundsInParent();
            Insets centerPadding=this.center.getPadding();

            double correctY=centerBounds.getMinY()-centerBounds.getHeight();
            double correctX=centerBounds.getMinX()-otherImage.getWidth()+centerPadding.getLeft();
            
            puzzle.setCorrectX(correctX+j*puzzle.getPuzzlePieceWidth());
            puzzle.setCorrectY(correctY+i*puzzle.getPuzzlePieceHeight());
            
            i++;
            if(i==nrVertical){
               j++;
               i=0;
            }
        }
    }
}
