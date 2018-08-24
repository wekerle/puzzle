/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Listeners.PuzzlePositionChangeListener;
import Models.ToothHeightsModel;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author tibor.wekerle
 */
public class PuzzlePiece extends HBox{
    private ImageView puzzle;
    private int puzzleId;
    private double puzzlePieceWidth, puzzlePieceHeight;
    private double correctX, correctY;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private double newTranslateX, newTranslateY;
    private boolean isVisible,movedOutFromFooter, isOnRightPosition;
    private PuzzlePositionChangeListener puzzlePositionChangeListener;
    private ToothHeightsModel toothHeights;
    private double calculateDistanceToCorrectPosition()
    {
        return (Math.sqrt((correctX-newTranslateX)*(correctX-newTranslateX) + (correctY-newTranslateY)*(correctY-newTranslateY)));
    }
    
    // <editor-fold defaultstate="collapsed" desc="getter setter region ">

    public double getPuzzlePieceWidth() {
        return puzzlePieceWidth;
    }

    public double getPuzzlePieceHeight() {
        return puzzlePieceHeight;
    }
    
    public double getCorrectX() {
        return correctX;
    }

    public void setCorrectX(double correctX) {
        this.correctX = correctX;
       // this.correctX = correctX - toothHeights.getEstToothHeight();
    }

    public double getCorrectY() {
        return correctY;
    }

    public void setCorrectY(double correctY) {
        this.correctY = correctY;
       // this.correctY = correctY - toothHeights.getNordToothHeight();
    }
    
    public double getOrgTranslateX() {
        return orgTranslateX;
    }

    public void setOrgTranslateX(double orgTranslateX) {
        this.orgTranslateX = orgTranslateX;
    }

    public double getOrgTranslateY() {
        return orgTranslateY;
    }

    public void setOrgTranslateY(double orgTranslateY) {
        this.orgTranslateY = orgTranslateY;
    }

    public double getNewTranslateX() {
        return newTranslateX;
    }

    public void setNewTranslateX(double newTranslateX) {
        this.newTranslateX = newTranslateX;
    }

    public double getNewTranslateY() {
        return newTranslateY;
    }

    public void setNewTranslateY(double newTranslateY) {
        this.newTranslateY = newTranslateY;
    }

    public void setPuzzlePositionChangeListener(PuzzlePositionChangeListener puzzlePositionChangeListener) {
        this.puzzlePositionChangeListener = puzzlePositionChangeListener;
    }
    
    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
        this.setVisible(isVisible);
    }

    public boolean isMovedOutFromFooter() {
        return movedOutFromFooter;
    }

    public void setMovedOutFromFooter(boolean movedOutFromFooter) {
        this.movedOutFromFooter = movedOutFromFooter;
    }
    // </editor-fold>
    
    public PuzzlePiece(int id, ToothHeightsModel toothHeights,ImageView image, double width, double height){
        this.puzzle=image;
        this.puzzleId=id;
        this.getChildren().add(puzzle);
        this.movedOutFromFooter=false;
        this.isOnRightPosition=false;
        this.puzzlePieceWidth=width;
        this.puzzlePieceHeight=height;
        this.toothHeights=toothHeights;
        
       this.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                orgSceneX=event.getSceneX();
                orgSceneY=event.getSceneY();
                orgTranslateX = ((PuzzlePiece)(event.getSource())).getTranslateX();
                orgTranslateY = ((PuzzlePiece)(event.getSource())).getTranslateY();
                //System.out.println(orgSceneX+","+orgSceneY+","+orgTranslateX+","+orgTranslateY);
            }
       });
       
        this.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!isOnRightPosition){
                    double offsetX = event.getSceneX() - orgSceneX;
                    double offsetY = event.getSceneY() - orgSceneY;
                    newTranslateX = orgTranslateX + offsetX;
                    newTranslateY = orgTranslateY + offsetY;

                    ((PuzzlePiece)(event.getSource())).setTranslateX(newTranslateX);
                    ((PuzzlePiece)(event.getSource())).setTranslateY(newTranslateY);

                    if(calculateDistanceToCorrectPosition()<50){
                     //   ((PuzzlePiece)(event.getSource())).setTranslateX(correctX);
                      //  ((PuzzlePiece)(event.getSource())).setTranslateY(correctY);
                      //  isOnRightPosition=true;
                    }
                }
            }
       });
        
        this.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                puzzlePositionChangeListener.positionChanged(PuzzlePiece.this);
            }
       });       
    }
    
    public int getPuzzleId(){
        return this.puzzleId;
    }
}
