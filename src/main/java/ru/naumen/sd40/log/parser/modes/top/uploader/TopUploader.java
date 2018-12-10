package ru.naumen.sd40.log.parser.modes.top.uploader;

import ru.naumen.perfhouse.uploaders.DBUploader;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.sd40.log.parser.modes.top.data.TopDataSet;

public class TopUploader extends DBUploader<TopDataSet> {
    public TopUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, TopDataSet dataSet) {
        if (!dataSet.isNan())
        {
            storage.storeData(batchPoints, influxDb, key, dataSet);
        }
    }
}
