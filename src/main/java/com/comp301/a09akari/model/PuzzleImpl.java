package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  int[][] board;

  public PuzzleImpl(int[][] board) {
    if (board == null) {
      throw new IllegalArgumentException();
    }
    this.board = board;
    // Your constructor code here
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0 || r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (board[r][c] >= 0 && board[r][c] < 5) {
      return CellType.CLUE;
    } else if (board[r][c] == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
