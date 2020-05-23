package br.ufrn.imd.lii.pidriver.format.csv;

import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvPiValueWriter {

    /**
     * Read PI item values from a CSV.
     *
     * @param strpath
     * @return
     */
    public List<PiItemValue> read(String strpath) throws Exception {
        Path path = Paths.get(strpath);
        try {
            List<CsvBean> r = this.beanBuilderExample(path, PiItemValueCsv.class);
            List<PiItemValue> result = r.stream().map(icsvi -> {
                return (PiItemValue) icsvi.getBean();
            }).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on CSV");
        }
    }

    public List<CsvBean> beanBuilderExample(Path path, Class clazz) throws Exception {
        Reader reader = Files.newBufferedReader(path);
        CsvToBean cb = new CsvToBeanBuilder(reader).withType(clazz).withIgnoreLeadingWhiteSpace(true).build();
        List<CsvBean> l = cb.parse();
        reader.close();
        return l;
    }

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
