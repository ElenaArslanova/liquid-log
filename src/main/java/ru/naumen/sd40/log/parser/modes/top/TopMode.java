package ru.naumen.sd40.log.parser.modes.top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.modes.Mode;
import ru.naumen.sd40.log.parser.modes.top.parsers.TopDataParser;
import ru.naumen.sd40.log.parser.modes.top.data.TopDataSetUploaderFactory;
import ru.naumen.sd40.log.parser.modes.top.parsers.TopTimeParserFactory;

@Component("top")
public class TopMode implements Mode {
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
