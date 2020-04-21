package br.ufrn.imd.lii.pidriver.format.csv;

import br.ufrn.imd.lii.pidriver.dao.jdbc.PiJdbcDefs;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class PiItemValueCsv extends CsvBean<PiItemValue> {

    @CsvBindByName(column = PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_TAG)
    private String tag;
    @CsvBindByName(column = PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_TIME)
    private String time;
    @CsvBindByName(column = PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_VALUE)
    private String value;
    @CsvBindByName(column = PiJdbcDefs.PI_JDBC_COL_NAME_PICOMP2_STATUS)
    private String status;
    @CsvBindByName(column = PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_POINTTYPE)
    private String pointtype;
    @CsvBindByName(column = PiJdbcDefs.PI_JDBC_COL_NAME_PIPOINT2_POINTTYPEX)
    private String pointtypex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiItemValueCsv that = (PiItemValueCsv) o;
        return Objects.equals(tag, that.tag) &&
                Objects.equals(time, that.time) &&
                Objects.equals(value, that.value) &&
                Objects.equals(status, that.status) &&
                Objects.equals(pointtype, that.pointtype) &&
                Objects.equals(pointtypex, that.pointtypex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, time, value, status, pointtype, pointtypex);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPointtype() {
        return pointtype;
    }

    public void setPointtype(String pointtype) {
        this.pointtype = pointtype;
    }

    public String getPointtypex() {
        return pointtypex;
    }

    public void setPointtypex(String pointtypex) {
        this.pointtypex = pointtypex;
    }

    @Override
    public void from(PiItemValue v) {
        this.tag = v.getTag();
        this.time = v.getTime();
        this.value = v.getValue();
        this.status = v.getStatus();
        this.pointtype = v.getPointtype();
        this.pointtypex = v.getPointtypex();
    }
}
