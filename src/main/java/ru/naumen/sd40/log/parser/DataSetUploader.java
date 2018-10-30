package ru.naumen.sd40.log.parser;

import ru.naumen.perfhouse.DBUploader;
import ru.naumen.perfhouse.DBCloseException;

import java.security.InvalidParameterException;

public class DataSetUploader implements AutoCloseable{
    private DBUploader influxUploader;
    private long currentKey = -1;
    private DataSet currentDataSet;

    public DataSetUploader(DBUploader influxUploader){
        this.influxUploader = influxUploader;
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
            currentDataSet = new DataSet();
            return currentDataSet;
        }
        throw new InvalidParameterException(String.format("Key %d should have been already processed", key));
    }

     private void upload(){
         influxUploader.upload(currentKey, currentDataSet);
     }
}
