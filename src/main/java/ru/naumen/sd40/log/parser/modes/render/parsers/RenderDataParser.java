package ru.naumen.sd40.log.parser.modes.render.parsers;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.modes.render.data.RenderDataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RenderDataParser implements DataParser<RenderDataSet>{
    private static final Pattern RENDER_TIME_REG_EX = Pattern.compile("render time: (\\d+)");

    @Override
    public void parseLine(String line, RenderDataSet dataSet){
        Matcher matcher = RENDER_TIME_REG_EX.matcher(line);
        if (matcher.find())
        {
            dataSet.addValue(Double.parseDouble(matcher.group(1)));
        }
    }
}
