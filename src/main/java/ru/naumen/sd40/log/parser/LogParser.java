package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;

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
    /**
     * 
     * @param args [0] - sdng.log, [1] - gc.log, [2] - top, [3] - dbName, [4] timezone
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException, InvalidParameterException
    {
        if (args.length <= 1){
            System.out.print("Not enough input parameters provided for db initialization");
            System.exit(1);
        }

        String influxDb = args[1].replaceAll("-", "_");
        DataSetUploader dataSetUploader = new DataSetUploader(new InfluxUploader(
                influxDb,
                System.getProperty("influx.host"),
                System.getProperty("influx.user"),
                System.getProperty("influx.password")));

        String log = args[0];

        DataParser dataParser;
        TimeParser timeParser;

        String mode = System.getProperty("parse.mode", "");
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
            timeParser = new TopTimeParser(log);
            dataParser = new TopDataParser();
            break;
        default:
            throw new IllegalArgumentException(
                    "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
        }

        if (args.length > 2)
        {
            timeParser.configureTimeZone(args[2]);
        }

        if (System.getProperty("NoCsv") == null)
        {
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(log), 32 * 1024 * 1024))
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
        dataSetUploader.close();
    }
}
