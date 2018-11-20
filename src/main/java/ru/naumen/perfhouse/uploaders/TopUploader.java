package ru.naumen.perfhouse.uploaders;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.TopData;
import ru.naumen.sd40.log.parser.dataset.TopDataSet;

@Component
public class TopUploader extends DBUploader<TopDataSet>{
    public TopUploader(String influxDb, String host, String user, String password, boolean requiredLogTrace){
        super(influxDb, host, user, password, requiredLogTrace);
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
