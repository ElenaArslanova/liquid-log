package ru.naumen.sd40.log.parser.dataset;

import org.springframework.stereotype.Component;

@Component
public class GCDataSetFactory implements DataSetFactory<GCDataSet>{
    @Override
    public GCDataSet create(){
        return new GCDataSet();
    }
}
