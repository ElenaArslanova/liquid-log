package ru.naumen.sd40.log.parser.modes.render.data;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

import java.util.HashMap;

import static ru.naumen.sd40.log.parser.NumberUtils.getSafeDouble;
import static ru.naumen.sd40.log.parser.NumberUtils.roundToTwoPlaces;
import static ru.naumen.sd40.log.parser.modes.render.data.RenderDataType.Render.*;

public class RenderDataSet implements DataSet{
    private DescriptiveStatistics ds = new DescriptiveStatistics();

    public void addValue(Double value){
        ds.addValue(value);
    }

    public double getMaxRenderTime()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getMax()));
    }

    public double getMeanRenderTime()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getMean()));
    }

    public double getMinRenderTime()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getMin()));
    }

    public double getPercent50()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getPercentile(50.0)));
    }

    public double getPercent95()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getPercentile(95.0)));
    }

    public double getPercent99()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getPercentile(99.0)));
    }

    public double getPercent999()
    {
        return roundToTwoPlaces(getSafeDouble(ds.getPercentile(99.9)));
    }

    public double getStddevRenderTime()
    {
        return ds.getStandardDeviation();
    }

    public long getCount()
    {
        return ds.getN();
    }

    public boolean isNan()
    {
        return getCount() == 0;
    }

    @Override
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        fields.put(COUNT, getCount());
        fields.put(MIN, getMinRenderTime());
        fields.put(MEAN, getMeanRenderTime());
        fields.put(STDDEV, getStddevRenderTime());
        fields.put(PERCENTILE50, getPercent50());
        fields.put(PERCENTILE95, getPercent95());
        fields.put(PERCENTILE99, getPercent99());
        fields.put(PERCENTILE999, getPercent999());
        fields.put(MAX, getMaxRenderTime());
        return fields;
    }
}
