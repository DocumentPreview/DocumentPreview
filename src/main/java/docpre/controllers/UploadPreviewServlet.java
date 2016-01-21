package docpre.controllers;

import docpre.adapters.FileServiceAdapter;
import docpre.adapters.UploadAdapter;
import docpre.app.WebappContext;
import docpre.entities.PreviewFile;
import docpre.execptions.PreviewExecption;
import docpre.preview.PreviewHandler;
import docpre.preview.PreviewHandlerFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/convert")
public class UploadPreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UploadPreviewServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 *
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	private final Logger logger = Logger.getLogger(getClass().getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "only multipart requests are allowed");
			return;
		}

		WebappContext webappContext = WebappContext.get(getServletContext());
		ServletFileUpload fileUpload = webappContext.getFileUpload();

		FileItem uploadedFile;
		try {
			uploadedFile = getUploadedFile(fileUpload, request);
		} catch (FileUploadException fileUploadException) {
			throw new ServletException(fileUploadException);
		}
		if (uploadedFile == null) {
			throw new NullPointerException("uploaded file is null");
		}
		String inputExtension = FilenameUtils.getExtension(uploadedFile.getName());

		// String baseName = FilenameUtils.getBaseName(uploadedFile.getName());
		File inputFile = File.createTempFile("" + System.currentTimeMillis(), "." + inputExtension);
		writeUploadedFile(uploadedFile, inputFile);
		PreviewHandler handler;
		try {
			PreviewFile previewFIle = new PreviewFile();
			previewFIle.setFilename(uploadedFile.getName());
			previewFIle.setRealFile(inputFile);
			previewFIle.setUniqueKey(System.currentTimeMillis() + "");
			FileServiceAdapter uploadAdapter = new UploadAdapter(previewFIle);
			handler = PreviewHandlerFactory.build(uploadedFile.getName(), uploadAdapter);
			handler.handle(getServletContext(), request, response, null);
		} catch (PreviewExecption e) {
			e.printStackTrace();
			inputFile.delete();
		}
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, File output) throws IOException {
		String basePath = "http://docpre.sweetvvck.win/";
		response.sendRedirect(basePath + "UploadPreviewServlet/generic/web/viewer.html?file=" + basePath + "static/" + output.getName());
	}

	@SuppressWarnings("unused")
	private void sendFile(File file, HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		response.setContentLength((int) file.length());
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			IOUtils.copy(inputStream, response.getOutputStream());
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	private void writeUploadedFile(FileItem uploadedFile, File destinationFile) throws ServletException {
		try {
			uploadedFile.write(destinationFile);
		} catch (Exception exception) {
			throw new ServletException("error writing uploaded file", exception);
		}
		uploadedFile.delete();
	}

	private FileItem getUploadedFile(ServletFileUpload fileUpload, HttpServletRequest request) throws FileUploadException {
		List<FileItem> fileItems = fileUpload.parseRequest(request);
		for (FileItem fileItem : fileItems) {
			if (!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}

}
