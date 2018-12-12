package ru.naumen.sd40.log.parser.modes.sdng.data;

import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.DataSetUploaderFactory;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.sdng.uploader.SdngUploader;

@Component
public class SdngDataSetUploaderFactory implements DataSetUploaderFactory<SdngDataSet> {
    @Override
    public DataSetUploader<SdngDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new SdngUploader(params), new SdngDataSetFactory());
    }
}
