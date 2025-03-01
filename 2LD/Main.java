
// 211REC027 Jānis Lejnieks 4
import java.util.Scanner;

abstract class Figura {
    abstract double getArea();

    abstract double getPerimeter();
}

class Rectangle extends Figura {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double getArea() {
        return width * height;
    }

    double getPerimeter() {
        return 2 * (width + height);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Ievada taisnturu daudzumu
        System.out.print("Ievadi taisntūru sakitu: ");
        int numRectangles = sc.nextInt();

        Figura[] figures = new Figura[numRectangles];

        for (int i = 0; i < numRectangles; i++) {
            System.out.println("\nIevadi dimensiju skaitu taisnstūrim" + (i + 1) + ":");
            System.out.print("width: ");
            double width = sc.nextDouble();
            System.out.print("height: ");
            double height = sc.nextDouble();

            Rectangle rectangle = new Rectangle(width, height);
            figures[i] = rectangle;
        }
        for (int i = 0; i < numRectangles; i++) {
            double area = figures[i].getArea();
            double perimeter = figures[i].getPerimeter();

            System.out.printf("%.2f %.2f%n", area, perimeter);
        }
        sc.close();
    }
}