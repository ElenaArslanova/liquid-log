package ru.naumen.perfhouse.uploaders;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.GCData;
import ru.naumen.sd40.log.parser.dataset.GCDataSet;

@Component
public class GCUploader extends DBUploader<GCDataSet>{
    public GCUploader(String influxDb, String host, String user, String password, boolean requiredLogTrace){
        super(influxDb, host, user, password, requiredLogTrace);
    }

    @Override
    public void upload(Long key, GCDataSet dataSet) {
        GCData gc = dataSet.getGcData();
        if (!gc.isNan())
        {
            storage.storeGc(batchPoints, influxDb, key, gc);
        }
    }
}
