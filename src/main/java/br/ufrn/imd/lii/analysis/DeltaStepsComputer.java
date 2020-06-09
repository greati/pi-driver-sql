package br.ufrn.imd.lii.analysis;

import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.javatuples.Pair;

import java.util.List;

public interface DeltaStepsComputer {
    Pair<Integer, Integer> computeSteps(List<PiItemValue> inBetweenValues, Integer leftIndex, Integer rightIndex);
}