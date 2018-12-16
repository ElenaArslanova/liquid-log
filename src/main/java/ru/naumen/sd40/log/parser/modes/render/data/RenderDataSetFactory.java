package ru.naumen.sd40.log.parser.modes.render.data;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.modes.dataset.DataSetFactory;

@Component
public class RenderDataSetFactory implements DataSetFactory<RenderDataSet>{
    @Override
    public RenderDataSet create() {
        return new RenderDataSet();
    }
}
