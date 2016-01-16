package docpre.entities;

import java.io.File;
import java.io.InputStream;

/**
 * Created by chengke on 16/1/16.
 */
public class PreviewFile {
    private String fileId;
    private String filename;
    private String uniqueKey;
    private InputStream content;
    private File realFile;

    public PreviewFile(){}

    public PreviewFile(String fileId, String filename, String uniqueKey, InputStream content, File realFile) {
        this.fileId = fileId;
        this.filename = filename;
        this.uniqueKey = uniqueKey;
        this.content = content;
        this.realFile = realFile;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public File getRealFile() {
        return realFile;
    }

    public void setRealFile(File realFile) {
        this.realFile = realFile;
    }

    @Override
    public String toString() {
        return "PreviewFile{" +
                "fileId='" + fileId + '\'' +
                ", filename='" + filename + '\'' +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", content=" + content +
                ", realFile=" + realFile +
                '}';
    }
}
