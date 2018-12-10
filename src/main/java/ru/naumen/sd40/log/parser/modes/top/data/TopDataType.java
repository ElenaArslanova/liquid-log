package ru.naumen.sd40.log.parser.modes.top.data;

import com.google.common.collect.Lists;
import ru.naumen.sd40.log.parser.DataType;

import java.util.List;

import static ru.naumen.sd40.log.parser.modes.Constants.TIME;

public enum TopDataType implements DataType{
    TOP(Top.getProps());

    private List<String> properties;

    TopDataType(List<String> properties){
        this.properties = properties;
    }

    @Override
    public List<String> getTypeProperties() {
        return properties;
    }

    public static class Top
    {
        private Top()
        {

        }

        public static final String AVG_LA = "avgLa";
        public static final String AVG_CPU = "avgCpu";
        public static final String AVG_MEM = "avgMem";
        public static final String MAX_LA = "maxLa";
        public static final String MAX_CPU = "maxCpu";
        public static final String MAX_MEM = "maxMem";

        static List<String> getProps()
        {
            return Lists.newArrayList(TIME, AVG_LA, AVG_CPU, AVG_MEM, MAX_LA, MAX_CPU, MAX_MEM);
        }
    }
}
