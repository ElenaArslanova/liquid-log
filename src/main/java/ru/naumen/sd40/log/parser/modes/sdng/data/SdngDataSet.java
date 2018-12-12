package ru.naumen.sd40.log.parser.modes.sdng.data;

import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

import java.util.HashMap;

public class SdngDataSet implements DataSet {
    private ActionDoneDataSet actionDoneDataSet;
    private ErrorDataSet errorDataSet;

    public SdngDataSet(){
        actionDoneDataSet = new ActionDoneDataSet();
        errorDataSet = new ErrorDataSet();
    }

    public ActionDoneDataSet getActionsDoneData() { return actionDoneDataSet; }

    public ErrorDataSet getErrorsData() { return errorDataSet; }

    @Override
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();

        actionDoneDataSet.calculate();

        if (!actionDoneDataSet.isNan()) {
            fields.putAll(actionDoneDataSet.getFields());
            fields.putAll(errorDataSet.getFields());
        }

        return fields;
    }
}
