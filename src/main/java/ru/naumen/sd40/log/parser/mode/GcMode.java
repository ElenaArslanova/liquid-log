package ru.naumen.sd40.log.parser.mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.GCDataSetUploaderFactory;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.gc.GCDataParser;
import ru.naumen.sd40.log.parser.gc.GCTimeParserFactory;

@Component("gc")
public class GcMode implements Mode{
    private GCTimeParserFactory timeParserFactory;
    private GCDataParser dataParser;
    private GCDataSetUploaderFactory dataSetUploaderFactory;

    @Autowired
    public GcMode(GCTimeParserFactory timeParserFactory,
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
}
