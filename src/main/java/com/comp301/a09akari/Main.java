package com.comp301.a09akari;

import com.comp301.a09akari.model.*;import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {

    PuzzleLibrary library = new PuzzleLibraryImpl();
    // Application.launch(AppLauncher.class);
    int[][] PUZZLE_01 = {
      {6, 6, 6},
      {6, 6, 6},
      {6, 6, 6}
    };
    Puzzle puzzle = new PuzzleImpl(PUZZLE_01);
    library.addPuzzle(puzzle);

    Model model = new ModelImpl(library);
    model.addLamp(0, 0);
    model.addLamp(0, 0);
    model.removeLamp(0, 0);
    model.removeLamp(1,0);
  }
}
