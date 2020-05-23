package br.ufrn.imd.lii.analysis;

import br.ufrn.imd.lii.common.utils.ListUtils;
import br.ufrn.imd.lii.common.utils.MathUtils;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.javatuples.Pair;

import java.awt.geom.Point2D;
import java.util.List;

public class LinearInterpolationDeltaStepsComputer implements DeltaStepsComputer {

    private List<PiItemValue> allValues;
    private Double setValue;
    private Double normalValue;

    public LinearInterpolationDeltaStepsComputer(List<PiItemValue> allValues, Double setValue, Double normalValue) {
        this.allValues = allValues;
        this.setValue = setValue;
        this.normalValue = normalValue;
    }

    @Override
    public Pair<Integer, Integer> computeSteps(List<PiItemValue> inBetweenValues, Integer leftIndex, Integer rightIndex) {
        List<Pair<Integer, PiItemValue>> maxItems = ListUtils.findMaxItems(inBetweenValues, (i1, i2) -> Double.valueOf(i1.getDoubleValue()) < Double.valueOf(i2.getDoubleValue()));
        if (!maxItems.isEmpty()) {
            // left part
            Point2D.Double left = new Point2D.Double(leftIndex, allValues.get(leftIndex).getDoubleValue());
            Point2D.Double maxLeft = new Point2D.Double(maxItems.get(0).getValue0(), Double.valueOf(maxItems.get(0).getValue1().getDoubleValue()));
            Integer leftSteps = getStepsByLinearInterp(left, maxLeft, normalValue);
            // right part
            Point2D.Double right = new Point2D.Double(rightIndex, Double.valueOf(allValues.get(rightIndex).getDoubleValue()));
            Point2D.Double maxRight = new Point2D.Double(maxItems.get(maxItems.size() - 1).getValue0(), Double.valueOf(maxItems.get(maxItems.size() - 1).getValue1().getDoubleValue()));
            Integer rightSteps = getStepsByLinearInterp(right, maxRight, normalValue);
            return Pair.with(leftSteps, rightSteps);
        } else {
            return Pair.with(0, 0);
        }
    }

    private Integer getStepsByLinearInterp(Point2D.Double p0, Point2D.Double p1, Double normalValue) {
        Point2D.Double coeffs = MathUtils.computeLinearCoefficients(p0, p1);
        Integer steps = Math.abs(MathUtils.inferXFromLinearCoefficient(coeffs, normalValue).intValue());
        return steps;
    }
}