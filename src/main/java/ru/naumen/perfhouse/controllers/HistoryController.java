package ru.naumen.perfhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.naumen.perfhouse.statdata.StatData;
import ru.naumen.perfhouse.statdata.StatDataService;
import ru.naumen.sd40.log.parser.DataType;
import ru.naumen.sd40.log.parser.modes.Mode;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HistoryController {
    @Autowired
    private StatDataService service;
    private Map<String, Mode> parsingModes;
    private HashMap<String, DataType> dataTypes = new HashMap<>();
    private static final String NO_HISTORY_VIEW = "no_history";
    private static final String HISTORY_VIEW = "history";
    private static final String DATA_TYPE = "datatype";

    @Inject
    public HistoryController(Map<String, Mode> parsingModes){
        this.parsingModes = parsingModes;
        for (Mode mode: parsingModes.values()){
            DataType[] dataTypes = mode.getDataTypes();
            for (DataType dataType: dataTypes){
                this.dataTypes.put(dataType.toString().toLowerCase(), dataType);
            }
        }
    }

    @RequestMapping(path = "/history/{client}/{datatype}/{year}/{month}/{day}")
    public ModelAndView historyByDay(@PathVariable("client") String client,
                                   @PathVariable("datatype") String dataType,
                                   @PathVariable(name = "year", required = false) int year,
                                   @PathVariable(name = "month", required = false) int month,
                                   @PathVariable(name = "day", required = false) int day) throws ParseException
    {
        return getDataAndViewByDate(client, this.dataTypes.get(dataType), year, month, day, HISTORY_VIEW,
                false);
    }

    @RequestMapping(path = "/history/{client}/{datatype}/{year}/{month}")
    public ModelAndView historyByMonth(@PathVariable("client") String client,
                                     @PathVariable("datatype") String dataType,
                                     @PathVariable(name = "year", required = false) int year,
                                     @PathVariable(name = "month", required = false) int month) throws ParseException
    {
        return getDataAndViewByDate(client, this.dataTypes.get(dataType), year, month, 0, HISTORY_VIEW,
                true);
    }

    @RequestMapping(path = "/history/{client}/{datatype}")
    public ModelAndView historyLast864(@PathVariable("client") String client,
                                       @PathVariable("datatype") String dataType,
                                       @RequestParam(name = "count", defaultValue = "864") int count)
            throws ParseException
    {
        return getDataAndView(client, this.dataTypes.get(dataType), count, HISTORY_VIEW);
    }

    @RequestMapping(path = "/history/{client}/custom/{datatype}")
    public ModelAndView customHistory(@PathVariable("client") String client, @RequestParam("from") String from,
                                     @RequestParam("to") String to, @RequestParam("maxResults") int maxResults,
                                     @PathVariable("datatype") String dataType)
            throws ParseException
    {
        return getDataAndViewCustom(client, this.dataTypes.get(dataType), from, to, maxResults, HISTORY_VIEW);
    }

    @RequestMapping(path = "/datatype/{client}")
    public ModelAndView setupCustomModal(@PathVariable("client") String client,
                                         @RequestParam(value = "year", required = false) Integer year,
                                         @RequestParam(value = "month", required = false) Integer month,
                                         @RequestParam(value = "day", required = false) Integer day,
                                         @RequestParam(value = "from", required = false) String from,
                                         @RequestParam(value = "to", required = false) String to,
                                         @RequestParam(value = "maxResults", defaultValue = "1000") int count)
            throws ParseException{
        Map<String, Object> model = new HashMap<>();
        model.put("client", client);
        if (from != null && to != null) {
            model.put("custom", true);
            model.put("from", from);
            model.put("to", to);
            model.put("maxResults", count);
        }
        else {
            model.put("year", year);
            model.put("month", month);
            model.put("day", day);
        }
        model.put("datatypes", dataTypes.keySet());
        return new ModelAndView(DATA_TYPE, model, HttpStatus.OK);
    }

    private ModelAndView getDataAndViewByDate(String client, DataType type, int year, int month, int day,
                                              String viewName, boolean compress) throws ParseException
    {
        StatData dataDate = service.getDataDate(client, type, year, month, day);
        if (dataDate == null)
        {
            return new ModelAndView(NO_HISTORY_VIEW);
        }

        dataDate = compress ? service.compress(dataDate, 3 * 60 * 24 / 5) : dataDate;
        Map<String, Object> model = new HashMap<>();
        model.put("client", client);
        model.put("year", year);
        model.put("month", month);
        model.put("day", day);
        model.put("datatypes", dataTypes.keySet());
        model.put("requested_datatype", type.toString());
        model.put("statdata", dataDate.asModel());
        return new ModelAndView(viewName, model, HttpStatus.OK);
    }

    private ModelAndView getDataAndViewCustom(String client, DataType dataType, String from, String to, int maxResults,
                                              String viewName) throws ParseException
    {
        StatData data = service.getDataCustom(client, dataType, from, to);
        if (data == null)
        {
            return new ModelAndView(NO_HISTORY_VIEW);
        }
        data = service.compress(data, maxResults);
        Map<String, Object> model = new HashMap<>();
        model.put("client", client);
        model.put("custom", true);
        model.put("from", from);
        model.put("to", to);
        model.put("maxResults", maxResults);
        model.put("datatypes", dataTypes.keySet());
        model.put("requested_datatype", dataType.toString());
        model.put("statdata", data.asModel());
        return new ModelAndView(viewName, model, HttpStatus.OK);
    }

    private ModelAndView getDataAndView(String client, DataType dataType, int count, String viewName)
            throws ParseException
    {
        StatData data = service.getData(client, dataType, count);
        if (data == null)
        {
            return new ModelAndView(NO_HISTORY_VIEW);
        }
        Map<String, Object> model = new HashMap<>();
        model.put("client", client);
        model.put("dataTypes", dataTypes.keySet());
        model.put("requested_datatype", dataType.toString());
        model.put("statdata", data.asModel());
        return new ModelAndView(viewName, model, HttpStatus.OK);
    }


}
