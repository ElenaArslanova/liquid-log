package ru.naumen.sd40.log.parser.modes.top.data;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.modes.dataset.DataSetFactory;

@Component
public class TopDataSetFactory implements DataSetFactory<TopDataSet> {
    @Override
    public TopDataSet create() {
        return new TopDataSet();
    }
}
