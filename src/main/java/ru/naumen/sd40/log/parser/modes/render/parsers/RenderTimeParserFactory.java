package ru.naumen.sd40.log.parser.modes.render.parsers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.naumen.sd40.log.parser.TimeParser;
import ru.naumen.sd40.log.parser.TimeParserFactory;

@Component
@RequestScope
public class RenderTimeParserFactory implements TimeParserFactory {
    private TimeParser timeParser;

    public RenderTimeParserFactory(){
        timeParser = new RenderTimeParser();
    }

    @Override
    public TimeParser create() {
        return timeParser;
    }
}
