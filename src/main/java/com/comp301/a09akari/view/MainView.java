package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MainView implements FXComponent, ModelObserver {
  private final Model model;
  private final ClassicMvcController controller;
  private final Scene scene;

  public MainView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
    this.scene = new Scene(render(), 800, 750);
    model.addObserver(this);
  }

  @Override
  public Parent render() {
    VBox pane = new VBox();

    PuzzleView puzzleView = new PuzzleView(model, controller);
    pane.getChildren().add(puzzleView.render());

    ControllerView controllerView = new ControllerView(model, controller);
    pane.getChildren().add(controllerView.render());

    return pane;
  }

  @Override
  public void update(Model model) {
    scene.setRoot(render());
  }

  public Scene getScene() {
    return scene;
  }
}
