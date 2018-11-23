package ru.naumen.sd40.log.parser.dataset;

public class SdngDataSet implements DataSet{
    private ActionDoneDataSet actionDoneDataSet;
    private ErrorDataSet errorDataSet;

    public SdngDataSet(){
        actionDoneDataSet = new ActionDoneDataSet();
        errorDataSet = new ErrorDataSet();
    }

    public ActionDoneDataSet getActionsDoneData() { return actionDoneDataSet; }

    public ErrorDataSet getErrorsData() { return errorDataSet; }
}
