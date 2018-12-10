package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.UploaderParams;
import ru.naumen.perfhouse.uploaders.*;
import ru.naumen.sd40.log.parser.modes.Mode;

/**
 * Created by doki on 22.10.16.
 */
@Component
public class LogParser
{
    private Map<String, Mode> modes;

    @Autowired
    public LogParser(Map<String, Mode> modes){
        this.modes = modes;
    }

    public void parse(String influxDb,
                      String mode,
                      String logPath,
                      String timeZone,
                      boolean requiredLogTrace) throws IOException, ParseException, InvalidParameterException{
        influxDb = influxDb.replaceAll("-", "_");

        UploaderParams uploaderParams = new UploaderParams(
                influxDb,
                System.getProperty("influx.host"),
                System.getProperty("influx.user"),
                System.getProperty("influx.password"),
                requiredLogTrace);

        Mode parsingMode = modes.get(mode.toLowerCase());
        if (parsingMode == null){
            throw new IllegalArgumentException(
                    "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
        }

        DataParser dataParser = parsingMode.getDataParser();
        TimeParser timeParser = parsingMode.getTimeParser();
        DataSetUploader dataSetUploader = parsingMode.getDataSetUploader(uploaderParams);

        timeParser.setLogPath(logPath);

        if (timeZone != null) {
            timeParser.configureTimeZone(timeZone);
        }

        if (requiredLogTrace){
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(logPath), 32 * 1024 * 1024))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                long time = timeParser.parseLine(line);

                if (time == 0)
                {
                    continue;
                }

                int min5 = 5 * 60 * 1000;
                long count = time / min5;
                long key = count * min5;

                dataParser.parseLine(line, dataSetUploader.get(key));
            }
        }
    }
}
