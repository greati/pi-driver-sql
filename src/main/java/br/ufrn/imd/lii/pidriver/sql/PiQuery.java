package br.ufrn.imd.lii.pidriver.sql;

import br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs;
import br.ufrn.imd.lii.pidriver.model.PiItem;
import br.ufrn.imd.lii.pidriver.model.PiItemInfo;
import br.ufrn.imd.lii.pidriver.model.PiItemInfoDigitalStates;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import br.ufrn.imd.lii.pidriver.util.PIUtil;
import org.javatuples.Pair;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PiQuery {

    public static String SQL_SELECT_DECLARACAO = "SELECT";
    public static String SQL_FROM_DECLARACAO = "FROM";
    public static String SQL_WHERE_DECLARACAO = "WHERE";
    public static String SQL_AND_DECLARACAO = "AND";
    public static String SQL_OR_DECLARACAO = "OR";
    public static String SQL_IGUAL_OPERADOR = "=";
    public static String SQL_MENOR_OPERADOR = "<";
    public static String SQL_MENOR_IGUAL_OPERADOR = "<=";
    public static String SQL_MAIOR_OPERADOR = ">";
    public static String SQL_MAIOR_IGUAL_OPERADOR = ">=";
    public static String SQL_LIKE_DECLARACAO = "LIKE";
    public static String SQL_IN_DECLARACAO = "IN";
    public static String SQL_ESPACO = " ";
    public static String SQL_VIRGULA = ",";
    public static String SQL_PONTO_VIRGULA = ";";
    public static String SQL_ASPAS = "'";
    public static String SQL_PARENTESES_INICIO = "(";
    public static String SQL_PARENTESES_FIM = ")";
    public static String SQL_TOP_DECLARACAO = "TOP";
    public static String SQL_BETWEEN_DECLARACAO = "BETWEEN";
    public static String SQL_INSERT_DECLARACAO = "INSERT";
    public static String SQL_VALUES_DECLARACAO = "VALUES";
    public static String SQL_UNION_DECLARACAO = "UNION";
    public static String SQL_DIGCODE = "DIGCODE";


    /**
     * Retorna a query utilizada para o metodo SearchItemsQuery.
     * <p>
     * Padrao: SELECT br.ufrn.imd.lii.pidriver.model.PiItem.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.POINT2_TABLE_NAME WHERE tag LIKE 'tag' AND (descriptor LIKE 'description' OR descriptor LIKE '');
     *
     * @param tag         nome do item
     * @param description descricao
     * @return A query de execucao
     */
    public static String searchItemsQuery(String tag, String description) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiItem.query()).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIPOINT2).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_TAG).append(SQL_ESPACO);
        builder.append(SQL_LIKE_DECLARACAO).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(tag).append(SQL_ASPAS).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(SQL_PARENTESES_INICIO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_DESCRIPTOR).append(SQL_ESPACO);
        builder.append(SQL_LIKE_DECLARACAO).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(description).append(SQL_ASPAS);
        builder.append(SQL_ESPACO).append(SQL_OR_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_DESCRIPTOR).append(SQL_ESPACO);
        builder.append(SQL_LIKE_DECLARACAO).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(SQL_ASPAS).append(SQL_PARENTESES_FIM);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo getTagInfo.
     * <p>
     * Padrao: SELECT br.ufrn.imd.lii.pidriver.model.PiItemInfo.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.POINT2_TABLE_NAME WHERE tag = 'tag';
     *
     * @param tag Nome do item
     * @return A query de execucao
     */
    public static String tagInfoQuery(String tag) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiItemInfo.query()).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIPOINT2).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_TAG).append(SQL_ESPACO);
        builder.append(SQL_IGUAL_OPERADOR).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(tag).append(SQL_ASPAS);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }


    /**
     * Retorna a query utilizada para o metodo getDigitalState.
     * <p>
     * Padrao: SELECT br.ufrn.imd.lii.pidriver.model.PiItemInfoDigitalStates.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIDS_TABLE_NAME WHERE digitalset = 'digitalset';
     *
     * @param digitalset Nome do grupo digital
     * @return A query de execucao
     */
    public static String getDigitalStateQuery(String digitalset) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiItemInfoDigitalStates.query()).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIDS).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PIDS_DIGITAL_SET).append(SQL_ESPACO);
        builder.append(SQL_IGUAL_OPERADOR).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(digitalset).append(SQL_ASPAS);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo getSnapshots.
     * <p>
     * Padrao: SELECT br.ufrn.imd.lii.pidriver.model.PiItemValue.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PISNAPSHOT_TABLE_NAME,br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIPOINTS2_TABLE_NAME WHERE pisnapshot.tag=pipoint2.tag AND tag IN (tags);
     *
     * @param tag Tag a ser buscado último valor
     * @return A query de execucao
     */
    public static String snapshotQuery(String tag) {
        return snapshotQuery(Arrays.asList(tag));
    }


    /**
     * Retorna a query utilizada para o metodo getSnapshots.
     * <p>
     * Padrao: SELECT br.ufrn.imd.lii.pidriver.model.PiItemValue.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PISNAPSHOT_TABLE_NAME,br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIPOINTS2_TABLE_NAME WHERE pisnapshot.tag=pipoint2.tag AND tag IN (tags);
     *
     * @param tags Lista de tags na query
     * @return A query de execucao
     */
    public static String snapshotQuery(List<String> tags) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiItemValue.query(PiItemValue.ItemValueSource.PI_SOURCE_SNAPSHOT)).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PISNAPSHOT).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIPOINT2).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PISNAPSHOT_TAG);
        builder.append(SQL_IGUAL_OPERADOR);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIPOINT2_TAG).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PISNAPSHOT_TAG).append(SQL_ESPACO);
        builder.append(SQL_IN_DECLARACAO).append(SQL_ESPACO);

        builder.append(SQL_PARENTESES_INICIO);
        for (int i = 0; i < tags.size(); i++) {
            if (i != 0) builder.append(SQL_VIRGULA);
            builder.append(SQL_ASPAS);
            builder.append(tags.get(i));
            builder.append(SQL_ASPAS);
        }
        builder.append(SQL_PARENTESES_FIM);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo getHistoricalValues.
     * <p>
     * Padrao: SELECT TOP nPoint br.ufrn.imd.lii.pidriver.model.PiItemValue.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PICOMP2_TABLE_NAME,br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIPOINTS2_TABLE_NAME WHERE picomp2.tag=pipoint2.tag AND picomp2.tag='tags' AND picomp2.time BETWEEN 'initDate' AND 'finalDate';
     *
     * @param tag      Nome do tag
     * @param initDate  Data de inicio
     * @param finalDate Data final
     * @param nPoints   numero de pontos
     * @return A query de execucao
     */
    public static String historicalValuesQuery(String tag, Date initDate, Date finalDate, int nPoints) {
        return  historicalValuesQuery(tag, initDate,  finalDate, nPoints, Optional.empty(), Optional.empty());
    }

    /**
     * Retorna a query utilizada para o metodo getHistoricalValues.
     * <p>
     * Padrao: SELECT TOP nPoint br.ufrn.imd.lii.pidriver.model.PiItemValue.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PICOMP2_TABLE_NAME,br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIPOINTS2_TABLE_NAME WHERE picomp2.tag=pipoint2.tag AND picomp2.tag='tags' AND picomp2.time BETWEEN 'initDate' AND 'finalDate';
     *
     * @param tag      Nome do tag
     * @param initDate  Data de inicio
     * @param finalDate Data final
     * @param nPoints   numero de pontos
     * @return A query de execucao
     */
    public static String historicalValuesQuery(String tag,
                                               Date initDate,
                                               Date finalDate,
                                               int nPoints,
                                               Optional<Pair<Float, Boolean>> minValue,
                                               Optional<Pair<Float, Boolean>> maxValue) {
        return valuesQuery(
                tag,
                Optional.of(new Pair(initDate, true)),
                Optional.of(new Pair(finalDate, true)),
                Optional.of(nPoints),
                minValue,
                maxValue
        );
    }

    /**
     * Retorna a query utilizada para o metodo getHistoricalValues.
     * <p>
     * Padrao: SELECT TOP nPoint br.ufrn.imd.lii.pidriver.model.PiItemValue.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PICOMP2_TABLE_NAME,br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIPOINTS2_TABLE_NAME WHERE picomp2.tag=pipoint2.tag AND picomp2.tag='tags' AND picomp2.time BETWEEN 'initDate' AND 'finalDate';
     *
     * @param tag      Nome do tag
     * @param initDate  Data de inicio
     * @param finalDate Data final
     * @param nPoints   numero de pontos
     * @return A query de execucao
     */
    public static String valuesQuery(String tag,
                                     Optional<Pair<Date, Boolean>> initDate,
                                     Optional<Pair<Date, Boolean>> finalDate,
                                     Optional<Integer> nPoints,
                                     Optional<Pair<Float, Boolean>> minValue,
                                     Optional<Pair<Float, Boolean>> maxValue) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        if (nPoints.isPresent())
            builder.append(SQL_TOP_DECLARACAO).append(SQL_ESPACO).append(nPoints.get()).append(SQL_ESPACO);
        builder.append(PiItemValue.query(PiItemValue.ItemValueSource.PI_SOURCE_COMP)).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PICOMP2).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIPOINT2).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PICOMP2_TAG);
        builder.append(SQL_IGUAL_OPERADOR);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIPOINT2_TAG).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PICOMP2_TAG).append(SQL_ESPACO);
        builder.append(SQL_IGUAL_OPERADOR).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(tag).append(SQL_ASPAS).append(SQL_ESPACO);

        if (initDate.isPresent()) {
            builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
            builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PICOMP2_TIME).append(SQL_ESPACO);
            String op = initDate.get().getValue1() ? SQL_MAIOR_IGUAL_OPERADOR : SQL_MAIOR_OPERADOR;
            builder.append(op).append(SQL_ASPAS).append((PIUtil.convertDateToString(initDate.get().getValue0()))).append(SQL_ASPAS).append(SQL_ESPACO);
        }

        if (finalDate.isPresent()) {
            builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
            builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PICOMP2_TIME).append(SQL_ESPACO);
            String op = finalDate.get().getValue1() ? SQL_MENOR_IGUAL_OPERADOR : SQL_MENOR_OPERADOR;
            builder.append(op).append(SQL_ASPAS).append((PIUtil.convertDateToString(finalDate.get().getValue0()))).append(SQL_ASPAS).append(SQL_ESPACO);
        }

        builder.append(SQL_ESPACO);
        if (minValue.isPresent()) {
            builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
            builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PICOMP2_VALUE);
            String op = minValue.get().getValue1() ? SQL_MAIOR_IGUAL_OPERADOR : SQL_MAIOR_OPERADOR;
            builder.append(op).append(minValue.get().getValue0()).append(SQL_ESPACO);
        }
        if (maxValue.isPresent()) {
            builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
            builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PICOMP2_VALUE);
            String op = maxValue.get().getValue1() ? SQL_MENOR_IGUAL_OPERADOR : SQL_MENOR_OPERADOR;
            builder.append(op).append(maxValue.get().getValue0()).append(SQL_ESPACO);
        }
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo getHistoricalInterpolatedValues.
     * <p>
     * Padrao: SELECT TOP nPoint br.ufrn.imd.lii.pidriver.model.PiItemValue.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIINTERP2_TABLE_NAME,br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIPOINTS2_TABLE_NAME WHERE piinterp2.tag=pipoint2.tag AND piinterp2.tag='tags' AND piinterp2.time BETWEEN 'initDate' AND 'finalDate' AND piinterp2.timestep= 'timestep';
     *
     * @param name      Nome do tag
     * @param initDate  Data de inicio
     * @param finalDate Data final
     * @param nPoints   numero de pontos
     * @param timestep  timestep utilizado
     * @return A query de execucao
     */
    public static String interpolatedHistoricalValuesQuery(String name, Date initDate, Date finalDate, int nPoints, String timestep) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        builder.append(SQL_TOP_DECLARACAO).append(SQL_ESPACO).append(nPoints).append(SQL_ESPACO);
        builder.append(PiItemValue.query(PiItemValue.ItemValueSource.PI_SOURCE_INTERP)).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIINTERP2).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIPOINT2).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIINTERP2_TAG);
        builder.append(SQL_IGUAL_OPERADOR);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIPOINT2_TAG).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIINTERP2_TAG).append(SQL_ESPACO);
        builder.append(SQL_IGUAL_OPERADOR).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(name).append(SQL_ASPAS).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIINTERP2_TIME).append(SQL_ESPACO);
        builder.append(SQL_BETWEEN_DECLARACAO).append(SQL_ESPACO);
        builder.append(SQL_ASPAS).append(PIUtil.convertDateToString(initDate)).append(SQL_ASPAS).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(SQL_ASPAS).append(PIUtil.convertDateToString(finalDate)).append(SQL_ASPAS).append(SQL_ESPACO);
        builder.append(SQL_AND_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_FULL_NAME_PIINTERP2_TIMESTEP).append(SQL_ESPACO);
        builder.append(SQL_IGUAL_OPERADOR).append(SQL_ESPACO).append(SQL_ASPAS);
        builder.append(timestep).append(SQL_ASPAS);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo writeValue.
     * <p>
     * Padrao: INSERT picomp2 (tag,time,value) VALUES ('tag', 'timestamp', 'value');
     *
     * @param tag       Nome da tag
     * @param timestamp Timestamp do evento
     * @param value     Valor do evento
     * @return A query de execucao
     */
    public static String writeValueQuery(String tag, String timestamp, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_INSERT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PICOMP2).append(SQL_ESPACO);
        builder.append(SQL_PARENTESES_INICIO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TAG).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TIME).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_VALUE);
        builder.append(SQL_PARENTESES_FIM).append(SQL_ESPACO);
        builder.append(SQL_VALUES_DECLARACAO).append(SQL_ESPACO);
        builder.append(SQL_PARENTESES_INICIO);
        builder.append(SQL_ASPAS).append(tag).append(SQL_ASPAS).append(SQL_VIRGULA);
        builder.append(SQL_ASPAS).append(timestamp).append(SQL_ASPAS).append(SQL_VIRGULA);
        builder.append(SQL_ASPAS).append(value).append(SQL_ASPAS);
        builder.append(SQL_PARENTESES_FIM);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo writeValue.
     * <p>
     * Padrao: INSERT picomp2 (tag,time,value) VALUES ('tag', 'timestamp', 'value');
     *
     * @param items
     * @return A query de execucao
     */
    public static String writeMultipleValuesQuery(List<PiItemValue> items) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_INSERT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PICOMP2).append(SQL_ESPACO);
        builder.append(SQL_PARENTESES_INICIO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TAG).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TIME).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_VALUE);
        builder.append(SQL_PARENTESES_FIM).append(SQL_ESPACO);

        for (int i = 0; i < items.size(); ++i) {
            PiItemValue item = items.get(i);
            builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
            builder.append(SQL_ASPAS).append(item.getTag()).append(SQL_ASPAS).append(SQL_VIRGULA);
            builder.append(SQL_ASPAS).append(item.getTime()).append(SQL_ASPAS).append(SQL_VIRGULA);
            builder.append(SQL_ASPAS).append(item.getValue()).append(SQL_ASPAS);
            if (i != items.size() - 1)
                builder.append(SQL_ESPACO).append(SQL_UNION_DECLARACAO).append(SQL_ESPACO);
        }
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo getDigitalStateFromCode.
     * <p>
     * Padrao: SELECT br.ufrn.imd.lii.pidriver.model.PiItemInfoDigitalStates.query FROM br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs.PIDS_TABLE_NAME WHERE code = code;
     *
     * @param code
     * @return
     */
    public static String digitalStateFromCodeQuery(String code) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_SELECT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiItemInfoDigitalStates.query()).append(SQL_ESPACO);
        builder.append(SQL_FROM_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PIDS).append(SQL_ESPACO);
        builder.append(SQL_WHERE_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PIDS_CODE).append(SQL_ESPACO);
        builder.append(SQL_IGUAL_OPERADOR).append(SQL_ESPACO);
        builder.append(code);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

    /**
     * Retorna a query utilizada para o metodo writeDigitalValue.
     * <p>
     * Padrao: INSERT picomp2 (tag,time,value) VALUES ('tag', 'timestamp', DIGCODE('value'));
     *
     * @param tag       Nome da tag
     * @param timestamp Timestamp do evento
     * @param value     Valor digital do evento
     * @return A query de execucao
     */
    public static String writeDigitalValueQuery(String tag, String timestamp, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(SQL_INSERT_DECLARACAO).append(SQL_ESPACO);
        builder.append(PiJdbcDefs.PI_JDBC_TABLE_NAME_PICOMP2).append(SQL_ESPACO);
        builder.append(SQL_PARENTESES_INICIO);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TAG).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TIME).append(SQL_VIRGULA);
        builder.append(PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_VALUE);
        builder.append(SQL_PARENTESES_FIM).append(SQL_ESPACO);
        builder.append(SQL_VALUES_DECLARACAO).append(SQL_ESPACO);
        builder.append(SQL_PARENTESES_INICIO);
        builder.append(SQL_ASPAS).append(tag).append(SQL_ASPAS).append(SQL_VIRGULA);
        builder.append(SQL_ASPAS).append(timestamp).append(SQL_ASPAS).append(SQL_VIRGULA);
        builder.append(SQL_DIGCODE).append(SQL_PARENTESES_INICIO).append(SQL_ASPAS);
        builder.append(value).append(SQL_ASPAS).append(SQL_PARENTESES_FIM);
        builder.append(SQL_PARENTESES_FIM);
        builder.append(SQL_PONTO_VIRGULA);
        return builder.toString();
    }

}
