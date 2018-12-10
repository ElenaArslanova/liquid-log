package ru.naumen.sd40.log.parser.modes.sdng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.modes.Mode;
import ru.naumen.sd40.log.parser.modes.sdng.parsers.SdngDataParser;
import ru.naumen.sd40.log.parser.modes.sdng.data.SdngDataSetUploaderFactory;
import ru.naumen.sd40.log.parser.modes.sdng.parsers.SdngTimeParserFactory;

@Component("sdng")
public class SdngMode implements Mode {
    private SdngTimeParserFactory timeParserFactory;
    private SdngDataParser dataParser;
    private SdngDataSetUploaderFactory dataSetUploaderFactory;

    @Autowired
    public SdngMode(SdngTimeParserFactory timeParserFactory,
                    SdngDataParser dataParser,
                    SdngDataSetUploaderFactory dataSetUploaderFactory){
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
