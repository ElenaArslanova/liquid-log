package ru.naumen.sd40.log.parser.modes.gc.data;

import com.google.common.collect.Lists;
import ru.naumen.sd40.log.parser.DataType;

import java.util.List;

import static ru.naumen.sd40.log.parser.modes.Constants.TIME;

public enum GCDataType implements DataType {
    GARBAGE_COLLECTION(GarbageCollection.getProps());

    private List<String> properties;

    GCDataType(List<String> properties){
        this.properties = properties;
    }

    @Override
    public List<String> getTypeProperties() {
        return properties;
    }

    public static class GarbageCollection
    {
        private GarbageCollection()
        {
        }

        public static final String GCTIMES = "gcTimes";
        public static final String AVARAGE_GC_TIME = "avgGcTime";
        public static final String MAX_GC_TIME = "maxGcTime";

        static List<String> getProps()
        {
            return Lists.newArrayList(TIME, GCTIMES, AVARAGE_GC_TIME, MAX_GC_TIME);
        }
    }
}
