package br.ufrn.imd.lii.pidriver.jdbc;

import br.ufrn.imd.lii.pidriver.dao.PiItemValueDAO;
import br.ufrn.imd.lii.pidriver.dao.jdbc.JDBCPiItemValueDAO;
import br.ufrn.imd.lii.pidriver.exception.DataAccessException;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPiItemValueDAOTest {

    @Test
    public void testMultipleWrite() {
        PiItemValue itemValue1 = new PiItemValue("PSVTEST", "2020-02-03 00:00:00.0", "37,5", "0");
        PiItemValue itemValue2 = new PiItemValue("PSVTEST", "2020-02-04 00:00:00.0", "17,5", "0");
        PiItemValue itemValue3 = new PiItemValue("PSVTEST", "2020-02-05 00:00:00.0", "27,5", "0");
        List<PiItemValue> items = new ArrayList<>();
        items.add(itemValue1);
        items.add(itemValue2);
        items.add(itemValue3);

        try {
            PiItemValueDAO ivDao = new JDBCPiItemValueDAO("localhost", "192.168.0.18", "pidemo", "");
            ivDao.insert(items);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite() {

        PiItemValue itemValue = new PiItemValue("PSVTEST", "2019-10-10", "37,5", "0");
        try {
            PiItemValueDAO ivDao = new JDBCPiItemValueDAO("localhost", "192.168.0.18",  "pidemo", "");
            ivDao.insert(itemValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

}
