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
    private HBox footer=new HBox();
    private GridPane center=new GridPane();
    private Pane mainBoard=new Pane();
    private Image otherImage=new Image("img/refresh.png");
    private final double SPACE_BEETWEEN_PUZZLES=10;
        
    private void populateContent()
    {   
        this.setCenter(this.center);
        this.setBottom(this.footer);  
        initCenterContent();
        initBottomContent(); 
    }
        
    private void initBottomContent(){      
        ImageView previousButton=new ImageView(otherImage);                       
        Pane puzzlePiecesContainer=new Pane();

        int i=0;
        int j=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()) {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();
            puzzle.setTranslateX(i*(puzzle.getPuzzlePieceWidth()+SPACE_BEETWEEN_PUZZLES));
            
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
    }
        
    private void initCenterContent() 
    {             
        center.setPadding(new Insets(20, 10, 10, 20));
    
        Image image=new Image("/img/flip.jpg");
        initPuzzlePieces(image);
        
        mainBoard.setMinWidth(image.getWidth());
        mainBoard.setMinHeight(image.getHeight());
        
        center.add(mainBoard,0,0);
                
        mainBoard.getStyleClass().add("mainBoard");      
        center.getStyleClass().add("center");
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
        
        Bounds footerBoundsInScene = footer.getBoundsInParent();
        Bounds puzzleBoundsInFooter =  puzzle.getBoundsInParent();
        Insets footerPadding=footer.getPadding();
        this.footer.getBoundsInParent();
        
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
    
    public void initPuzzlesCorrectPosition(){
        int i=0;
        int j=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()) {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();       
            Bounds centerBounds=this.center.getBoundsInParent();

            double correctX=centerBounds.getMinX() + puzzle.getTranslateX()-((puzzle.getPuzzlePieceWidth()+SPACE_BEETWEEN_PUZZLES)*i)-otherImage.getWidth();
            double correctY=centerBounds.getMinY()-centerBounds.getHeight();

            puzzle.setCorrectX(correctX+j*puzzle.getPuzzlePieceWidth());
            puzzle.setCorrectY(correctY+i*puzzle.getPuzzlePieceHeight());
            
            i++;
            if(i==3){
               j++;
               i=0;
            }
        }
    }
}
