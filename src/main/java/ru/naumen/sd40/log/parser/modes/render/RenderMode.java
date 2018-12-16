package ru.naumen.sd40.log.parser.modes.render;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.DataType;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.modes.Mode;
import ru.naumen.sd40.log.parser.modes.render.data.RenderDataSetUploaderFactory;
import ru.naumen.sd40.log.parser.modes.render.data.RenderDataType;
import ru.naumen.sd40.log.parser.modes.render.parsers.RenderDataParser;
import ru.naumen.sd40.log.parser.modes.render.parsers.RenderTimeParserFactory;

@Component("render")
public class RenderMode implements Mode {
    private RenderTimeParserFactory timeParserFactory;
    private RenderDataParser dataParser;
    private RenderDataSetUploaderFactory dataSetUploaderFactory;

    @Autowired
    public RenderMode(RenderTimeParserFactory timeParserFactory,
                      RenderDataParser dataParser,
                      RenderDataSetUploaderFactory dataSetUploaderFactory){
        this.timeParserFactory = timeParserFactory;
        this.dataParser = dataParser;
        this.dataSetUploaderFactory = dataSetUploaderFactory;
    }

    @Override
    public TimeParser getTimeParser() {
        return timeParserFactory.create();
    }

    @Override
    public DataParser getDataParser() {
        return dataParser;
    }

    @Override
    public DataSetUploader getDataSetUploader(UploaderParams params) {
        return dataSetUploaderFactory.create(params);
    }

    @Override
    public DataType[] getDataTypes() {
        return RenderDataType.values();
    }
}
