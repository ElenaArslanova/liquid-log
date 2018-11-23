package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.dataset.TopDataSet;
import ru.naumen.sd40.log.parser.dataset.TopDataSetFactory;

public class TopDataSetUploaderFactory implements DataSetUploaderFactory<TopDataSet>{
    @Override
    public DataSetUploader<TopDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new TopUploader(params), new TopDataSetFactory());
    }
}
