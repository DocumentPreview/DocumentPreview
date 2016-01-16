package docpre.adapters;

import docpre.entities.PreviewFile;

import java.io.InputStream;

/**
 * Created by chengke on 16/1/16.
 */
public class UploadAdapter implements FileServiceAdapter{

    private PreviewFile mFile;

    public UploadAdapter(PreviewFile mFile) {
        this.mFile = mFile;
    }

    public void setmFile(PreviewFile mFile) {
        this.mFile = mFile;
    }

    @Override
    public PreviewFile info(String mFileId) {
        return mFile;
    }

    @Override
    public InputStream get(String mFileId) {
        return mFile.getContent();
    }
}
