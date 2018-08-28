/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import Listeners.LevelFinishedEventListener;
import Listeners.PuzzlePositionChangeListener;
import Models.LevelModel;
import Models.PuzzlePiece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import puzzlejavafx.PuzzleCutter;

/**
 *
 * @author tibor.wekerle
 */
public class GameSessionView extends BorderPane implements PuzzlePositionChangeListener{
    
    private HashMap<Integer,PuzzlePiece> puzzlePiecesMap=new LinkedHashMap<Integer,PuzzlePiece>();
    private HBox footer=new HBox();
    private GridPane center=new GridPane();
    private Pane mainBoard=new Pane();
    private Image otherImage=new Image("img/refresh.png");
    private final double SPACE_BEETWEEN_PUZZLES=15;
    private LevelModel level=null;
    private LevelFinishedEventListener levelFinishedEvent=null;
    private int puzzlesInFooter;
        
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
            
            if(i==puzzlesInFooter){
               isVisible=false;
               i=1;
            }
            puzzlePiecesContainer.getChildren().add(puzzle);
            i++;
        }
        otherButtonContainer.getStyleClass().add("cursor-hand");
        otherButtonContainer.getChildren().add(otherButton);
        otherButtonContainer.setAlignment(Pos.CENTER);
        otherButtonContainer.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event) {
                showOtherPuzzles();
            }
        });
        footer.getChildren().add(otherButtonContainer);
        footer.getChildren().add(puzzlePiecesContainer);
        
        footer.getStyleClass().add("footer");
    }
    
    private void showOtherPuzzles(){
        
    } 
    
    private void initCenterContent(Image image) 
    {                 
        ArrayList<PuzzlePiece> puzzlePieces=new PuzzleCutter(level.getNrVertical(),level.getNrHorizontal()).getPuzzlePieces(image);
        initPuzzlePieces(puzzlePieces);
        
        mainBoard.setMinWidth(image.getWidth());
        mainBoard.setMinHeight(image.getHeight());
        
        center.add(mainBoard,0,0);
                
        mainBoard.getStyleClass().add("mainBoard");      
        center.getStyleClass().add("center");
    }

    private void initPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces){

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
        
        double foooterInSceneMinY=footerBoundsInScene.getMinY(); 
        double puzzleInSceneMaxY=foooterInSceneMinY + puzzleBoundsInFooter.getMaxY()+footerPadding.getTop();
        
        if(puzzleInSceneMaxY>=foooterInSceneMinY){
            result=false;
        }
        
        return result;
    }
    
    private boolean puzzleWasInFooter(PuzzlePiece puzzle){
        boolean result=true;
                
        double puzzleOrgTranslateY=puzzle.getOrgSceneY();      
        if(puzzleOrgTranslateY<=footer.getLayoutY()){
            result=false;
        }
        
        return result;
    }
    
    private void checkWin(){
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()){
            PuzzlePiece puzzlePiece=(PuzzlePiece)item.getValue();
            if(!puzzlePiece.getIsOnRightPosition()){
                return;
            }
        }
        levelFinishedEvent.levelFinished(this.level);
    }
    
    @Override
    public void positionChanged(PuzzlePiece puzzle) {        
        boolean puzzleIsOutFromFooter=puzzleIsOutFromFooter(puzzle);
        boolean puzzleWasInFooter=puzzleWasInFooter(puzzle);
        //if not left completly the footer bounds
        if(!puzzleIsOutFromFooter){
            puzzle.setTranslateX(puzzle.getOrgTranslateX());
            puzzle.setTranslateY(puzzle.getOrgTranslateY());  
        }else{
            if(puzzleWasInFooter){
                for (HashMap.Entry item : puzzlePiecesMap.entrySet()){
                    PuzzlePiece puzzlePiece=(PuzzlePiece)item.getValue();
                    if(!puzzlePiece.isVisible()){
                        puzzlePiece.setIsVisible(true);
                        puzzlePiece.setTranslateX(puzzle.getOrgTranslateX());
                        break;
                    }
                }
                checkWin();
            }
        }     
    }
    
    public GameSessionView(LevelModel level,LevelFinishedEventListener levelFinishedEvent, double paneWidth)
    {    
        this.levelFinishedEvent=levelFinishedEvent;
        Image image=new Image(level.getImagePath());
        this.level=level;
        double puzzlePieceWidth=image.getWidth()/level.getNrHorizontal();
        this.puzzlesInFooter=(int)((paneWidth-100)/(puzzlePieceWidth+SPACE_BEETWEEN_PUZZLES));
        this.puzzlesInFooter=5;
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
            if(i==level.getNrVertical()){
               j++;
               i=0;
            }
        }
    }
}
