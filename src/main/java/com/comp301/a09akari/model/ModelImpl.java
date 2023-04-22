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
      //return isLightClear(p, r, c);
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

  public void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  public boolean isLightClear(Pair<Integer, Integer> lamp, int r, int c) {
    if (lamp.getKey() == r && lamp.getValue() == c) {
      return true;
    } else if (lamp.getKey() == r) {
      int start = lamp.getValue();
      boolean blocked = false;
      while (!blocked && start != c) {
        if (c < start) {
          if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, start - 1)
              != CellType.CORRIDOR) {
            blocked = true;
          }
          start -= 1;
        } else {
          if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(r, start + 1)
              != CellType.CORRIDOR) {
            blocked = true;
          }
          start += 1;
        }
      }
      return !blocked;
    } else {
      int start = lamp.getKey();
      boolean blocked = false;
      while (!blocked && start != r) {
        if (r < start) {
          if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(start - 1, c)
              != CellType.CORRIDOR) {
            blocked = true;
          }
          start -= 1;
        } else {
          if (puzzleLibrary.getPuzzle(activePuzzleIdx).getCellType(start + 1, c)
              != CellType.CORRIDOR) {
            blocked = true;
          }
          start += 1;
        }
      }
      return !blocked;
    }
  }
}
