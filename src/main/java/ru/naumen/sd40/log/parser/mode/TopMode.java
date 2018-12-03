package ru.naumen.sd40.log.parser.mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.TopDataSetUploaderFactory;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.top.TopDataParser;
import ru.naumen.sd40.log.parser.top.TopTimeParserFactory;

@Component("top")
public class TopMode implements Mode{
    private TopTimeParserFactory timeParserFactory;
    private TopDataParser dataParser;
    private TopDataSetUploaderFactory dataSetUploaderFactory;

    @Autowired
    public TopMode(TopTimeParserFactory timeParserFactory,
                   TopDataParser dataParser,
                   TopDataSetUploaderFactory dataSetUploaderFactory){
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
