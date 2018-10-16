package ru.naumen.sd40.log.parser.top;

import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.DataSet;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopDataParser implements DataParser {
    private Pattern cpuAndMemPattren = Pattern
            .compile("^ *\\d+ \\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ \\S+ +(\\S+) +(\\S+) +\\S+ java");

    @Override
    public void parseLine(String line, DataSet dataSet) throws IOException, ParseException {
        //get la
        Matcher la = Pattern.compile(".*load average:(.*)").matcher(line);
        if (la.find()) {
            dataSet.cpuData().addLa(Double.parseDouble(la.group(1).split(",")[0].trim()));
            return;
        }

        //get cpu and mem
        Matcher cpuAndMemMatcher = cpuAndMemPattren.matcher(line);
        if (cpuAndMemMatcher.find()) {
            dataSet.cpuData().addCpu(Double.valueOf(cpuAndMemMatcher.group(1)));
            dataSet.cpuData().addMem(Double.valueOf(cpuAndMemMatcher.group(2)));
            return;
        }
    }
}
