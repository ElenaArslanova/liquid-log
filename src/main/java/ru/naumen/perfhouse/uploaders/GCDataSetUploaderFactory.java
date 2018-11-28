package ru.naumen.perfhouse.uploaders;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataset.GCDataSet;
import ru.naumen.sd40.log.parser.dataset.GCDataSetFactory;

@Component
public class GCDataSetUploaderFactory implements DataSetUploaderFactory<GCDataSet>{
    @Override
    public DataSetUploader<GCDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new GCUploader(params), new GCDataSetFactory());
    }
}
