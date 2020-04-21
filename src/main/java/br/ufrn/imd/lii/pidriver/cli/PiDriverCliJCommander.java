package br.ufrn.imd.lii.pidriver.cli;

import br.ufrn.imd.lii.pidriver.dao.PiItemValueDAO;
import br.ufrn.imd.lii.pidriver.dao.jdbc.JDBCPiItemValueDAO;
import br.ufrn.imd.lii.pidriver.dao.jdbc.PiDriver;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class PiDriverCliJCommander {

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
    }

    public SearchValuesCommand searchValCmd = new SearchValuesCommand();

    public JCommander getCommander(PiDriverCliJCommander cli) {
        JCommander jc = JCommander.newBuilder()
                .addObject(cli)
                .addCommand(CliDefs.SEARCH_VALUES_CMD, searchValCmd)
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

    public void execute(JCommander jc, PiDriverCliJCommander cli) {
        cli.piPass = adjustPassword(cli.piPass);

        if (cli.help) {
            jc.usage();
            return;
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
                    dao.search(cli.searchValCmd.tag, initDate, endDate, limit, minValue, maxValue);
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
