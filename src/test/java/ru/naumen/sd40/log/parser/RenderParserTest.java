package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Test;
import ru.naumen.sd40.log.parser.modes.render.data.RenderDataSet;
import ru.naumen.sd40.log.parser.modes.render.parsers.RenderDataParser;
import ru.naumen.sd40.log.parser.modes.render.parsers.RenderTimeParser;

import java.text.ParseException;

public class RenderParserTest {
    private RenderDataSet dataSet = new RenderDataSet();
    private String log = "102725313 2017-12-13 03:48:56,048 [http-nio-8443-exec-83 operator1 fs000080000m0jaoh10o2ito00] DEBUG AdvFormEngine - session: fs000080000m0jaoh10o2ito00 render time: 18";

    @Test
    public void mustParseRenderData() {
        //given
        RenderDataParser parser = new RenderDataParser();

        //when
        parser.parseLine("render time: 16", dataSet);
        parser.parseLine("render time: 17", dataSet);
        parser.parseLine("render time: 20", dataSet);

        //then
        Assert.assertEquals(16, dataSet.getMinRenderTime(), 0.001);
        Assert.assertEquals(17.67, dataSet.getMeanRenderTime(), 0.001);
        Assert.assertEquals(20, dataSet.getMaxRenderTime(), 0.001);
    }

    @Test
    public void mustParserRenderTime() throws ParseException {
        //given
        RenderTimeParser parser = new RenderTimeParser();

        //when
        long time = parser.parseLine(log);

        //then
        Assert.assertEquals(1513136936048L, time);
    }
}
