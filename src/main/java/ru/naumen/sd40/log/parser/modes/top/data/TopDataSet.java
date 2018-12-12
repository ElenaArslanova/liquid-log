package ru.naumen.sd40.log.parser.modes.top.data;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

import java.util.HashMap;

import static ru.naumen.sd40.log.parser.NumberUtils.getSafeDouble;
import static ru.naumen.sd40.log.parser.NumberUtils.roundToTwoPlaces;
import static ru.naumen.sd40.log.parser.modes.top.data.TopDataType.Top.*;

public class TopDataSet implements DataSet {
    private DescriptiveStatistics laStat = new DescriptiveStatistics();
    private DescriptiveStatistics cpuStat = new DescriptiveStatistics();
    private DescriptiveStatistics memStat = new DescriptiveStatistics();

    public void addLa(double la)
    {
        laStat.addValue(la);
    }

    public void addCpu(double cpu)
    {
        cpuStat.addValue(cpu);
    }

    public void addMem(double mem)
    {
        memStat.addValue(mem);
    }

    public boolean isNan()
    {
        return laStat.getN() == 0 && cpuStat.getN() == 0 && memStat.getN() == 0;
    }

    public double getAvgLa()
    {
        return roundToTwoPlaces(getSafeDouble(laStat.getMean()));
    }

    public double getAvgCpuUsage()
    {
        return roundToTwoPlaces(getSafeDouble(cpuStat.getMean()));
    }

    public double getAvgMemUsage()
    {
        return roundToTwoPlaces(getSafeDouble(memStat.getMean()));
    }

    public double getMaxLa()
    {
        return roundToTwoPlaces(getSafeDouble(laStat.getMax()));
    }

    public double getMaxCpu()
    {
        return roundToTwoPlaces(getSafeDouble(cpuStat.getMax()));
    }

    public double getMaxMem()
    {
        return roundToTwoPlaces(getSafeDouble(memStat.getMax()));
    }

    @Override
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        if (!isNan()) {
            fields.put(AVG_LA, getAvgLa());
            fields.put(AVG_CPU, getAvgCpuUsage());
            fields.put(AVG_MEM, getAvgMemUsage());
            fields.put(MAX_LA, getMaxLa());
            fields.put(MAX_CPU, getMaxCpu());
            fields.put(MAX_MEM, getMaxMem());
        }
        return fields;
    }
}
