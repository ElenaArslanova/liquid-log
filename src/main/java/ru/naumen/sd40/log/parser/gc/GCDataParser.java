package ru.naumen.sd40.log.parser.gc;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.DataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GCDataParser implements DataParser {
    private Pattern gcExecutionTime = Pattern.compile(".*real=(.*)secs.*");

    @Override
    public void parseLine(String line, DataSet dataSet)
    {
        Matcher matcher = gcExecutionTime.matcher(line);
        if (matcher.find())
        {
            dataSet.getGcData().addValue(
                    Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
        }
    }
}
