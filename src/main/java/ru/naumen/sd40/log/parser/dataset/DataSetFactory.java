package ru.naumen.sd40.log.parser.dataset;

import org.springframework.stereotype.Component;

@Component
public interface DataSetFactory<T extends DataSet> {
    T create();
}
