package ru.naumen.sd40.log.parser.dataset;

import org.springframework.stereotype.Component;

@Component
public class SdngDataSetFactory implements DataSetFactory<SdngDataSet>{
    @Override
    public SdngDataSet create() {
        return new SdngDataSet();
    }
}
