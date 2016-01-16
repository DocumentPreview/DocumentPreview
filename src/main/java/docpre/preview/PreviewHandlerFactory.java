package docpre.preview;

import docpre.adapters.FileServiceAdapter;
import docpre.execptions.PreviewExecption;
import docpre.utils.Utils;
import org.apache.commons.io.FilenameUtils;

import java.net.URLConnection;

public class PreviewHandlerFactory {

	public static PreviewHandler build(String filename, FileServiceAdapter uploadAdapter) throws PreviewExecption {
		PREVIEW_TYPES type = PREVIEW_TYPES.NOT_SUPPORT;
		String fileExtension = FilenameUtils.getExtension(filename);
		if (Utils.isSupportOfficeFile(fileExtension)) {
			type = PREVIEW_TYPES.OFFICE;
		} else if (Utils.isTextFile(fileExtension)) {
			type = PREVIEW_TYPES.TEXT;
		} else if (Utils.isImageFile(fileExtension)) {
			type = PREVIEW_TYPES.SPECIAL_IMAGE;
		} else if (type == PREVIEW_TYPES.NOT_SUPPORT && URLConnection.guessContentTypeFromName(filename) != null) {
			type = Utils.translateContentType(URLConnection.guessContentTypeFromName(filename));
		}
		PreviewHandler handler = null;
		switch (type) {
		case PDF:
			handler = new PdfPreviewHandler(uploadAdapter);
			break;
		case OFFICE:
			handler = new OfficePreviewHandler(uploadAdapter);
			break;
		case TEXT:
			handler = new TextPreviewHandler(uploadAdapter);
			break;
		case NORMAL_IMAGE:
		case SPECIAL_IMAGE:
			handler = new ImagePreviewhandler(uploadAdapter);
			break;
		case NOT_SUPPORT:
		default:
			handler = new DefaultPreviewHandler(uploadAdapter);
			break;
		}
		return handler;
	}
}
