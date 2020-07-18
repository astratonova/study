package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("I'm smarter than a monkey:)");
    double x1=3;
    double x2=0;
    double y1=4;
    double y2=0;
    Point p1 = new Point(x1,y1);
    Point p2 = new Point(x2,y2);
    double d = distance(p1,p2);
    System.out.println("Расстояние между 2 точками с координатами"+"("+x1+";"+y1+")"+"и"+"("+x2+";"+y2+")"+"="+d);
  }

  public static double distance(Point p1, Point p2) {
    double d;
    d = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    return d;
  }
}