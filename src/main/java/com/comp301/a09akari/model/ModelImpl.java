package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  PuzzleLibrary puzzleLibrary;
  List<ModelObserver> observers;
  int activePuzzleIdx;
  List<Lamp> lamps;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    puzzleLibrary = library;
    observers = new ArrayList<>();
    activePuzzleIdx = 0;
    lamps = new ArrayList<>();
    // Your constructor code here
  }

  @Override
  public void addLamp(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    Lamp newLamp = new LampImpl(r, c);
    if (!lamps.contains(newLamp)) {
      lamps.add(newLamp);
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    Lamp newLamp = new LampImpl(r, c);
    lamps.remove(newLamp);
  }

  @Override
  public boolean isLit(int r, int c) {
    // TODO
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps.contains(new LampImpl(r, c));
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    // TODO
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return puzzleLibrary.getPuzzle(activePuzzleIdx);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIdx;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= getPuzzleLibrarySize() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzleIdx = index;
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    lamps = new ArrayList<>();
  }

  @Override
  public boolean isSolved() {
    // TODO
    return false;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    // TODO
    return false;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }
}
