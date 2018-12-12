package ru.naumen.sd40.log.parser.modes.gc.data;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.modes.dataset.DataSetFactory;

@Component
public class GCDataSetFactory implements DataSetFactory<GCDataSet> {
    @Override
    public GCDataSet create(){
        return new GCDataSet();
    }
}
