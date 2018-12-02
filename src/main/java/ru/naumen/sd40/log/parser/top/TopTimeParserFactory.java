package ru.naumen.sd40.log.parser.top;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.TimeParserFactory;

@Component
@RequestScope
public class TopTimeParserFactory implements TimeParserFactory{
    private TimeParser timeParser;

    public TopTimeParserFactory(){
        timeParser = new TopTimeParser();
    }

    @Override
    public TimeParser create() {
        return timeParser;
    }
}
