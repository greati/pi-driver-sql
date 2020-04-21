package br.ufrn.imd.lii.pidriver.format.csv;

import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sun.deploy.ref.Helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class CsvPiValueWriter {

    public void write(List<PiItemValue> items, String path) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        // Get CSV objects
        List<PiItemValueCsv> csvItems = items.stream().map(obj -> {
            PiItemValueCsv csvBean = new PiItemValueCsv();
            csvBean.from(obj);
            return csvBean;
        }).collect(Collectors.toList());

        Writer writer  = new FileWriter(path);

        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        sbc.write(csvItems);
        writer.close();
    }

}
