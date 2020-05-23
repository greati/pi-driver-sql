package br.ufrn.imd.lii.pidriver.format.csv;

public abstract class CsvBean<T> {

    public abstract void from(T obj);

    public abstract T getBean();
}
