package ru.naumen.sd40.log.parser.modes.gc.data;

import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.DataSetUploaderFactory;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.gc.uploader.GCUploader;

@Component
public class GCDataSetUploaderFactory implements DataSetUploaderFactory<GCDataSet> {
    @Override
    public DataSetUploader<GCDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new GCUploader(params), new GCDataSetFactory());
    }
}
