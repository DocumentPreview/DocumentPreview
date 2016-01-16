package docpre.controllers;

import docpre.execptions.PreviewExecption;
import docpre.preview.PreviewHandler;
import docpre.preview.PreviewHandlerFactory;
import docpre.utils.DriveUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PreviewServlet
 */
@WebServlet("/preview")
public class PreviewServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(PreviewServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileId = request.getParameter("fileId");
		log.debug("Request param->fileId: " + fileId);
		if (fileId == null) {
			throw new ServletException("fileId required");
		}
		PreviewHandler handler;
//		try {
//			handler = PreviewHandlerFactory.build(driveFile.getFname(), uploadAdapter);
//			handler.handle(getServletContext(), request, response);
//		} catch (PreviewExecption e) {
//			e.printStackTrace();
//		}
	}

}
