package ru.naumen.perfhouse.uploaders;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.DBCloseException;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

public abstract class DBUploader<T extends DataSet> implements AutoCloseable{
    protected InfluxDAO storage;
    protected BatchPoints batchPoints;
    protected String influxDb;
    protected boolean requiredLogTrace;

    public DBUploader(UploaderParams params){
        storage = new InfluxDAO(params.getHost(), params.getUser(), params.getPassword());
        this.influxDb = params.getInfluxDb();
        this.requiredLogTrace = params.isRequiredLogTrace();
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
