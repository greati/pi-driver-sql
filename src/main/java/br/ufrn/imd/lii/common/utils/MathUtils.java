package br.ufrn.imd.lii.common.utils;


import java.awt.geom.Point2D;

public class MathUtils {

    public static Point2D.Double computeLinearCoefficients(Double alpha, Point2D.Double point) {
        return new Point2D.Double(alpha, point.getY() - alpha * point.getX());
    }

    public static Point2D.Double computeLinearCoefficients(Point2D.Double p1,
                                                           Point2D.Double p2) {
        Double alpha = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        return computeLinearCoefficients(alpha, p1);
    }

    public static Double inferYFromLinearCoefficient(Point2D.Double coeffs, Double x) {
        return coeffs.getX() * x + coeffs.getY();
    }

    public static Double inferXFromLinearCoefficient(Point2D.Double coeffs, Double y) {
        return (y - coeffs.getY()) / coeffs.getX();
    }

}
