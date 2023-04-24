package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import java.util.Random;

public class ControllerImpl implements ClassicMvcController {

  private final Model model;

  public ControllerImpl(Model model) {
    if (model != null) {
      this.model = model;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void clickNextPuzzle() {
    if (model.getActivePuzzleIndex() + 1 < model.getPuzzleLibrarySize())
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
  }

  @Override
  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() - 1 >= 0)
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
  }

  @Override
  public void clickRandPuzzle() {
    int min = 0;
    int max = model.getPuzzleLibrarySize();
    Random random = new Random();
    int randomNumber = random.nextInt(max - min) + min;
    model.setActivePuzzleIndex(randomNumber);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }
}
