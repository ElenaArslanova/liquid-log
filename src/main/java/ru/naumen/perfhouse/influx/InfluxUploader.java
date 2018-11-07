package ru.naumen.perfhouse.influx;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.DBUploader;
import ru.naumen.sd40.log.parser.DataSet;
import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;
import ru.naumen.sd40.log.parser.data.GCData;
import ru.naumen.sd40.log.parser.data.TopData;

public class InfluxUploader implements DBUploader{
    private InfluxDAO storage;
    private BatchPoints batchPoints;
    private String influxDb;
    private boolean requiredLogTrace;

    public InfluxUploader(String influxDb, String host, String user, String password, boolean requiredLogTrace){
        storage = new InfluxDAO(host, user, password);
        this.influxDb = influxDb;
        this.requiredLogTrace = requiredLogTrace;
        connect();
    }

    public void connect(){
        storage.init();
        storage.connectToDB(influxDb);
        batchPoints = storage.startBatchPoints(influxDb);
    }

    public void upload(Long key, DataSet dataSet){
        ActionDoneData dones = dataSet.getActionsDoneData();
        dones.calculate();
        ErrorData errors = dataSet.getErrorsData();
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

        GCData gc = dataSet.getGcData();
        if (!gc.isNan())
        {
            storage.storeGc(batchPoints, influxDb, key, gc);
        }

        TopData cpuData = dataSet.getTopData();
        if (!cpuData.isNan())
        {
            storage.storeTop(batchPoints, influxDb, key, cpuData);
        }
    }

    public void close(){
        storage.writeBatch(batchPoints);
    }
}
