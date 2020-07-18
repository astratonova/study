package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("I'm smarter than a monkey:)");
    double x1 = 3;
    double x2 = 0;
    double y1 = 2;
    double y2 = -2;
    Point p1 = new Point(x1, y1);
    Point p2 = new Point(x2, y2);
    //выводится расстояние между 2 точками с вызовом функции  public static double distance(Point p1, Point p2)
    System.out.println("Расстояние между 2 точками с координатами" + "(" + x1 + ";" + y1 + ")" + "и" + "(" + x2 + ";" + y2 + ")" + "=" + distance(p1, p2));
    //выводится расстояние между 2 точками с вызовом метода public double distance(Point p2) класса "Point"
    System.out.println("Расстояние между 2 точками с координатами" + "(" + p1.x + ";" + p1.y + ")" + "и" + "(" + p2.x + ";" + p2.y + ")" + "=" + p1.distance(p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
  }
}