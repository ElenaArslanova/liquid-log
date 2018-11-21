package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.dataset.SdngDataSet;
import ru.naumen.sd40.log.parser.dataset.SdngDataSetFactory;

public class SdngDataSetUploaderFactory implements DataSetUploaderFactory<SdngDataSet> {
    @Override
    public DataSetUploader<SdngDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new SdngUploader(params), new SdngDataSetFactory());
    }
}
