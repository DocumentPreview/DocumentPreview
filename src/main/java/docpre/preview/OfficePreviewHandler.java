package docpre.preview;

import docpre.adapters.FileServiceAdapter;
import docpre.app.WebappContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.document.DocumentFormat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class OfficePreviewHandler extends BasePreviewHandler {

	private static Logger logger = Logger.getLogger(OfficePreviewHandler.class);

	public OfficePreviewHandler(FileServiceAdapter mFileServiceAdapter) {
		super(mFileServiceAdapter);
	}

	@Override
	public File getPreviewFile() {
		return new File("/Users/chengke/static/" + mFile.getUniqueKey() + "." + getPreviewFileExtension());
	}

	@Override
	public void preview() throws IOException {
		String basePath = "http://docpre.sweetvvck.com/";
		mResponse.sendRedirect(basePath + "static/preview/viewer.html?file=" + basePath + "static/" + mPreviewFile.getName());
	}

	@Override
	public void process() throws ServletException, IOException {
		download();
		OfficeDocumentConverter converter = WebappContext.get(mServletContext).getDocumentConverter();

		DocumentFormat outputFormat = converter.getFormatRegistry().getFormatByExtension(getPreviewFileExtension());
		long startTime = System.currentTimeMillis();
		converter.convert(mFile.getRealFile(), mPreviewFile);
		long conversionTime = System.currentTimeMillis() - startTime;
		logger.info(String.format("successful conversion: %s [%db] to %s in %dms", FilenameUtils.getExtension(mFile.getRealFile().getName()), mFile.getRealFile().length(), "pdf", conversionTime));

	}

	@Override
	public String getPreviewFileExtension() {
		return "pdf";
	}
}
