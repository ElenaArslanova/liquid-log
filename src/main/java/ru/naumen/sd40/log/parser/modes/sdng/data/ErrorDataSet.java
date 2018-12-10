package ru.naumen.sd40.log.parser.modes.sdng.data;

import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

import java.util.HashMap;

import static ru.naumen.sd40.log.parser.modes.sdng.data.SdngDataType.ResponseTimes.ERRORS;

public class ErrorDataSet implements DataSet {
    private long warnCount;
    private long errorCount;
    private long fatalCount;

    public long getWarnCount()
    {
        return warnCount;
    }

    public long getErrorCount()
    {
        return errorCount;
    }

    public long getFatalCount()
    {
        return fatalCount;
    }

    public void incrementWarnCount(){
        warnCount++;
    }

    public void incrementErrorCount(){
        errorCount++;
    }

    public void incrementFatalCount(){
        fatalCount++;
    }

    @Override
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        fields.put(ERRORS, getErrorCount());
        return fields;
    }
}
