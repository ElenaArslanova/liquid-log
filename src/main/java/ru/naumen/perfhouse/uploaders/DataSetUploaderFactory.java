package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.dataset.DataSet;

public interface DataSetUploaderFactory<T extends DataSet>{
    DataSetUploader<T> create(UploaderParams params);
}
