/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import ViewModels.GameSessionView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author tibor.wekerle
 */
public class PuzzleJavaFx extends Application{
    
    private BorderPane borderPane = new BorderPane();
    private Scene scene=new Scene(borderPane);
    private Stage stage=null;
    private GameSessionView gameSession=null;
        
    @Override
    public void start(Stage primaryStage) {       
        MenuBar menuBar=createMenu(); 
        initGameSession();
        borderPane.setTop(menuBar);         
        borderPane.setCenter(gameSession);
        
        stage=primaryStage;
    
        scene.getStylesheets().add("Styling/styles.css");
                                                     
        primaryStage.setWidth(825);
        primaryStage.setHeight(700);
        
        primaryStage.setTitle("Iza & Tibi");                            
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show(); 
        gameSession.initPuzzlesCorrectPosition();
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
                    
    private void initGameSession() 
    {
        gameSession=new GameSessionView(new Image("/img/flip.jpg"));
    }    
}
