package ru.naumen.sd40.log.parser.modes;

import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.TimeParser;

public interface Mode{
    TimeParser getTimeParser();
    DataParser getDataParser();
    DataSetUploader getDataSetUploader(UploaderParams params);
}
