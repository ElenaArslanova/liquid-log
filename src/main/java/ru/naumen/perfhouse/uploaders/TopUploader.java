package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.data.TopData;
import ru.naumen.sd40.log.parser.dataset.TopDataSet;

public class TopUploader extends DBUploader<TopDataSet>{
    public TopUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, TopDataSet dataSet) {
        TopData cpuData = dataSet.getTopData();
        if (!cpuData.isNan())
        {
            storage.storeTop(batchPoints, influxDb, key, cpuData);
        }
    }
}
