package br.ufrn.imd.lii.pidriver.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;

public class PiDriverCLIJCommanderTest {

    private PiDriverCliJCommander cli;
    private JCommander jc;

    @Before
    public void setUp() {
        cli = new PiDriverCliJCommander();
        jc = cli.getCommander(cli);
    }

    @Test
    public void usageDisplay() {
        String[] args = new String[] {
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

    @Test
    public void helpDisplay() {
        String[] args = new String[] {
                "--help"
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

    @Test
    public void testMissingCommand() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

    @Test
    public void inexistentOutputFormat() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
                "search-values",
                "--tag", "SINUSOID",
                "-o", "wtf",
                "-p", "/home/inexistentpath/"
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

    @Test
    public void csvWithoutPath() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
                "search-values",
                "--tag", "SINUSOID",
                "-o", "csv",
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }


    @Test
    public void inexistentPathFailTest() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
                "search-values",
                "--tag", "SINUSOID",
                "-o", "csv",
                "-p", "/home/inexistentpath/"
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());jc.usage();
        }
    }

    @Test
    public void searchValuesTagOnly() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
                "search-values",
                "--tag", "SINUSOID",
                "-o", "csv",
                "-p", "C:\\Users\\Vitor Greati\\Desktop\\TESTPI.csv"
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

    @Test
    public void searchValuesMissingTag() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
                "search-values",
                "-o", "csv",
                "-p", "C:\\Users\\Vitor Greati\\Desktop\\PI.csv"
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

    @Test
    public void searchValuesFull() {
        String[] args = new String[] {
                "--pi-host", "10.0.0.106",
                "--das-host", "localhost",
                "--pi-user", "pidemo",
                "--pi-pass", "-",
                "search-values",
                "--tag", "SINUSOID",
                "--from", "2020-01-01",
                "--to", "2020-01-02",
                "--min-value", "50.5",
                "--max-value", "120.0",
                "--limit", "100",
                "-o", "csv",
                "-p", "C:\\Users\\Vitor Greati\\Desktop\\PI.csv"
        };
        try {
            jc.parse(args);
            cli.execute(jc, cli);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jc.usage();
        }
    }

}
