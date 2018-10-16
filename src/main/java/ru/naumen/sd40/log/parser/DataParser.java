package ru.naumen.sd40.log.parser;

import java.io.IOException;
import java.text.ParseException;

public interface DataParser {
    void parseLine(String line, DataSet dataSet) throws IOException, ParseException;
}
