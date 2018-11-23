package ru.naumen.sd40.log.parser.gc;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.dataset.GCDataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GCDataParser implements DataParser<GCDataSet> {
    private static final Pattern GC_EXECUTION_TIME = Pattern.compile(".*real=(.*)secs.*");

    @Override
    public void parseLine(String line, GCDataSet dataSet)
    {
        Matcher matcher = GC_EXECUTION_TIME.matcher(line);
        if (matcher.find())
        {
            dataSet.addValue(Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
        }
    }
}
