package ru.naumen.perfhouse.uploaders;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.DBCloseException;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.dataset.DataSet;

public abstract class DBUploader<T extends DataSet> implements AutoCloseable{
    protected InfluxDAO storage;
    protected BatchPoints batchPoints;
    protected String influxDb;
    protected boolean requiredLogTrace;

    public DBUploader(String influxDb, String host, String user, String password, boolean requiredLogTrace){
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

    public void close() throws DBCloseException{
        storage.writeBatch(batchPoints);
    }

    public abstract void upload(Long key, T dataSet);
}
