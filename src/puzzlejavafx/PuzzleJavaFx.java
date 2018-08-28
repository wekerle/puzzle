/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

import Listeners.LevelFinishedEventListener;
import Listeners.LevelSelectedEventListener;
import Models.AplicationModel;
import Models.LevelModel;
import ViewModels.FinishLevelView;
import ViewModels.GameSessionView;
import ViewModels.MinimalLevelView;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author tibor.wekerle
 */
public class PuzzleJavaFx extends Application implements LevelSelectedEventListener, LevelFinishedEventListener{
    
    private AplicationModel aplicationModel=new AplicationModel();
    private BorderPane borderPane = new BorderPane();
    private Scene scene=new Scene(borderPane);
    private Stage stage=null;
    private GameSessionView gameSession=null;   
    
    @Override
    public void start(Stage primaryStage) {   
        DataCollector dataCollector=new DataCollector();
        aplicationModel.setLevels(dataCollector.getLevels());
        
        MenuBar menuBar=createMenu(); 
        borderPane.setTop(menuBar);       
        borderPane.setCenter(getContent());
           
        stage=primaryStage;
    
        scene.getStylesheets().add("Styling/styles.css");
                                                     
        primaryStage.setWidth(825);
        primaryStage.setHeight(700);
        
        primaryStage.setTitle("Iza & Tibor");                            
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);        
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
        
        int i=0;
        int j=0;
        for(LevelModel level :aplicationModel.getLevels())
        {
            MinimalLevelView minimalLevel= new MinimalLevelView(level.getLevelId(), level.getLevelNumber(),aplicationModel.getMaxSolvedLevel());
            minimalLevel.setLevelSelectedEventListener(this);
            grid.add(minimalLevel,j,i);
            
            j++;
            if(j==5)
            {
                i++;
                j=0;
            }
        }
        grid.setHgap(45);
        grid.setVgap(25);
        grid.setPadding(new Insets(20, 10, 10, 50));
        grid.getStyleClass().add("grid");
        return grid;
    }    
    
    private void renderLevel(LevelModel level){
        gameSession=new GameSessionView(level,this,borderPane.getWidth());
        borderPane.setCenter(gameSession);
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                     gameSession.initPuzzlesCorrectPosition();
                }
            }, 500);
    }

    @Override
    public void levelSelected(int levelNr) {
        if(levelNr==0)
        {
            start(stage);
        }else
        {
            LevelModel level=aplicationModel.getLevelByNr(levelNr);
            renderLevel(level);
        }
    }

    @Override
    public void levelFinished(LevelModel level) {
        
        if(this.aplicationModel.getMaxSolvedLevel()<level.getLevelNumber()+1)
        {
            this.aplicationModel.setMaxSolvedLevel(level.getLevelNumber()+1);
        }
        FinishLevelView finishLevelView=new FinishLevelView(level.getLevelNumber(),level.getImagePath());
        finishLevelView.setLevelSelectedEventListener(this);
        borderPane.setCenter(finishLevelView);
    }
}
