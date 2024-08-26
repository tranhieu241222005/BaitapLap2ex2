package iuh.fit.KTPM;

import java.util.Scanner;

/**
 * @description :
 * @author: Tran Hieu
 * @version: 1.0
 * @created :  25/08/2024 9:17 SA
 */
public class TestRectangle {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the rectangle: ");
        r1.setLength(sc.nextDouble());
        System.out.println("Enter the width of the rectangle: ");
        r1.setWidth(sc.nextDouble());
        System.out.println("Area r1: " + (Double)r1.getArea());
        System.out.println("Perimeter r1: " + (Double)r1.getPerimeter());
        System.out.println("Area r2: " + (Double)r1.getArea());
        System.out.println("Rectangle: " + r1.toString());
    }
}
