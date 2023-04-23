package com.comp301.a09akari;

import com.comp301.a09akari.model.*;import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {

    Application.launch(AppLauncher.class);

    /*PuzzleLibrary library = new PuzzleLibraryImpl();
    int[][] PUZZLE_01 = {
      {6, 6, 6, 6},
      {6, 4, 6, 6},
      {6, 6, 6, 6}
    };
    Puzzle puzzle = new PuzzleImpl(PUZZLE_01);
    library.addPuzzle(puzzle);
    Model model = new ModelImpl(library);


    model.addLamp(0,1);
    model.addLamp(1, 0);
    model.addLamp(1, 2);
    model.addLamp(2, 1);


    System.out.println("should be true");
    System.out.println( model.isLit(0,0));
    System.out.println(model.isClueSatisfied(1, 1));
    System.out.println(model.isLit(0,2));
    System.out.println(model.isLit(1,0));
    System.out.println(model.isLit(1,2));
    System.out.println(model.isLit(2,0));
    System.out.println(model.isLit(2,1));
    System.out.println(model.isLit(2,2));
    System.out.println(model.isSolved());

     */


  }
}
