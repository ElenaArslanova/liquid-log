package ru.naumen.sd40.log.parser.modes.render.data;

import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.DataSetUploaderFactory;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.render.uploader.RenderUploader;

@Component
public class RenderDataSetUploaderFactory implements DataSetUploaderFactory<RenderDataSet>{
    @Override
    public DataSetUploader<RenderDataSet> create(UploaderParams params) {
        return new DataSetUploader<>(new RenderUploader(params), new RenderDataSetFactory());
    }
}
