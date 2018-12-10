package ru.naumen.sd40.log.parser.modes.sdng.data;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.modes.dataset.DataSetFactory;

@Component
public class SdngDataSetFactory implements DataSetFactory<SdngDataSet> {
    @Override
    public SdngDataSet create() {
        return new SdngDataSet();
    }
}
