package ru.naumen.sd40.log.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.naumen.perfhouse.DBUploader;

import java.security.InvalidParameterException;

import static org.mockito.Mockito.*;

public class DataSetUploaderTest {
    private DBUploader dbUploader;
    private DataSetUploader dataSetUploader;

    @Before
    public void setUp(){
        dbUploader = Mockito.mock(DBUploader.class);
        dataSetUploader = new DataSetUploader(dbUploader);
    }

    @Test
    public void mustUploadOnIncreasedKey(){
        //when
        dataSetUploader.get(0L);
        dataSetUploader.get(5L);

        //then
        verify(dbUploader).upload(eq(0L), any(DataSet.class));
    }

    @Test
    public void mustUploadOnClose(){
        //when
        dataSetUploader.get(0L);
        dataSetUploader.close();

        //then
        verify(dbUploader).upload(eq(0L), any(DataSet.class));
    }

    @Test(expected=InvalidParameterException.class)
    public void mustThrowOnInvalidOrder(){
        //when
        dataSetUploader.get(5L);
        dataSetUploader.get(0L);
    }
}
