package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    Model model = new ModelImpl(puzzleLibrary);
    ClassicMvcController controller = new ControllerImpl(model);
    MainView view = new MainView(model, controller);

    stage.setScene(view.getScene());

    stage.setTitle("Akari");
    stage.show();
  }
}
