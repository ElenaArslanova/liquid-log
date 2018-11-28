package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.uploaders.*;
import ru.naumen.sd40.log.parser.gc.GCDataParser;
import ru.naumen.sd40.log.parser.gc.GCTimeParserFactory;
import ru.naumen.sd40.log.parser.sdng.SdngDataParser;
import ru.naumen.sd40.log.parser.sdng.SdngTimeParserFactory;
import ru.naumen.sd40.log.parser.top.TopDataParser;
import ru.naumen.sd40.log.parser.top.TopTimeParserFactory;

/**
 * Created by doki on 22.10.16.
 */
@Component
public class LogParser
{
    private SdngDataParser sdngDataParser;
    private GCDataParser gcDataParser;
    private TopDataParser topDataParser;

    private SdngDataSetUploaderFactory sdngDataSetUploaderFactory;
    private GCDataSetUploaderFactory gcDataSetUploaderFactory;
    private TopDataSetUploaderFactory topDataSetUploaderFactory;

    private SdngTimeParserFactory sdngTimeParserFactory;
    private GCTimeParserFactory gcTimeParserFactory;
    private TopTimeParserFactory topTimeParserFactory;

    @Autowired
    public LogParser(SdngDataParser sdngDataParser,
                     GCDataParser gcDataParser,
                     TopDataParser topDataParser,
                     SdngDataSetUploaderFactory sdngDataSetUploaderFactory,
                     GCDataSetUploaderFactory gcDataSetUploaderFactory,
                     TopDataSetUploaderFactory topDataSetUploaderFactory,
                     SdngTimeParserFactory sdngTimeParserFactory,
                     GCTimeParserFactory gcTimeParserFactory,
                     TopTimeParserFactory topTimeParserFactory){
        this.sdngDataParser = sdngDataParser;
        this.gcDataParser = gcDataParser;
        this.topDataParser = topDataParser;

        this.sdngDataSetUploaderFactory = sdngDataSetUploaderFactory;
        this.gcDataSetUploaderFactory = gcDataSetUploaderFactory;
        this.topDataSetUploaderFactory = topDataSetUploaderFactory;

        this.sdngTimeParserFactory = sdngTimeParserFactory;
        this.gcTimeParserFactory = gcTimeParserFactory;
        this.topTimeParserFactory = topTimeParserFactory;
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

        DataParser dataParser;
        TimeParserFactory timeParserFactory;
        DataSetUploaderFactory uploaderFactory;

        switch (mode)
        {
            case "sdng":
                //Parse sdng
                dataParser = sdngDataParser;
                timeParserFactory = sdngTimeParserFactory;
                uploaderFactory = sdngDataSetUploaderFactory;
                break;
            case "gc":
                //Parse gc log
                dataParser = gcDataParser;
                timeParserFactory = gcTimeParserFactory;
                uploaderFactory = gcDataSetUploaderFactory;
                break;
            case "top":
                dataParser = topDataParser;
                timeParserFactory = topTimeParserFactory;
                uploaderFactory = topDataSetUploaderFactory;
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
        }

        DataSetUploader dataSetUploader = uploaderFactory.create(uploaderParams);
        TimeParser timeParser = timeParserFactory.create();
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
