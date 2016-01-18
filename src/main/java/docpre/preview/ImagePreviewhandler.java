package docpre.preview;

import docpre.adapters.FileServiceAdapter;
import docpre.utils.GloabConfig;
import org.apache.commons.io.FilenameUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class ImagePreviewhandler extends BasePreviewHandler {
	static {
		ProcessStarter.setGlobalSearchPath(GloabConfig.get("IMAGEMAGICK_PATH"));
	}

	public ImagePreviewhandler(FileServiceAdapter mFileServiceAdapter) {
		super(mFileServiceAdapter);
	}

	@Override
	public File getPreviewFile() {
		return new File(GloabConfig.get("PREVIEW_FILE_PATH") + mFile.getUniqueKey() + "." + getPreviewFileExtension());
	}

	@Override
	public void preview() throws IOException {
		String basePath = GloabConfig.get("BASE_PATH");
		mResponse.sendRedirect(basePath + "docpre/textview.jsp?isImage=true&file=" + basePath + "static/" + mPreviewFile.getName());
	}

	@Override
	public void process() throws ServletException, IOException {
		download();

		IMOperation op = new IMOperation();
		op.addImage(mFile.getRealFile().getAbsolutePath() + "[0]");
		op.addImage(mPreviewFile.getAbsolutePath());
		ConvertCmd convert = new ConvertCmd();
		try {
			convert.run(op);
		} catch (InterruptedException | IM4JavaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPreviewFileExtension() {
		return "png";
	}

}
