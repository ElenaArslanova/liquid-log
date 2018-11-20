package ru.naumen.sd40.log.parser.dataset;

import ru.naumen.sd40.log.parser.data.TopData;

public class TopDataSet implements DataSet{
    private TopData topData;

    public TopDataSet(){
        topData = new TopData();
    }

    public TopData getTopData()
    {
        return topData;
    }
}
