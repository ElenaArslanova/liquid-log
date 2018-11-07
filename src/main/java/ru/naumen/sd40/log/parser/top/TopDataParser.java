package ru.naumen.sd40.log.parser.top;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.DataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TopDataParser implements DataParser {
    private Pattern cpuAndMemPattren = Pattern
            .compile("^ *\\d+ \\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ \\S+ +(\\S+) +(\\S+) +\\S+ java");

    @Override
    public void parseLine(String line, DataSet dataSet){
        //get la
        Matcher la = Pattern.compile(".*load average:(.*)").matcher(line);
        if (la.find()) {
            dataSet.getTopData().addLa(Double.parseDouble(la.group(1).split(",")[0].trim()));
            return;
        }

        //get cpu and mem
        Matcher cpuAndMemMatcher = cpuAndMemPattren.matcher(line);
        if (cpuAndMemMatcher.find()) {
            dataSet.getTopData().addCpu(Double.valueOf(cpuAndMemMatcher.group(1)));
            dataSet.getTopData().addMem(Double.valueOf(cpuAndMemMatcher.group(2)));
        }
    }
}
