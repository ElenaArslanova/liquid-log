package ru.naumen.sd40.log.parser.data;

public class ErrorData implements ParserData{
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
}