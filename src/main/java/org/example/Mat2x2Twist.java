package org.example;

public class Mat2x2Twist extends nXnMAtrix {
    private Mat2x2Twist(double[][] matEntries) {
        super(matEntries);
    }

    public static Mat2x2Twist createMat2x2Twist(double alpha) {
        double[][] matEntries = new double[][]{{Math.cos(alpha), -Math.sin(alpha)}, {Math.sin(alpha), Math.cos(alpha)}};
        return new Mat2x2Twist(matEntries);
    }
}
