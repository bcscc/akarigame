package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;import javafx.scene.layout.BorderPane;

public class MainView implements FXComponent, ModelObserver {
  private final Scene scene;
  private final FXComponent TitleView;
  private final FXComponent MenuView;



  public MainView(Model model, ClassicMvcController controller) {
    this.scene = new Scene(render());
    model.addObserver(this);
  }

  @Override
  public Parent render() {
    BorderPane pane = new BorderPane();


    return null;
  }

  @Override
  public void update(Model model) {
    scene.setRoot(render());
  }

  public Scene getScene() {
    return scene;
  }
}
