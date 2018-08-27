/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import Listeners.LevelSelectedEventListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author tibor.wekerle
 */
public class MinimalLevelView extends HBox{
    private int levelId;
    private int levelNumber;
    private LevelSelectedEventListener levelSelectedEvent=null;

    public void setLevelSelectedEventListener(LevelSelectedEventListener levelSelectedEvent) 
    {
        this.levelSelectedEvent = levelSelectedEvent;
    }
    
    public MinimalLevelView(int levelId,int levelNumber,int maxSolvedLevel)
    {
        this.levelId=levelId;
        this.levelNumber=levelNumber;
        populateContent(maxSolvedLevel);
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               // if(levelNumber<=maxSolvedLevel)
               // {
                    MinimalLevelView.this.levelSelectedEvent.levelSelected(levelId);
              //  }              
            }
        });
    }
    
    private void populateContent(int maxSolvedLevel)
    {        
        Text levelNumberText=new Text(Integer.toString(levelNumber));
        levelNumberText.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,40));
             
        this.setAlignment(Pos.CENTER);
        Shape shape=null;
        
        CubicCurve test1 = new CubicCurve();
        test1.setStartX(0);
        test1.setStartY(72);                
        test1.setControlX1(-108);
        test1.setControlY1(-24);
        test1.setControlX2(0);
        test1.setControlY2(-48);                
        test1.setEndX(0);
        test1.setEndY(-12);
        
        CubicCurve test2 = new CubicCurve();
        test2.setStartX(0);
        test2.setStartY(72);                
        test2.setControlX1(108);
        test2.setControlY1(-24);
        test2.setControlX2(0);
        test2.setControlY2(-48);                
        test2.setEndX(0);
        test2.setEndY(-12);
        
        shape = Shape.union(test1,test2);
        
        if(maxSolvedLevel>this.levelNumber)
        {
            shape.setFill(Color.RED);
            shape.setStroke(Color.BLACK);
            levelNumberText.getStyleClass().add("cursor-hand");
            shape.getStyleClass().add("cursor-hand");
        }else if(maxSolvedLevel==this.levelNumber)
        {
          shape.setFill(Color.web("C28585"));
          shape.setStroke(Color.BLACK);
          levelNumberText.getStyleClass().add("cursor-hand");
          shape.getStyleClass().add("cursor-hand");
        }else
        {
            levelNumberText.setFill(Color.web("717171"));
            shape.setFill(Color.web("C28585"));
            shape.setStroke(Color.web("717171"));
        }
        shape.setStrokeWidth(4.0);
                              
        this.getChildren().add(shape);
       
        StackPane stack = new StackPane();
        stack.getChildren().addAll(shape, levelNumberText);        
        this.getChildren().add(stack);
    }
}
