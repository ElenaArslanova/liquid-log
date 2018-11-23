package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.dataset.ActionDoneDataSet;
import ru.naumen.sd40.log.parser.dataset.ErrorDataSet;
import ru.naumen.sd40.log.parser.dataset.SdngDataSet;

public class SdngUploader extends DBUploader<SdngDataSet>{
    public SdngUploader(UploaderParams uploaderParams){
        super(uploaderParams);
    }

    @Override
    public void upload(Long key, SdngDataSet dataSet) {
        ActionDoneDataSet dones = dataSet.getActionsDoneData();
        dones.calculate();
        ErrorDataSet errors = dataSet.getErrorsData();
        if (requiredLogTrace)
        {
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, dones.getCount(),
                    dones.getMin(), dones.getMean(), dones.getStddev(), dones.getPercent50(), dones.getPercent95(),
                    dones.getPercent99(), dones.getPercent999(), dones.getMax(), errors.getErrorCount()));
        }
        if (!dones.isNan())
        {
            storage.storeActionsFromLog(batchPoints, influxDb, key, dones, errors);
        }
    }
}
