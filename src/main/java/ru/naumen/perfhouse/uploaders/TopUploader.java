package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.dataset.TopDataSet;

public class TopUploader extends DBUploader<TopDataSet>{
    public TopUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, TopDataSet dataSet) {
        if (!dataSet.isNan())
        {
            storage.storeTop(batchPoints, influxDb, key, dataSet);
        }
    }
}
