package ru.naumen.sd40.log.parser.dataset;

import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;

public class SdngDataSet implements DataSet{
    private ActionDoneData actionsDoneData;
    private ErrorData errorsData;

    public SdngDataSet(){
        actionsDoneData = new ActionDoneData();
        errorsData = new ErrorData();
    }

    public ActionDoneData getActionsDoneData() { return actionsDoneData; }

    public ErrorData getErrorsData() { return errorsData; }
}
