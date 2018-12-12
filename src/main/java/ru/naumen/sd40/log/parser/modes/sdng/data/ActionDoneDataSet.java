package ru.naumen.sd40.log.parser.modes.sdng.data;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

import java.util.ArrayList;
import java.util.HashMap;

import static ru.naumen.sd40.log.parser.modes.sdng.data.SdngDataType.PerformedActions.*;
import static ru.naumen.sd40.log.parser.modes.sdng.data.SdngDataType.PerformedActions.GET_CATALOGS_ACTION;
import static ru.naumen.sd40.log.parser.modes.sdng.data.SdngDataType.ResponseTimes.*;

public class ActionDoneDataSet implements DataSet {
    private ArrayList<Integer> times = new ArrayList<>();
    private double min;
    private double mean;
    private double stddev;
    private double percent50;
    private double percent95;
    private double percent99;

    private double percent999;
    private double max;
    private long count;
    private int addObjectActions = 0;
    private int editObjectsActions = 0;
    private int getListActions = 0;
    private int commentActions = 0;

    private int getFormActions = 0;

    private int getDtObjectActions = 0;

    private int getCatalogsAction = 0;

    private int searchActions = 0;

    private boolean isNan = true;

    private HashMap<String, Integer> actions = new HashMap<>();

    public void calculate()
    {
        DescriptiveStatistics ds = new DescriptiveStatistics();
        times.forEach(t -> ds.addValue(t));
        min = ds.getMin();
        mean = ds.getMean();
        stddev = ds.getStandardDeviation();
        percent50 = ds.getPercentile(50.0);
        percent95 = ds.getPercentile(95.0);
        percent99 = ds.getPercentile(99.0);
        percent999 = ds.getPercentile(99.9);
        max = ds.getMax();
        count = ds.getN();
        isNan = count == 0;
    }

    public int getListActions()
    {
        return getListActions;
    }

    public HashMap<String, Integer> getActionsCounter()
    {
        return actions;
    }

    public int getAddObjectActions()
    {
        return addObjectActions;
    }

    public int getCommentActions()
    {
        return commentActions;
    }

    public long getCount()
    {
        return count;
    }

    public int getDtObjectActions()
    {
        return getDtObjectActions;
    }

    public int getEditObjectsActions()
    {
        return editObjectsActions;
    }

    public int getFormActions()
    {
        return getFormActions;
    }

    public int getGetCatalogsAction() { return getCatalogsAction; }

    public double getMax()
    {
        return max;
    }

    public double getMean()
    {
        return mean;
    }

    public double getMin()
    {
        return min;
    }

    public double getPercent50()
    {
        return percent50;
    }

    public double getPercent95()
    {
        return percent95;
    }

    public double getPercent99()
    {
        return percent99;
    }

    public double getPercent999()
    {
        return percent999;
    }

    public int getSearchActions()
    {
        return searchActions;
    }

    public double getStddev()
    {
        return stddev;
    }

    public ArrayList<Integer> getTimes()
    {
        return times;
    }

    public boolean isNan()
    {
        return isNan;
    }

    public void incrementAddObjectActions(){
        addObjectActions++;
    }

    public void incrementEditObjectsActions(){
        editObjectsActions++;
    }

    public void incrementGetListActions(){
        getListActions++;
    }

    public void incrementCommentActions(){
        commentActions++;
    }

    public void incrementGetFormActions(){
        getFormActions++;
    }

    public void incrementGetDtObjectActions(){
        getDtObjectActions++;
    }

    public void incrementGetCatalogsAction(){
        getCatalogsAction++;
    }

    public void incrementSearchActions(){
        searchActions++;
    }

    @Override
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        fields.put(COUNT, getCount());
        fields.put(MIN, getMin());
        fields.put(MEAN, getMean());
        fields.put(STDDEV, getStddev());
        fields.put(PERCENTILE50, getPercent50());
        fields.put(PERCENTILE95, getPercent95());
        fields.put(PERCENTILE99, getPercent99());
        fields.put(PERCENTILE999, getPercent999());
        fields.put(MAX, getMax());
        fields.put(ADD_ACTIONS, getAddObjectActions());
        fields.put(EDIT_ACTIONS, getEditObjectsActions());
        fields.put(LIST_ACTIONS, getListActions());
        fields.put(COMMENT_ACTIONS, getCommentActions());
        fields.put(GET_FORM_ACTIONS, getFormActions());
        fields.put(GET_DT_OBJECT_ACTIONS, getDtObjectActions());
        fields.put(SEARCH_ACTIONS, getSearchActions());
        fields.put(GET_CATALOGS_ACTION, getGetCatalogsAction());
        return fields;
    }
}
