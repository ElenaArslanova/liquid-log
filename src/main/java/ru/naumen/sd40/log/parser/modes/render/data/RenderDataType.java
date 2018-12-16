package ru.naumen.sd40.log.parser.modes.render.data;

import com.google.common.collect.Lists;
import ru.naumen.sd40.log.parser.DataType;

import java.util.List;

import static ru.naumen.sd40.log.parser.modes.Constants.TIME;

public enum RenderDataType implements DataType{
    RENDER(Render.getProps());

    private List<String> properties;

    RenderDataType(List<String> properties){
        this.properties = properties;
    }

    @Override
    public List<String> getTypeProperties() {
        return properties;
    }

    public static class Render{
        private Render()
        {
        }

        public static final String PERCENTILE50 = "render_percent50";
        public static final String PERCENTILE95 = "render_percent95";
        public static final String PERCENTILE99 = "render_percent99";
        public static final String PERCENTILE999 = "render_percent999";
        public static final String MAX = "render_max";
        public static final String MIN = "render_min";
        public static final String COUNT = "render_count";
        public static final String MEAN = "render_mean";
        public static final String STDDEV = "render_stddev";

        static List<String> getProps()
        {
            return Lists.newArrayList(TIME, COUNT, MEAN, STDDEV, PERCENTILE50, PERCENTILE95, PERCENTILE99,
                    PERCENTILE999, MAX, MIN);
        }
    }
}
