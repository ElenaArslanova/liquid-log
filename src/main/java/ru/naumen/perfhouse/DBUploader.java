package ru.naumen.perfhouse;

import ru.naumen.sd40.log.parser.DataSet;

public interface DBUploader extends AutoCloseable{
    void upload(Long key, DataSet dataSet);

    void connect();

    void close() throws DBCloseException;
}
