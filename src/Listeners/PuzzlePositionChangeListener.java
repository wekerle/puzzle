/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import ViewModels.PuzzlePiece;


/**
 *
 * @author tibor.wekerle
 */
public interface PuzzlePositionChangeListener 
{
    void positionChanged(PuzzlePiece puzzle);
}
