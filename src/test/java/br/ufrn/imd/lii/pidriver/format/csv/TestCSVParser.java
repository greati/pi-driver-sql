package br.ufrn.imd.lii.pidriver.format.csv;

import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.junit.Test;

import java.util.List;

public class TestCSVParser {

    @Test
    public void testCsvReader() {
        String path = "C:\\Users\\Vitor Greati\\Desktop\\git-repos\\psv_flow\\data\\generated\\psv\\processed\\full_test_psv_data.csv";
        CsvPiValueWriter csvParser = new CsvPiValueWriter();
        try {
            List<PiItemValue> values = csvParser.read(path);
            System.out.println(values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
