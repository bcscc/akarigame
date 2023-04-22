package com.comp301.a09akari;

import com.comp301.a09akari.model.*;import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {

    //Application.launch(AppLauncher.class);
    PuzzleLibrary library = new PuzzleLibraryImpl();
    int[][] PUZZLE_01 = {
      {6, 5, 6},
      {6, 6, 6},
      {6, 6, 6}
    };
    Puzzle puzzle = new PuzzleImpl(PUZZLE_01);
    library.addPuzzle(puzzle);

    Model model = new ModelImpl(library);
    model.addLamp(0, 0);
    System.out.println(model.isLampIllegal(2, 0));
    model.addLamp(0,2);
    System.out.println(model.isLampIllegal(0, 2));
  }
}
