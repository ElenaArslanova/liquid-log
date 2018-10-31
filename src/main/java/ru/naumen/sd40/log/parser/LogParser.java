package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;

import ru.naumen.perfhouse.DBCloseException;
import ru.naumen.perfhouse.influx.InfluxUploader;
import ru.naumen.sd40.log.parser.gc.GCDataParser;
import ru.naumen.sd40.log.parser.gc.GCTimeParser;
import ru.naumen.sd40.log.parser.sdng.SdngDataParser;
import ru.naumen.sd40.log.parser.sdng.SdngTimeParser;
import ru.naumen.sd40.log.parser.top.TopDataParser;
import ru.naumen.sd40.log.parser.top.TopTimeParser;

/**
 * Created by doki on 22.10.16.
 */
public class LogParser
{
    public static void parse(String influxDb,
                             String mode,
                             String logPath,
                             String timeZone,
                             boolean requiredLogTrace) throws IOException, ParseException, InvalidParameterException,
            DBCloseException{
        influxDb = influxDb.replaceAll("-", "_");
        DataParser dataParser;
        TimeParser timeParser;

        switch (mode)
        {
            case "sdng":
                //Parse sdng
                dataParser = new SdngDataParser();
                timeParser = new SdngTimeParser();
                break;
            case "gc":
                //Parse gc log
                dataParser = new GCDataParser();
                timeParser = new GCTimeParser();
                break;
            case "top":
                timeParser = new TopTimeParser(logPath);
                dataParser = new TopDataParser();
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
        }

        if (timeZone != null) {
            timeParser.configureTimeZone(timeZone);
        }

        if (requiredLogTrace){
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(logPath), 32 * 1024 * 1024);
             DataSetUploader dataSetUploader = new DataSetUploader(new InfluxUploader(
                     influxDb,
                     System.getProperty("influx.host"),
                     System.getProperty("influx.user"),
                     System.getProperty("influx.password"),
                     requiredLogTrace)))
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
