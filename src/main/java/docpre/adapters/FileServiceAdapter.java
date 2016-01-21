package docpre.adapters;

import docpre.entities.PreviewFile;

import java.io.InputStream;

/**
 * Created by chengke on 16/1/16.
 */
public interface FileServiceAdapter {

    PreviewFile info(String mFileId);

    InputStream get(String mFileId);
}
