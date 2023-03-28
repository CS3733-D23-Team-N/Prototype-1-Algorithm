package edu.wpi.teamname.tester;

import static org.junit.Assert.*;

import edu.wpi.teamname.controllers.AlgorithmController;
import org.junit.Test;

public class AlgorithmTester {

  private AlgorithmController controller = new AlgorithmController();

  @Test
  public void testNodeExistsFirst() {
    assertTrue(controller.nodeExists("Anesthesia Conf Floor L1"));
  }

  @Test
  public void testNodeExistsSecond() {
    assertTrue(controller.nodeExists("Medical Records Conference Room Floor L1"));
  }

  @Test
  public void testNodeExistsMiddle1() {
    assertTrue(controller.nodeExists("Hallway 13 Floor L1"));
  }

  @Test
  public void testNodeExistsMiddle2() {
    assertTrue(controller.nodeExists("Vending Machine 1 L1"));
  }

  @Test
  public void testNodeExistsSecondLast() {
    assertTrue(controller.nodeExists("Elevator L Floor L1"));
  }

  @Test
  public void testNodeExistsLast() {
    assertTrue(controller.nodeExists("Elevator M Floor L1"));
  }

  @Test
  public void testNodeDoesntExistNothing() {
    assertFalse(controller.nodeExists(""));
  }

  @Test
  public void testNodeDoesntExistCloseMiss() {
    assertFalse(controller.nodeExists("Bnesthesia Conf Floor L1"));
  }

  @Test
  public void testNodeDoesntExistRandom() {
    assertFalse(controller.nodeExists("asldfjhwop4iu;adsfj"));
  }

  @Test
    public void testBFS
}
