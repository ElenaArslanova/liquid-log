package ru.naumen.sd40.log.parser.dataset;

import org.springframework.stereotype.Component;

@Component
public class TopDataSetFactory implements DataSetFactory<TopDataSet>{
    @Override
    public TopDataSet create() {
        return new TopDataSet();
    }
}
