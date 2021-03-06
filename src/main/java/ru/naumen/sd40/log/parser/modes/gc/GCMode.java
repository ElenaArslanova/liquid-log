package ru.naumen.sd40.log.parser.modes.gc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.DataType;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.modes.Mode;
import ru.naumen.sd40.log.parser.modes.gc.data.GCDataType;
import ru.naumen.sd40.log.parser.modes.gc.parsers.GCDataParser;
import ru.naumen.sd40.log.parser.modes.gc.data.GCDataSetUploaderFactory;
import ru.naumen.sd40.log.parser.modes.gc.parsers.GCTimeParserFactory;

@Component("gc")
public class GCMode implements Mode {
    private GCTimeParserFactory timeParserFactory;
    private GCDataParser dataParser;
    private GCDataSetUploaderFactory dataSetUploaderFactory;

    @Autowired
    public GCMode(GCTimeParserFactory timeParserFactory,
                  GCDataParser dataParser,
                  GCDataSetUploaderFactory dataSetUploaderFactory){
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
        return GCDataType.values();
    }
}
