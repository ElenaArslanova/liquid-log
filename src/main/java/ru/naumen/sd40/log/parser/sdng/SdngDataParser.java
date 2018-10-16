package ru.naumen.sd40.log.parser.sdng;

import ru.naumen.sd40.log.parser.DataParser;
import ru.naumen.sd40.log.parser.DataSet;

public class SdngDataParser implements DataParser {
    @Override
    public void parseLine(String line, DataSet dataSet) {
        dataSet.getErrors().parseLine(line, dataSet);
        dataSet.getActionsDone().parseLine(line, dataSet);
    }
}
