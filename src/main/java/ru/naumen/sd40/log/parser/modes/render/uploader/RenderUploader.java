package ru.naumen.sd40.log.parser.modes.render.uploader;

import ru.naumen.perfhouse.uploaders.DBUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.render.data.RenderDataSet;

public class RenderUploader extends DBUploader<RenderDataSet>{
    public RenderUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, RenderDataSet dataSet) {
        if (requiredLogTrace)
        {
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f\n", key, dataSet.getCount(),
                    dataSet.getMinRenderTime(), dataSet.getMeanRenderTime(), dataSet.getStddevRenderTime(),
                    dataSet.getPercent50(), dataSet.getPercent95(), dataSet.getPercent99(),
                    dataSet.getPercent999(), dataSet.getMaxRenderTime()));
        }
        if (!dataSet.isNan())
        {
            storage.storeData(batchPoints, influxDb, key, dataSet);
        }
    }
}
