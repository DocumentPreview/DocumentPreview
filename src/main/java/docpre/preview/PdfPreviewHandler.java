package docpre.preview;

import docpre.adapters.FileServiceAdapter;
import docpre.utils.GloabConfig;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PdfPreviewHandler extends BasePreviewHandler {

	public PdfPreviewHandler(FileServiceAdapter mFileServiceAdapter) {
		super(mFileServiceAdapter);
	}

	@Override
	public File getPreviewFile() {
		return new File(GloabConfig.get("PREVIEW_FILE_PATH") + mFile.getUniqueKey());
	}

	@Override
	public void preview() throws IOException {
		String basePath = GloabConfig.get("BASE_PATH");
		mResponse.sendRedirect(basePath + "static/preview/viewer.html?file=" + basePath + "static/" + mPreviewFile.getName());
	}

	@Override
	public void process() throws ServletException, IOException {
		download();
		Files.copy(mFile.getRealFile().toPath(), mPreviewFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	@Override
	public String getPreviewFileExtension() {
		return "pdf";
	}
	
}
