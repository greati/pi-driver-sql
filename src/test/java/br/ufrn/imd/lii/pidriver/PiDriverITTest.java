package br.ufrn.imd.lii.pidriver;

import br.ufrn.imd.lii.pidriver.dao.jdbc.PiDriver;
import br.ufrn.imd.lii.pidriver.sql.PiQuery;
import br.ufrn.imd.lii.pidriver.util.PIUtil;
import javafx.util.Pair;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

public class PiDriverITTest {

    private PiDriver getPiDriverTest() throws ClassNotFoundException {
        //TODO change this informations to your PI configuration.
        return new PiDriver("localhost", "192.168.0.18",  "pidemo", "");
    }

    @Test
    public void snapshotTest() throws ClassNotFoundException, SQLException {
        String tag = "SINUSOID";
        ResultSet resultSet = getPiDriverTest().executarQuery(PiQuery.snapshotQuery(tag));
        assertNotNull("The result can`t be null", resultSet);
    }

    @Test
    public void historicalTest() throws ClassNotFoundException, SQLException {
        String tag = "SINUSOID";
        //format yyyy-mm-dd
        Date initialDate = Date.valueOf("2020-01-01");
        Date finalDate = Date.valueOf("2020-01-02");
        //max points
        int points = 100;
        String query = PiQuery.historicalValuesQuery(tag, initialDate, finalDate, points);
        System.out.println(query);
        ResultSet resultSet = getPiDriverTest().executarQuery(query);
        PIUtil.printResultSet(resultSet);
        assertNotNull("The result can`t be null", resultSet);
    }

    @Test
    public void historicalWithMinValueTest() throws ClassNotFoundException, SQLException {
        String tag = "SINUSOID";
        //format yyyy-mm-dd
        Date initialDate = Date.valueOf("2020-01-01");
        Date finalDate = Date.valueOf("2020-01-02");
        //max points
        int points = 100;
        String query = PiQuery.historicalValuesQuery(tag, initialDate, finalDate, points,
                Optional.of(new Pair<Float,Boolean>(40.0f, false)),
                Optional.of(new Pair<Float,Boolean>(120.0f, true)));
        System.out.println(query);
        ResultSet resultSet = getPiDriverTest().executarQuery(query);
        PIUtil.printResultSet(resultSet);
        assertNotNull("The result can`t be null", resultSet);
    }

    @Test
    public void historicalInterpolated() throws ClassNotFoundException, SQLException {
        String tag = "SINUSOID";
        //format yyyy-mm-dd
        Date initialDate = Date.valueOf("2020-01-01");
        Date finalDate = Date.valueOf("2020-01-02");
        //sample rate
        String step = "1h";
        //quantity of samples.
        int points = 100;
        ResultSet resultSet = getPiDriverTest().executarQuery(PiQuery.interpolatedHistoricalValuesQuery(tag, initialDate, finalDate, points, step));
        PIUtil.printResultSet(resultSet);
        assertNotNull("The result can`t be null", resultSet);
    }

    @Test
    public void tagInfoTest() throws ClassNotFoundException, SQLException {
        String tag = "SINUSOID";
        //format yyyy-mm-dd
        Date initialDate = Date.valueOf("2020-01-01");
        Date finalDate = Date.valueOf("2020-01-02");
        //sample rate
        String step = "1h";
        //quantity of samples.
        int points = 100;
        ResultSet resultSet = getPiDriverTest().executarQuery(PiQuery.tagInfoQuery(tag));
        PIUtil.printResultSet(resultSet);
        assertNotNull("The result can`t be null", resultSet);
    }

    @Test
    public void searchItemsTest() throws ClassNotFoundException, SQLException {
        String tag = "SINUS*";
        String description = "";
        ResultSet resultSet = getPiDriverTest().executarQuery(PiQuery.searchItemsQuery(tag, description));
        PIUtil.printResultSet(resultSet);
        assertNotNull("The result can`t be null", resultSet);
    }

    @Test
    public void writevaluesTest() throws ClassNotFoundException, SQLException {
        String tag = "TESTPIDRIVER";
        String description = "";
        Date timestamp = Date.valueOf("2020-04-27");
        String value = "10,0";
        ResultSet resultSet = getPiDriverTest().executarQuery(PiQuery.writeValueQuery(tag, timestamp, value));
        PIUtil.printResultSet(resultSet);
        assertNotNull("The result can`t be null", resultSet);
    }

}