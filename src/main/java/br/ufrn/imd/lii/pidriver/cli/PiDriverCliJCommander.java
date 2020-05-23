package br.ufrn.imd.lii.pidriver.cli;

import br.ufrn.imd.lii.pidriver.dao.PiItemValueDAO;
import br.ufrn.imd.lii.pidriver.dao.jdbc.JDBCPiItemValueDAO;
import br.ufrn.imd.lii.pidriver.format.csv.CsvPiValueWriter;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import br.ufrn.imd.lii.pidriver.util.FileUtil;
import br.ufrn.imd.lii.pidriver.util.JavaUtil;
import com.beust.jcommander.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.util.Pair;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PiDriverCliJCommander {

    public class OutputFormatConverter implements IStringConverter<OutputFormat> {
        @Override
        public OutputFormat convert(String value) {
            switch(value) {
                case "csv":
                    return OutputFormat.CSV;
                default:
                    return OutputFormat.CSV;
            }
        }
    }

    public class InsertValuesInputFormatConverter implements IStringConverter<InsertValuesInputFormat> {
        @Override
        public InsertValuesInputFormat convert(String value) {
            switch(value) {
                case "csv":
                    return InsertValuesInputFormat.CSV;
                default:
                    return InsertValuesInputFormat.CSV;
            }
        }
    }

    public static class OutputFormatValidator implements IParameterValidator {
        public void validate(String name, String value) throws ParameterException {
            Set<String> possibleOutputFormats = Arrays.stream(JavaUtil.getNames(OutputFormat.class)).map(String::toLowerCase).collect(Collectors.toSet());
            if (!possibleOutputFormats.contains(value))
                throw new ParameterException("Allowed output format(s) is(are) " + String.join(", ", possibleOutputFormats));
        }
    }

    public static class InsertValuesInputFormatValidator implements IParameterValidator {
        public void validate(String name, String value) throws ParameterException {
            Set<String> possibleOutputFormats = Arrays.stream(JavaUtil.getNames(InsertValuesInputFormat.class)).map(String::toLowerCase).collect(Collectors.toSet());
            if (!possibleOutputFormats.contains(value))
                throw new ParameterException("Allowed output format(s) is(are) " + String.join(", ", possibleOutputFormats));
        }
    }

    public static class FileExistenceValidator implements IParameterValidator {
        public void validate(String name, String value) throws ParameterException {
            if (!FileUtil.canWriteInFile(value))
                throw new ParameterException("You provided an invalid file path");
        }
    }

    public static class FileInputExistenceValidator implements IParameterValidator {
        public void validate(String name, String value) throws ParameterException {
            if (!FileUtil.pathExists(value))
                throw new ParameterException("You provided an invalid file path");
        }
    }

    @Parameter(names = {CliDefs.LONG_OPT_PI_HOST, CliDefs.SHORT_OPT_PI_HOST},
            description = CliDefs.OPT_DESC_PI_HOST, required=true, order=0)
    private String piHost;

    @Parameter(order=1, names = {CliDefs.LONG_OPT_DAS_HOST, CliDefs.SHORT_OPT_DAS_HOST}, description = CliDefs.OPT_DESC_DAS_HOST, required=true)
    private String dasHost;

    @Parameter(order=2, names = {CliDefs.LONG_OPT_PI_USER, CliDefs.SHORT_OPT_PI_USER}, description = CliDefs.OPT_DESC_PI_USER, required=true)
    private String piUser;

    @Parameter(order=3, names = {CliDefs.LONG_OPT_PI_PASS, CliDefs.SHORT_OPT_PI_PASS}, description = CliDefs.OPT_DESC_PI_PASS, required=true)
    private String piPass;

    @Parameter(order=4, names = CliDefs.HELP_PARAM, help = true, description = "Display command help")
    private boolean help;

    @Parameters(separators = "=", commandDescription = "Insert values in PI from a source file")
    public class InsertValuesCommand {

        @Parameter(order=0, names={CliDefs.LONG_OPT_FROM_PATH, CliDefs.SHORT_OPT_FROM_PATH},
                validateWith = FileInputExistenceValidator.class, description = CliDefs.OPT_DESC_FROM_PATH)
        private String fromPath;

        @Parameter(order=1, names={CliDefs.LONG_OPT_INPUT_FORMAT, CliDefs.SHORT_OPT_INPUT_FORMAT}, description=CliDefs.OPT_DESC_INPUT_FORMAT,
                converter = OutputFormatConverter.class, required = false, validateWith = InsertValuesInputFormatValidator.class)
        private InsertValuesInputFormat inputFormat = InsertValuesInputFormat.CSV;
    }

    @Parameters(separators = "=", commandDescription = "Search for values in PI")
    public class SearchValuesCommand {

        @Parameter(order=0, names={CliDefs.LONG_OPT_TAG, CliDefs.SHORT_OPT_TAG}, description=CliDefs.OPT_DESC_TAG, required=true)
        private String tag;

        @Parameter(order=1, names={CliDefs.LONG_OPT_FROM, CliDefs.SHORT_OPT_FROM}, description=CliDefs.OPT_DESC_FROM)
        private Date initDate;

        @Parameter(order=2, names={CliDefs.LONG_OPT_TO, CliDefs.SHORT_OPT_TO}, description=CliDefs.OPT_DESC_TO)
        private Date endDate;

        @Parameter(order=3, names={CliDefs.LONG_OPT_MINV, CliDefs.SHORT_OPT_MINV}, description=CliDefs.OPT_DESC_MINV)
        private Float minValue;

        @Parameter(order=4, names={CliDefs.LONG_OPT_CLOSED_MNV, CliDefs.SHORT_OPT_CLOSED_MNV}, description=CliDefs.OPT_DESC_CLOSED_MNV)
        private Boolean minValueClosed = false;

        @Parameter(order=5, names={CliDefs.LONG_OPT_MAXV, CliDefs.SHORT_OPT_MAXV}, description=CliDefs.OPT_DESC_MAXV)
        private Float maxValue;

        @Parameter(order=6, names={CliDefs.LONG_OPT_CLOSED_MXV, CliDefs.SHORT_OPT_CLOSED_MXV}, description=CliDefs.OPT_DESC_CLOSED_MXV)
        private Boolean maxValueClosed = false;

        @Parameter(order=7, names={CliDefs.LONG_OPT_LIMIT, CliDefs.SHORT_OPT_LIMIT}, description=CliDefs.OPT_DESC_LIMIT)
        private Integer limit;

        @Parameter(order=8, names={CliDefs.LONG_OPT_OUTPUT_FORMAT, CliDefs.SHORT_OPT_OUTPUT_FORMAT}, description=CliDefs.OPT_DESC_OUTPUT_FORMAT,
        converter = OutputFormatConverter.class, required = false, validateWith = OutputFormatValidator.class)
        private OutputFormat output = OutputFormat.CSV;

        @Parameter(order=8, names={CliDefs.LONG_OPT_OUTPUT_PATH, CliDefs.SHORT_OPT_OUTPUT_PATH}, description=CliDefs.OPT_DESC_OUTPUT_PATH, required = false,
        validateWith = FileExistenceValidator.class)
        private String outputPath;
    }

    public SearchValuesCommand searchValCmd = new SearchValuesCommand();
    public InsertValuesCommand insertValCmd = new InsertValuesCommand();

    public JCommander getCommander(PiDriverCliJCommander cli) {
        JCommander jc = JCommander.newBuilder()
                .addObject(cli)
                .addCommand(CliDefs.SEARCH_VALUES_CMD, searchValCmd)
                .addCommand(CliDefs.INSERT_VALUES_CMD, insertValCmd)
                .build();
        return jc;
    }

    private String adjustPassword(String pass){
        if (pass != null && pass.equals("-"))
            return "";
        return pass;
    }

    private <T, R> Optional<R> makeOptional(T compObj, R innerObj) {
        return ((compObj != null) ? Optional.of(innerObj) : Optional.empty());
    }

    public void globalValidation(JCommander jc, PiDriverCliJCommander cli) {
        if (jc.getParsedCommand() != null) {
            switch(jc.getParsedCommand()) {
                case CliDefs.SEARCH_VALUES_CMD:
                    if (cli.searchValCmd.output == OutputFormat.CSV && cli.searchValCmd.outputPath == null)
                        throw new ParameterException("You must provide an output path");
                    break;
            }
        }
    }

    public void execute(JCommander jc, PiDriverCliJCommander cli) {

        globalValidation(jc, cli);

        cli.piPass = adjustPassword(cli.piPass);

        if (cli.help) {
            jc.usage(); return;
        }

        if (jc.getParsedCommand() == null) {
            throw new ParameterException("You must provide a command");
        }

        switch(jc.getParsedCommand()) {
            case CliDefs.SEARCH_VALUES_CMD:
                try(PiItemValueDAO dao = new JDBCPiItemValueDAO(cli.dasHost, cli.piHost, cli.piUser, cli.piPass)) {
                    Optional initDate = makeOptional(cli.searchValCmd.initDate, new Pair(cli.searchValCmd.initDate, true));
                    Optional endDate = makeOptional(cli.searchValCmd.endDate, new Pair(cli.searchValCmd.endDate, true));
                    Optional limit = makeOptional(cli.searchValCmd.limit, cli.searchValCmd.limit);
                    Optional minValue = makeOptional(cli.searchValCmd.minValue, new Pair(cli.searchValCmd.minValue, cli.searchValCmd.minValueClosed));
                    Optional maxValue = makeOptional(cli.searchValCmd.maxValue, new Pair(cli.searchValCmd.maxValue, cli.searchValCmd.maxValueClosed));
                    List<PiItemValue> items = dao.search(cli.searchValCmd.tag, initDate, endDate, limit, minValue, maxValue);
                    processItemValueOutput(items, cli.searchValCmd.output, cli.searchValCmd.outputPath);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CliDefs.INSERT_VALUES_CMD:
                try(PiItemValueDAO dao = new JDBCPiItemValueDAO(cli.dasHost, cli.piHost, cli.piUser, cli.piPass)) {
                    String fromPath = cli.insertValCmd.fromPath;
                    InsertValuesInputFormat inputFormat = cli.insertValCmd.inputFormat;
                    System.out.println("Asked to insert values from " + fromPath);
                    CsvPiValueWriter csvParser = new CsvPiValueWriter();
                    List<PiItemValue> items;
                    switch(inputFormat) {
                        case CSV:
                            items = csvParser.read(fromPath);
                            break;
                        default:
                            items = csvParser.read(fromPath);
                    }
                    dao.insert(items);
                    System.out.println("A total of " + items.size() + " values were inserted.");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void processItemValueOutput(List<PiItemValue> items, OutputFormat output, String path) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        switch (output) {
            case CSV:
                CsvPiValueWriter csvWriter = new CsvPiValueWriter();
                csvWriter.write(items, path);
        }
    }

    public static void main(String[] args) throws Exception {
        PiDriverCliJCommander cli = new PiDriverCliJCommander();
        JCommander jc = cli.getCommander(cli);
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

}
