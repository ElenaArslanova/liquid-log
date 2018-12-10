package ru.naumen.perfhouse.uploaders;

import ru.naumen.perfhouse.DBCloseException;
import ru.naumen.sd40.log.parser.modes.dataset.DataSet;
import ru.naumen.sd40.log.parser.modes.dataset.DataSetFactory;

import java.security.InvalidParameterException;

public class DataSetUploader<T extends DataSet> implements AutoCloseable{
    private DBUploader<T> influxUploader;
    private DataSetFactory<T> dataSetFactory;
    private long currentKey = -1;
    private T currentDataSet;

    public DataSetUploader(DBUploader<T> influxUploader, DataSetFactory<T> dataSetFactory){
        this.influxUploader = influxUploader;
        this.dataSetFactory = dataSetFactory;
    }

    public void close() throws DBCloseException {
        if (currentKey != -1){
            upload();
        }
        influxUploader.close();
    }

    public DataSet get(Long key) throws InvalidParameterException{
        if (key == currentKey){
            return currentDataSet;
        }
        if (key > currentKey){
            if (currentKey != -1){
                upload();
            }
            currentKey = key;
            currentDataSet = dataSetFactory.create();
            return currentDataSet;
        }
        throw new InvalidParameterException(String.format("Key %d should have been already processed", key));
    }

     private void upload(){
         influxUploader.upload(currentKey, currentDataSet);
     }
}
