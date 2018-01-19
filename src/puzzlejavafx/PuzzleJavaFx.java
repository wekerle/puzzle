/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import ViewModels.PuzzlePiece;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    
    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar=createMenu();       
        borderPane.setTop(menuBar);         
        borderPane.setCenter(getContent());
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
        
    private GridPane getContent() 
    {
        GridPane grid = new GridPane();              
        grid.setPadding(new Insets(20, 10, 10, 50));
    
        Pane pane = new Pane();
        ArrayList<PuzzlePiece> pieces=new DataCollector().getPuzzlePieces();
        int i=0;
        for(PuzzlePiece puzzle : pieces)
        {
            puzzle.setLayoutX(i*10);
            puzzle.setLayoutY(i*15);
            
            pane.getChildren().add(puzzle);
            if(i % 2==0){
               // pane.getChildren().add(puzzle);
            }
            
            i++;
        }
        grid.add(pane,0,0);
        
        pane.getStyleClass().add("mainBoard");
        return grid;
    }    
}
