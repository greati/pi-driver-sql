package br.ufrn.imd.lii.pidriver;

import br.ufrn.imd.lii.analysis.LinearInterpolationDeltaStepsComputer;
import br.ufrn.imd.lii.analysis.OverSetPeriodSearch;
import br.ufrn.imd.lii.common.Period;
import br.ufrn.imd.lii.pidriver.format.csv.CsvPiValueWriter;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.junit.Test;

import java.util.List;

public class OverSetPeriodSearchTest {


    @Test
    public void overSetPeriodLinearTest() {

        String csvPath = "";
        CsvPiValueWriter csvParser = new CsvPiValueWriter();
        try {
            List<PiItemValue> items = csvParser.read(csvPath);
            LinearInterpolationDeltaStepsComputer deltaStepsComputer =
                    new LinearInterpolationDeltaStepsComputer(null,null,null);
            OverSetPeriodSearch periodSearch = new OverSetPeriodSearch(deltaStepsComputer);
            List<Period> periods = periodSearch.searchOverSetPeriods(items, 0.7, 600);
            System.out.println(periods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
