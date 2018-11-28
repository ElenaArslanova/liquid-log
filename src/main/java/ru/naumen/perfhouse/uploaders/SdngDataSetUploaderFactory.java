package ru.naumen.perfhouse.uploaders;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataset.SdngDataSet;
import ru.naumen.sd40.log.parser.dataset.SdngDataSetFactory;

@Component
public class SdngDataSetUploaderFactory implements DataSetUploaderFactory<SdngDataSet> {
    @Override
    public DataSetUploader<SdngDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new SdngUploader(params), new SdngDataSetFactory());
    }
}
