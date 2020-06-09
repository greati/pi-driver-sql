package br.ufrn.imd.lii.pidriver.dao.jdbc;

import br.ufrn.imd.lii.pidriver.dao.PiItemValueDAO;
import br.ufrn.imd.lii.pidriver.exception.DataAccessException;
import br.ufrn.imd.lii.pidriver.model.PiItem;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import br.ufrn.imd.lii.pidriver.sql.PiQuery;
import org.javatuples.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JDBCPiItemValueDAO implements PiItemValueDAO {

    private Connection connection;

    public JDBCPiItemValueDAO(String dashost, String pihost, String user, String senha) throws ClassNotFoundException, SQLException {
        Class.forName("com.osisoft.jdbc.Driver");
        PiJdbcUrl piUrl  = PiJdbcUrl.getDefaultPiJdbcUrl(dashost, pihost);
        piUrl.setPiLogin(user, senha);
        this.connection = DriverManager.getConnection(piUrl.getUrl());
    }

    @Override
    public List<PiItemValue> search(String tag,
                                    Optional<Pair<Date, Boolean>> initDate,
                                    Optional<Pair<Date, Boolean>> finalDate,
                                    Optional<Integer> nPoints,
                                    Optional<Pair<Float, Boolean>> minValue,
                                    Optional<Pair<Float, Boolean>> maxValue) throws DataAccessException {

        String query = PiQuery.valuesQuery(tag, initDate, finalDate, nPoints, minValue, maxValue);
        List<PiItemValue> piItemValues = new ArrayList<>();
        try (PreparedStatement pStatement = this.connection.prepareStatement(query)) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                String tagR = rs.getString(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_TAG);
                String timeR = rs.getString(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TIME);
                String valueR = rs.getString(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_VALUE);
                String statusR = rs.getString(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_STATUS);
                String pointtypeR = rs.getString(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_POINTTYPE);
                String pointtypexR = rs.getString(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_POINTTYPEX);
                PiItemValue piItemValue = new PiItemValue(tagR, timeR, valueR, statusR, pointtypeR, pointtypexR);
                piItemValues.add(piItemValue);
            }
            return piItemValues;
        } catch (SQLException e) {
            new DataAccessException();
        }
        return piItemValues;
    }

    @Override
    public void insert(PiItemValue itemValue) throws DataAccessException {
        String query = PiQuery.writeValueQuery(itemValue.getTag(),
                itemValue.getTime(), itemValue.getValue());
        try (PreparedStatement pStatement = this.connection.prepareStatement(query)) {
            pStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    @Override
    public void insert(List<PiItemValue> itemValues) throws DataAccessException {
        /*int i = 0;
        for (PiItemValue v : itemValues) {
            i += 1;
            insert(v);
            if (i % 10 == 0) {
                System.out.println("Inserted: " + i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
        if (itemValues.isEmpty())
            return;
        int L = 500;
        int N = itemValues.size();
        for (int i = 0; i < N; i += L) {
            System.out.println("Inserted from " + i + " to " + Math.min(N, i + L - 1));
            List<PiItemValue> subList = itemValues.subList(i, Math.min(N, i + L));
            String query = PiQuery.writeMultipleValuesQuery(subList);
            try (PreparedStatement pStatement = this.connection.prepareStatement(query)) {
                pStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DataAccessException();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null)
            connection.close();
    }
}
