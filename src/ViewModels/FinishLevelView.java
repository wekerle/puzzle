/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import Listeners.LevelSelectedEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.Timer;

/**
 *
 * @author Ronaldo
 */
public class FinishLevelView extends HBox {
    private VBox contentNode=new VBox();
    private LevelSelectedEventListener levelSelectedEvent=null;
    private int currentPictureNumberInLastFinishLevelView=1;
    public void setLevelSelectedEventListener(LevelSelectedEventListener levelSelectedEvent) 
    {
        this.levelSelectedEvent = levelSelectedEvent;
    }
    
    public FinishLevelView(int previousLevelNr, String imagePath)
    {
        HBox footerNode=new HBox();
        
        ImageView imageView=new ImageView(new Image(imagePath));
        contentNode.getChildren().add(imageView);
                       
        Image imageHome=new Image("/img/home.png");
        Button buttonHome=new Button("Home", new ImageView(imageHome));  
        
        buttonHome.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event) {
                levelSelectedEvent.levelSelected(0);
            }
        });
        
        footerNode.getChildren().add(buttonHome);
        
        Image imageRestart=new Image("/img/restart.png");
        Button buttonRestart=new Button("Restart Level", new ImageView(imageRestart));  
        
        buttonRestart.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event) {
                levelSelectedEvent.levelSelected(previousLevelNr);
            }
        });
               
        footerNode.getChildren().add(buttonRestart);
        footerNode.setAlignment(Pos.CENTER);
        footerNode.setSpacing(20);
        
        contentNode.getChildren().add(footerNode);
        
        this.getChildren().add(contentNode);
        this.setAlignment(Pos.CENTER);        
    }
        
    private Image getImageByLevelNumber(int previousLevelNumber)
    {
        Image image=null;
        switch(previousLevelNumber)
        {
            case 1:
                image= new Image("/img/1.png");
                break;
            case 2:
                image= new Image("/img/2.png");
                break;
            case 3:
                image= new Image("/img/3.png");
                break;
            case 4:
                image= new Image("/img/4.png");
                break;
            case 5:
                image= new Image("/img/5.png");
                break;
            case 6:
                image= new Image("/img/6.png");
                break;
            case 7:
                image= new Image("/img/7.png");
                break;
            case 8:
                image= new Image("/img/8.png");
                break;
            case 9:
                image= new Image("/img/9.png");
                break;
            case 10:
                image= new Image("/img/10.png");
                break;
            case 11:
                image= new Image("/img/11.png");
                break;
            case 12:
                image= new Image("/img/12.png");
                break;
            case 13:
                image= new Image("/img/13.png");
                break;
            case 14:
                image= new Image("/img/14.png");
                break;
            case 15:
                image= new Image("/img/15.png");
                break;
            case 16:
                image= new Image("/img/16.png");
                break;
            case 17:
                image= new Image("/img/17.png");
                break;
            case 18:
                image= new Image("/img/18.png");
                break;
            case 19:
                image= new Image("/img/19.png");
                break;
            case 20:
                image= new Image("/img/20.png");
                break;
            case 21:
                image= new Image("/img/21.png");
                break;
            case 22:
                image= new Image("/img/22.png");
                break;
            case 23:
                image= new Image("/img/23.png");
                break;
            case 24:
                image= new Image("/img/24.png");
                break;
            case 25:
                image= new Image("/img/25.png");
                break;
            case 26:
                image= new Image("/img/26.png");
                break;
            case 27:
                image= new Image("/img/27.png");
                break;
            case 28:
                image= new Image("/img/28.png");
                break;
            case 29:
                image= new Image("/img/29.png");
                break;
            case 30:
                image= new Image("/img/30.png");
                break;
        }
        
        return image;
    }
}
