package ru.naumen.sd40.log.parser.sdng;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.TimeParserFactory;

@Component
@RequestScope
public class SdngTimeParserFactory implements TimeParserFactory{
    private TimeParser timeParser;

    public SdngTimeParserFactory(){
        timeParser = new SdngTimeParser();
    }

    @Override
    public TimeParser create(){
        return timeParser;
    }
}
