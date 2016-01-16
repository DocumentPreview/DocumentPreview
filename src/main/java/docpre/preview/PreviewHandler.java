package docpre.preview;

import docpre.entities.PreviewFile;
import org.apache.http.client.ClientProtocolException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface PreviewHandler {

	void handle(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, String fileId) throws ServletException, IOException;
	
	PreviewFile get() throws ClientProtocolException, IOException;
	
	void download();

	File getPreviewFile();
	
	void preview() throws IOException;

	void process() throws ServletException, IOException;
	
	String getPreviewFileExtension();
}
