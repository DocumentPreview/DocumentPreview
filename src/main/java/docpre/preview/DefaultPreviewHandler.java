package docpre.preview;

import docpre.adapters.FileServiceAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DefaultPreviewHandler extends BasePreviewHandler {

	public DefaultPreviewHandler(FileServiceAdapter mFileServiceAdapter) {
		super(mFileServiceAdapter);
	}

	@Override
	public void handle(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, String fileId) throws ServletException, IOException {
		request.setAttribute("notSupport", true);
		servletContext.getRequestDispatcher("/textview.jsp").forward(request, response);
	}

	@Override
	public File getPreviewFile() {
		return null;
	}

	@Override
	public void preview() throws IOException {
	}

	@Override
	public void process() throws ServletException, IOException {
	}

	@Override
	public String getPreviewFileExtension() {
		return null;
	}

}
