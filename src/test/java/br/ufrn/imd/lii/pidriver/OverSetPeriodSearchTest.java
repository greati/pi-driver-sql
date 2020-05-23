package br.ufrn.imd.lii.pidriver;

import br.ufrn.imd.lii.analysis.LinearInterpolationDeltaStepsComputer;
import br.ufrn.imd.lii.analysis.OverSetPeriodSearch;
import br.ufrn.imd.lii.pidriver.format.csv.CsvPiValueWriter;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.junit.Test;

public class OverSetPeriodSearchTest {


    @Test
    public void overSetPeriodLinearTest() {

        String csvPath = "";
        CsvPiValueWriter csvParser = new CsvPiValueWriter();
        LinearInterpolationDeltaStepsComputer deltaStepsComputer =
                new LinearInterpolationDeltaStepsComputer(null,null,null);
        OverSetPeriodSearch periodSearch = new OverSetPeriodSearch(deltaStepsComputer);

    }

}
