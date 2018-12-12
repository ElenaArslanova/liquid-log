package ru.naumen.sd40.log.parser.modes.top.data;

import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.DataSetUploaderFactory;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.top.uploader.TopUploader;

@Component
public class TopDataSetUploaderFactory implements DataSetUploaderFactory<TopDataSet> {
    @Override
    public DataSetUploader<TopDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new TopUploader(params), new TopDataSetFactory());
    }
}
