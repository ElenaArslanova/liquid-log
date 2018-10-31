package ru.naumen.perfhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.naumen.perfhouse.DBCloseException;
import ru.naumen.sd40.log.parser.LogParser;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;

@Controller
public class ParseController {
    @RequestMapping(path = "/parse", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity parseLog(
            @RequestParam(name = "influxDb") String influxDb,
            @RequestParam(name = "mode") String mode,
            @RequestParam(name = "logPath") String logPath,
            @RequestParam(name = "timeZone") String timeZone,
            @RequestParam(name = "logTrace") String logTrace
    )throws IOException, ParseException, InvalidParameterException, DBCloseException {
        boolean requiredLogTrace = logTrace.equals("required");
        LogParser.parse(influxDb, mode, logPath, timeZone, requiredLogTrace);
        return new ResponseEntity<>("Completed parsing", HttpStatus.OK);
    }
}
