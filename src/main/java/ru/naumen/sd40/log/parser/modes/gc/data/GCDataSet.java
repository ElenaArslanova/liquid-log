package ru.naumen.sd40.log.parser.modes.gc.data;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

import java.util.HashMap;

import static ru.naumen.sd40.log.parser.NumberUtils.getSafeDouble;
import static ru.naumen.sd40.log.parser.NumberUtils.roundToTwoPlaces;
import static ru.naumen.sd40.log.parser.modes.gc.data.GCDataType.GarbageCollection.*;

public class GCDataSet implements DataSet {
    private DescriptiveStatistics ds = new DescriptiveStatistics();

    public double getCalculatedAvg()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getMean()));
    }

    public long getGcTimes()
    {
        return ds.getN();
    }

    public double getMaxGcTime()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getMax()));
    }

    public boolean isNan()
    {
        return getGcTimes() == 0;
    }

    public void addValue(Double value){
        ds.addValue(value);
    }

    @Override
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        if (!isNan()){
            fields.put(GCTIMES, getGcTimes());
            fields.put(AVARAGE_GC_TIME, getCalculatedAvg());
            fields.put(MAX_GC_TIME, getMaxGcTime());
        }
        return fields;
    }
}
