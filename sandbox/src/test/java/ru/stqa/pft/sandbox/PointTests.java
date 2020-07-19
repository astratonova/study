package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testDistanceForSamePoints() {
    Point p1 = new Point(10, 4);
    Point p2 = new Point(10, 4);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDistanceNegativeCoordinates() {
    Point p1 = new Point(-6, 0);
    Point p2 = new Point(-2, 3);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testDistanceStraight() {
    Point p1 = new Point(7, 3);
    Point p2 = new Point(-2, 3);
    Assert.assertEquals(p1.distance(p2), 9.0);
  }

  @Test
  public void testDistanceFormat1() {
    Point p1 = new Point(7, 3);
    Point p2 = new Point(99999999.9, 3);
    Assert.assertEquals(p1.distance(p2), 9.99999929E7);
  }

  @Test
  public void testDistanceFormat() {
    Point p1 = new Point(7, 3);
    Point p2 = new Point(99999999.9, 3);
    Assert.assertEquals(p1.distance(p2), 99999992.9);
  }
}