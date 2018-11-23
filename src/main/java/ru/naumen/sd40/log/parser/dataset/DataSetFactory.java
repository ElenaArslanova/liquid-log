package ru.naumen.sd40.log.parser.dataset;

public interface DataSetFactory<T extends DataSet> {
    T create();
}
