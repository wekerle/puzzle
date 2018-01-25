/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import ViewModels.PuzzlePiece;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author tibor.wekerle
 */
public class PuzzleJavaFx extends Application {
    
    private BorderPane borderPane = new BorderPane();
    private Scene scene=new Scene(borderPane);
    private Stage stage=null;
    private HashMap<Integer,PuzzlePiece> puzzlePiecesMap=new HashMap<Integer,PuzzlePiece>();
    
    @Override
    public void start(Stage primaryStage) {       
        MenuBar menuBar=createMenu();       
        borderPane.setTop(menuBar);         
        borderPane.setCenter(getContent());
        borderPane.setBottom(getBottom());
        
        stage=primaryStage;
    
        scene.getStylesheets().add("Styling/styles.css");
                                                     
        primaryStage.setWidth(825);
        primaryStage.setHeight(700);
        
        primaryStage.setTitle("Iza & Tibi");
        
        
        
        
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
        private MenuBar createMenu()
    { 
        MenuBar menuBar = new MenuBar();
 
        // --- Menu File
        Menu menuFile = new Menu("Menu");
        
        MenuItem homeMenuItem = new MenuItem("Home");
        MenuItem loadMenuItem = new MenuItem("Load");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent ->
                stage.fireEvent(
                        new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                ));

        menuFile.getItems().addAll(homeMenuItem,loadMenuItem, saveMenuItem,
        new SeparatorMenuItem(), exitMenuItem);
        
       // homeMenuItem.setOnAction(actionEvent -> clickHome());
       // saveMenuItem.setOnAction(actionEvent -> clickSave());
       // loadMenuItem.setOnAction(actionEvent -> clickLoad());
                      
        menuBar.getMenus().addAll(menuFile);
                  
        return menuBar;

    }
    
    private void initPuzzlePieces(Image image){
        ArrayList<PuzzlePiece> puzzlePieces=new DataCollector().getPuzzlePieces(image);
        
        for(PuzzlePiece puzzle : puzzlePieces)
        {
           this.puzzlePiecesMap.put(new Integer(puzzle.getPuzzleId()), puzzle);
        }
    }    
        
    private HBox getBottom(){
        HBox hBox=new HBox();
        
        ImageView nextButton=new ImageView(new Image("img/next.png"));
        ImageView previousButton=new ImageView(new Image("img/previous.png"));
        
                
        Pane puzzlePiecesContainer=new Pane();

        int i=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet()) {
          PuzzlePiece puzzle=(PuzzlePiece)item.getValue();
         // puzzle.setLayoutX(i*120);
         puzzle.setTranslateX(i*50);
          i++;
          puzzlePiecesContainer.getChildren().add(puzzle);
        }
        
      //  hBox.getChildren().add(previousButton);
        hBox.getChildren().add(puzzlePiecesContainer);
      //  hBox.getChildren().add(nextButton);
        
        hBox.setPadding(new Insets(20,0, 10, 20));
        return hBox;
    }
        
    private GridPane getContent() 
    {
        GridPane grid = new GridPane();              
        grid.setPadding(new Insets(20, 10, 10, 20));
    
        Image image=new Image("/img/flip.jpg");
        initPuzzlePieces(image);
        
        Pane pane = new Pane();
        pane.setMinWidth(image.getWidth());
        pane.setMinHeight(image.getHeight());
       // ArrayList<PuzzlePiece> pieces=new DataCollector().getPuzzlePieces(new Image("/img/flip.jpg"));
        int i=0;
        int j=0;
        for (HashMap.Entry item : puzzlePiecesMap.entrySet())
        {
            PuzzlePiece puzzle=(PuzzlePiece)item.getValue();            
           // puzzle.setLayoutX(i*200);
          //  puzzle.setLayoutY(j*170);           
            pane.getChildren().add(puzzle);
            if(i % 2==0){
             //  pane.getChildren().add(puzzle);
            }
            
            
            
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
}
