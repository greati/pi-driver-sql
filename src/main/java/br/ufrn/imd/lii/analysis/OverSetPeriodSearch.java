package br.ufrn.imd.lii.analysis;

import br.ufrn.imd.lii.common.Period;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.javatuples.Pair;

import java.text.ParseException;
import java.util.List;

public interface OverSetPeriodSearch {

     List<Period> searchOverSetPeriods(List<PiItemValue> values,
                                       Double setValue,
                                       Integer timeStepSeconds,
                                       Pair<Integer, Integer> extraDeltas) throws ParseException;

}
