package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControllerView implements FXComponent, ModelObserver {
  private final Model model;
  private final ClassicMvcController controller;
  private Button nextButton;
  private Button prevButton;
  private Button resetButton;
  private Button randomButton;

  public ControllerView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public void update(Model model) {}

  @Override
  public Parent render() {
    HBox pane = new HBox();
    pane.getChildren().clear();
    pane.setAlignment(Pos.CENTER);

    if (prevButton == null) {
      prevButton = new Button("Previous Puzzle");
      prevButton.setOnAction((ActionEvent event) -> controller.clickPrevPuzzle());
    }
    pane.getChildren().add(prevButton);

    if (randomButton == null) {
      randomButton = new Button("Random Puzzle");
      randomButton.setOnAction((ActionEvent event) -> controller.clickRandPuzzle());
    }
    pane.getChildren().add(randomButton);

    if (resetButton == null) {
      resetButton = new Button("Reset Puzzle");
      resetButton.setOnAction((ActionEvent event) -> controller.clickResetPuzzle());
    }
    pane.getChildren().add(resetButton);

    if (nextButton == null) {
      nextButton = new Button("Next Puzzle");
      nextButton.setOnAction((ActionEvent event) -> controller.clickNextPuzzle());
    }
    pane.getChildren().add(nextButton);

    pane.setStyle("-fx-padding: 10 0 0 0;");

    return pane;
  }
}
