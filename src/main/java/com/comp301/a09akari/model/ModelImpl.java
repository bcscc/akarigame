package com.comp301.a09akari.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelImpl implements Model {

  PuzzleLibrary puzzleLibrary;
  List<ModelObserver> observers;
  int activePuzzleIdx;
  Set<Pair<Integer, Integer>> lamps;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    puzzleLibrary = library;
    observers = new ArrayList<>();
    activePuzzleIdx = 0;
    lamps = new HashSet<>();
    // Your constructor code here
  }

  @Override
  public void addLamp(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (!lamps.contains(new Pair<>(r, c))) {
      lamps.add(new Pair<>(r, c));
    }
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps.remove(new Pair<>(r, c));
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    for (Pair<Integer, Integer> p : lamps) {
      if (isLightClear(p, r, c)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps.contains(new Pair<>(r, c));
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }

    for (Pair<Integer, Integer> otherLamp : lamps) {
      if (!otherLamp.equals(new Pair<>(r, c)) && isLightClear(otherLamp, r, c)) {
        return true;
      }
    }

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
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    lamps = new HashSet<>();
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int r = 0; r < puzzleLibrary.getPuzzle(activePuzzleIdx).getHeight(); r++) {
      for (int c = 0; c < puzzleLibrary.getPuzzle(activePuzzleIdx).getWidth(); c++) {
        if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) == CellType.CLUE) {
          if (!isClueSatisfied(r, c)) {
            return false;
          }
        }
        if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) == CellType.CORRIDOR) {
          if (!isLit(r, c)) {
            return false;
          }
          if (isLamp(r, c) && isLampIllegal(r, c)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int numLumps = 0;
    for (Pair<Integer, Integer> p : lamps) {
      if ((p.getKey() == r - 1) && (p.getValue() == c)) {
        numLumps += 1;
      }
      if ((p.getKey() == r + 1) && (p.getValue() == c)) {
        numLumps += 1;
      }
      if ((p.getKey() == r) && (p.getValue() == c - 1)) {
        numLumps += 1;
      }
      if ((p.getKey() == r) && (p.getValue() == c + 1)) {
        numLumps += 1;
      }
    }
    return numLumps == puzzleLibrary.getPuzzle(activePuzzleIdx).getClue(r, c);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  public boolean isLightClear(Pair<Integer, Integer> lamp, int r, int c) {
    if (lamp.getKey() == r && lamp.getValue() == c) {
      return true;
    }
    if (lamp.getKey() == r) {
      int minCol = Math.min(lamp.getValue(), c);
      int maxCol = Math.max(lamp.getValue(), c);
      for (int col = minCol + 1; col < maxCol; col++) {
        if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, col) != CellType.CORRIDOR) {
          return false;
        }
      }
      return true;
    } else if (lamp.getValue() == c) {
      int minRow = Math.min(lamp.getKey(), r);
      int maxRow = Math.max(lamp.getKey(), r);
      for (int row = minRow + 1; row < maxRow; row++) {
        if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(row, c) != CellType.CORRIDOR) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
}
