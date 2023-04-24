package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent, ModelObserver {
  private final Model model;
  private final ClassicMvcController controller;
  private Label solvedLabel;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
    this.solvedLabel = new Label("Puzzle Solved!");
    this.solvedLabel.setStyle("-fx-font-size: 25px; -fx-padding: 15 0 0 0; -fx-text-fill: green;");
    this.solvedLabel.setVisible(false);
  }

  @Override
  public void update(Model model) {}

  @Override
  public Parent render() {
    VBox pane = new VBox();
    pane.getChildren().clear();
    pane.setAlignment(Pos.CENTER);

    Label title = new Label("Welcome to Akari!");
    title.setStyle("-fx-font-size: 30px; -fx-padding: 40 0 0 0;");
    pane.getChildren().add(title);

    Label puzzleIdx = new Label("Puzzle " + (model.getActivePuzzleIndex() + 1));
    puzzleIdx.setStyle("-fx-font-size: 20px; -fx-padding: 15 0 0 0;");
    pane.getChildren().add(puzzleIdx);
    System.out.println(model.isSolved());
    solvedLabel.setVisible(model.isSolved());
    pane.getChildren().add(solvedLabel);

    GridPane grid = makePuzzle();
    grid.setStyle("-fx-padding: 10 0 0 0;");

    pane.getChildren().add(grid);
    return pane;
  }

  public GridPane makePuzzle() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);

    for (int r = 0; r < model.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < model.getActivePuzzle().getWidth(); c++) {
        final int row = r;
        final int col = c;
        Button cell;
        if (model.getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
          cell = new Button("" + model.getActivePuzzle().getClue(r, c));
          cell.setDisable(true);
          cell.setStyle("-fx-border-color: lightgray; -fx-background-color: blue; -fx-text-fill: white;");
        } else if (model.getActivePuzzle().getCellType(r, c) == CellType.WALL) {
          cell = new Button();
          cell.setDisable(true);
          cell.setStyle("-fx-border-color: lightgray; -fx-background-color: black;");
        } else {
          cell = new Button();
          if (model.isLit(r, c)) {
            cell.setStyle("-fx-border-color: lightgray; -fx-background-color: #FFFF1C;");
          }
          if (model.isLamp(r, c)) {
            cell.setStyle("-fx-border-color: lightgray; -fx-background-color: #FFFF1C;");
            Image img;
            ImageView imageView;
            if (model.isLampIllegal(r, c)) {
              imageView = new ImageView();
              ColorAdjust colorAdjust = new ColorAdjust();
              colorAdjust.setSaturation(-1);
              img = new Image("light-bulb.png");
              imageView.setEffect(colorAdjust);
            } else {
              imageView = new ImageView();
              img = new Image("light-bulb.png");
            }
            imageView.setImage(img);
            imageView.setFitHeight(30);
            imageView.setPreserveRatio(true);
            cell.setGraphic(imageView);
          }
        }
        cell.setOnAction((ActionEvent event) -> controller.clickCell(row, col));
        cell.setMinSize(50, 50);
        grid.add(cell, c, r);
      }
    }
    return grid;
  }
}
