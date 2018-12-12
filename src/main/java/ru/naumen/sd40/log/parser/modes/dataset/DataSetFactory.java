package ru.naumen.sd40.log.parser.modes.dataset;

public interface DataSetFactory<T extends DataSet> {
    T create();
}
