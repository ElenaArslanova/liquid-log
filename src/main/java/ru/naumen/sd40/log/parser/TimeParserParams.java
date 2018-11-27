package ru.naumen.sd40.log.parser;

public class TimeParserParams{
    private String logPath;

    public TimeParserParams(String logPath){
        this.logPath = logPath;
    }

    public String getLogPath() {
        return logPath;
    }
}
