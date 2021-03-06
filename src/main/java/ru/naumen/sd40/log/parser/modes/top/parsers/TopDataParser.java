package ru.naumen.sd40.log.parser.modes.top.parsers;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.modes.top.data.TopDataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TopDataParser implements DataParser<TopDataSet> {
    private static final Pattern CPU_AND_MEM_PATTERN = Pattern
            .compile("^ *\\d+ \\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ \\S+ +(\\S+) +(\\S+) +\\S+ java");

    @Override
    public void parseLine(String line, TopDataSet dataSet){
        //get la
        Matcher la = Pattern.compile(".*load average:(.*)").matcher(line);
        if (la.find()) {
            dataSet.addLa(Double.parseDouble(la.group(1).split(",")[0].trim()));
            return;
        }

        //get cpu and mem
        Matcher cpuAndMemMatcher = CPU_AND_MEM_PATTERN.matcher(line);
        if (cpuAndMemMatcher.find()) {
            dataSet.addCpu(Double.valueOf(cpuAndMemMatcher.group(1)));
            dataSet.addMem(Double.valueOf(cpuAndMemMatcher.group(2)));
        }
    }
}
