package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.dataset.DataSet;

import java.io.IOException;
import java.text.ParseException;

public interface DataParser<T extends DataSet> {
    void parseLine(String line, T dataSet) throws IOException, ParseException;
}
