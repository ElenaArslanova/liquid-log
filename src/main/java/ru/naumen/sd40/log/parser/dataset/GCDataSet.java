package ru.naumen.sd40.log.parser.dataset;

import ru.naumen.sd40.log.parser.data.GCData;

public class GCDataSet implements DataSet {
    private GCData gcData;

    public GCDataSet(){
        gcData = new GCData();
    }

    public GCData getGcData()
    {
        return gcData;
    }
}
