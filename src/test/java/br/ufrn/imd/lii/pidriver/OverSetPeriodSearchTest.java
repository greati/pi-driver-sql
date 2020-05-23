package br.ufrn.imd.lii.pidriver;

import br.ufrn.imd.lii.analysis.LinearInterpolationDeltaStepsComputer;
import br.ufrn.imd.lii.analysis.OverSetPeriodSearch;
import br.ufrn.imd.lii.common.Period;
import br.ufrn.imd.lii.pidriver.format.csv.CsvPiValueWriter;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.javatuples.Pair;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class OverSetPeriodSearchTest {


    @Test
    public void overSetPeriodLinearTest() {

        String csvPath = "/home/vitorgreati/git-repos/psv_flow/data/generated/psv/processed/full_test_psv_data.csv";
        CsvPiValueWriter csvParser = new CsvPiValueWriter();
        try {
            List<PiItemValue> items = csvParser.read(csvPath);
            List<PiItemValue> filteredItems = items.stream()
                    .filter(i -> i.getDoubleValue() >= 0.6)
                    .collect(Collectors.toList());
            LinearInterpolationDeltaStepsComputer deltaStepsComputer =
                    new LinearInterpolationDeltaStepsComputer(filteredItems,0.75,0.1);
            OverSetPeriodSearch periodSearch = new OverSetPeriodSearch(deltaStepsComputer);
            List<Period> periods = periodSearch.searchOverSetPeriods(
                    filteredItems,
                    0.75,
                    600,
                    Pair.with(86400, 86400));
            System.out.println(periods.size());
            System.out.println(periods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
