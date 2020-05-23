package br.ufrn.imd.lii.analysis;

import br.ufrn.imd.lii.common.Period;
import br.ufrn.imd.lii.common.utils.DateUtil;
import br.ufrn.imd.lii.common.utils.ListUtils;
import br.ufrn.imd.lii.common.utils.MathUtils;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Search for periods in which the values were
 * over the set value.
 */
public class OverSetPeriodSearch {

    private DeltaStepsComputer deltaStepsComputer;

    public OverSetPeriodSearch(DeltaStepsComputer deltaStepsComputer) {
        this.deltaStepsComputer = deltaStepsComputer;
    }

    /**
     * Given a set of PiItemValue and a set value, search
     * all periods (t0,t1) in which they were over the
     * value. More than that, search for suitable
     * delta1 and delta2 such that (t0-delta1, t1+delta2)
     * represents a more complete scenario in which the values were
     * above the set value.
     *
     *        /---\
     *       /     \
     * -t0--/       \--t1--------
     *
     * @param values
     * @return
     */
    public List<Period> searchOverSetPeriods(List<PiItemValue> values,
                                             Double setValue,
                                             Integer timeStepSeconds) throws ParseException {

        Integer currentIndex = 0;
        List<Period> periods = new ArrayList<>();
        while (currentIndex < values.size()) {

            // search first value above the set
            Triplet<Optional<Integer>, Integer, List<PiItemValue>> belowValues = ListUtils.collectUntil(values, currentIndex,
                    item -> item.getDoubleValue() < setValue);

            //-- break if not find
            if (!belowValues.getValue0().isPresent())
                break;

            currentIndex += belowValues.getValue1() + 1;

            // collect all points above the set value, stopping at the first value below the set
            Triplet<Optional<Integer>, Integer, List<PiItemValue>> aboveValues = ListUtils.collectUntil(values, currentIndex,
                    item -> item.getDoubleValue() >= setValue);
            // -- if we only keep above the set, we do not count as a period, and break
            if (!aboveValues.getValue0().isPresent())
                break;

            // we have the period, now we compute the delta steps
            Pair<Integer, Integer> deltaSteps = deltaStepsComputer.computeSteps(
                    aboveValues.getValue2(),
                    belowValues.getValue0().get(),
                    belowValues.getValue0().get());
            // compute period using the delta steps
            //-- original values
            Date startDate = DateUtil.fromString(values.get(belowValues.getValue0().get()).getTime());
            Date endDate = DateUtil.fromString(values.get(aboveValues.getValue0().get()).getTime());
            //-- dates with deltas
            startDate = DateUtil.subtractSeconds(startDate, deltaSteps.getValue0() * timeStepSeconds);
            endDate = DateUtil.addSeconds(endDate, deltaSteps.getValue1() * timeStepSeconds);
            //-- add period
            periods.add(new Period(startDate, endDate));
            // keep going
            currentIndex += aboveValues.getValue1() + 1;
        }
        return periods;
    }
}
