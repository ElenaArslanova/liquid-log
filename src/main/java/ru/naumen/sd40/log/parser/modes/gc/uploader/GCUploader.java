package ru.naumen.sd40.log.parser.modes.gc.uploader;

import ru.naumen.perfhouse.uploaders.DBUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.gc.data.GCDataSet;

public class GCUploader extends DBUploader<GCDataSet> {
    public GCUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, GCDataSet dataSet) {
        if (!dataSet.isNan())
        {
            storage.storeData(batchPoints, influxDb, key, dataSet);
        }
    }
}
