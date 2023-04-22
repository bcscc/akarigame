package com.comp301.a09akari;

import static org.junit.Assert.assertTrue;

import com.comp301.a09akari.model.*;
import org.junit.Test;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void testIsClueSatisfied() {
    PuzzleLibrary pl = new PuzzleLibraryImpl();
    Puzzle p = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    pl.addPuzzle(p);
    ModelImpl m = new ModelImpl(pl);

    m.addLamp(2, 0);
    m.addLamp(1, 1);
    m.addLamp(2, 2);
    assertTrue(m.isClueSatisfied(2, 1));

    try {
      m.isClueSatisfied(0, 0);
    } catch (IllegalArgumentException e) {
    }

    m.removeLamp(1, 1);
    assertTrue(!m.isClueSatisfied(2, 1));

    assertTrue(m.isClueSatisfied(4, 4));
  }

}
