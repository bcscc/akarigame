package com.comp301.a09akari.model;

public class LampImpl implements Lamp {
  int row;
  int column;

  public LampImpl(int r, int c) {
    row = r;
    column = c;
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public int getRow() {
    return row;
  }
}
