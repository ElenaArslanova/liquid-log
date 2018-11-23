package ru.naumen.sd40.log.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.naumen.perfhouse.DBCloseException;
import ru.naumen.perfhouse.uploaders.DataSetUploader;
import ru.naumen.perfhouse.uploaders.SdngUploader;
import ru.naumen.sd40.log.parser.dataset.SdngDataSet;
import ru.naumen.sd40.log.parser.dataset.SdngDataSetFactory;

import java.security.InvalidParameterException;

import static org.mockito.Mockito.*;

public class DataSetUploaderTest {
    private SdngUploader dbUploader;
    private SdngDataSetFactory dataSetFactory;

    @Before
    public void setUp(){
        dbUploader = Mockito.mock(SdngUploader.class);
        dataSetFactory = new SdngDataSetFactory();
    }

    @Test
    public void mustUploadOnIncreasedKey() throws DBCloseException {
        try (DataSetUploader<SdngDataSet> dataSetUploader = new DataSetUploader<>(dbUploader, dataSetFactory)){
            //when
            dataSetUploader.get(0L);
            dataSetUploader.get(5L);
        }
        //then
        verify(dbUploader).upload(eq(0L), any(SdngDataSet.class));
    }

    @Test
    public void mustUploadOnCloseIfHasData() throws DBCloseException{
        try (DataSetUploader<SdngDataSet> dataSetUploader = new DataSetUploader<>(dbUploader, dataSetFactory)) {
            //when
            dataSetUploader.get(0L);
        }
        //then
        verify(dbUploader).upload(eq(0L), any(SdngDataSet.class));
    }

    @Test
    public void mustNotUploadOnCloseIfHasNoData() throws DBCloseException{
        try (DataSetUploader<SdngDataSet> dataSetUploader = new DataSetUploader<>(dbUploader, dataSetFactory)) {
        }

        verify(dbUploader, never()).upload(anyLong(), any(SdngDataSet.class));
    }

    @Test(expected=InvalidParameterException.class)
    public void mustThrowOnInvalidOrder() throws DBCloseException{
        try (DataSetUploader<SdngDataSet> dataSetUploader = new DataSetUploader<>(dbUploader, dataSetFactory)) {
            //when
            dataSetUploader.get(5L);
            dataSetUploader.get(0L);
        }
    }
}
