package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.dataset.GCDataSet;

public class GCUploader extends DBUploader<GCDataSet>{
    public GCUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, GCDataSet dataSet) {
        if (!dataSet.isNan())
        {
            storage.storeGc(batchPoints, influxDb, key, dataSet);
        }
    }
}
