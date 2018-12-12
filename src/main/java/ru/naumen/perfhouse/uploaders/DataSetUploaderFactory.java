package ru.naumen.perfhouse.uploaders;

import ru.naumen.sd40.log.parser.modes.dataset.DataSet;

public interface DataSetUploaderFactory<T extends DataSet>{
    DataSetUploader<T> create(UploaderParams params);
}
