package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;
import ru.naumen.sd40.log.parser.data.GCData;
import ru.naumen.sd40.log.parser.data.TopData;

/**
 * Created by doki on 22.10.16.
 */
public class DataSet
{
    private ActionDoneData actionsDoneData;
    private ErrorData errorsData;
    private GCData gcData;
    private TopData topData;

    public DataSet()
    {
        actionsDoneData = new ActionDoneData();
        errorsData = new ErrorData();
        gcData = new GCData();
        topData = new TopData();
    }

    public ActionDoneData getActionsDoneData() { return actionsDoneData; }

    public ErrorData getErrorsData() { return errorsData; }

    public GCData getGcData()
    {
        return gcData;
    }

    public TopData getTopData()
    {
        return topData;
    }
}
