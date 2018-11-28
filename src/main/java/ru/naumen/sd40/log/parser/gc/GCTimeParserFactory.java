package ru.naumen.sd40.log.parser.gc;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.TimeParserFactory;

@Component
@RequestScope
public class GCTimeParserFactory implements TimeParserFactory{
    private TimeParser timeParser;

    public GCTimeParserFactory(){
        timeParser = new GCTimeParser();
    }

    @Override
    public TimeParser create() {
        return timeParser;
    }
}
