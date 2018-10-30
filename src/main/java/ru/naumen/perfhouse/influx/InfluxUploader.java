package ru.naumen.perfhouse.influx;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.DBUploader;
import ru.naumen.sd40.log.parser.ActionDoneParser;
import ru.naumen.sd40.log.parser.DataSet;
import ru.naumen.sd40.log.parser.ErrorParser;
import ru.naumen.sd40.log.parser.TopData;
import ru.naumen.sd40.log.parser.gc.GCDataParser;

public class InfluxUploader implements DBUploader{
    private InfluxDAO storage;
    private BatchPoints batchPoints;
    private String influxDb;

    public InfluxUploader(String influxDb, String host, String user, String password){
        storage = new InfluxDAO(host, user, password);
        this.influxDb = influxDb;
        connect();
    }

    public void connect(){
        storage.init();
        storage.connectToDB(influxDb);
        batchPoints = storage.startBatchPoints(influxDb);
    }

    public void upload(Long key, DataSet dataSet){
        ActionDoneParser dones = dataSet.getActionsDone();
        dones.calculate();
        ErrorParser errors = dataSet.getErrors();
        if (System.getProperty("NoCsv") == null)
        {
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, dones.getCount(),
                    dones.getMin(), dones.getMean(), dones.getStddev(), dones.getPercent50(), dones.getPercent95(),
                    dones.getPercent99(), dones.getPercent999(), dones.getMax(), errors.getErrorCount()));
        }
        if (!dones.isNan())
        {
            storage.storeActionsFromLog(batchPoints, influxDb, key, dones, errors);
        }

        GCDataParser gc = dataSet.getGc();
        if (!gc.isNan())
        {
            storage.storeGc(batchPoints, influxDb, key, gc);
        }

        TopData cpuData = dataSet.cpuData();
        if (!cpuData.isNan())
        {
            storage.storeTop(batchPoints, influxDb, key, cpuData);
        }
    }

    public void close(){
        storage.writeBatch(batchPoints);
    }
}
