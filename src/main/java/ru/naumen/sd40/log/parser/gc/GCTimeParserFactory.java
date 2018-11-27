package ru.naumen.sd40.log.parser.gc;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.TimeParserFactory;
import ru.naumen.sd40.log.parser.TimeParserParams;

@Component
@RequestScope
public class GCTimeParserFactory implements TimeParserFactory{
    @Override
    public TimeParser create(TimeParserParams params) {
        return new GCTimeParser();
    }
}
