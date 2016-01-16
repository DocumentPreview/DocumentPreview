package docpre.preview;

import docpre.adapters.FileServiceAdapter;
import docpre.utils.DriveUtils;
import org.apache.commons.io.IOUtils;
import org.mozilla.universalchardet.CharsetListener;
import org.mozilla.universalchardet.UniversalDetector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TextPreviewHandler extends BasePreviewHandler {

	public TextPreviewHandler(FileServiceAdapter mFileServiceAdapter) {
		super(mFileServiceAdapter);
	}

	@Override
	public void handle(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, String fileId) throws IOException, ServletException {
		mFile = get();
		String content = parseTextContent(mFile.getRealFile());
		request.setAttribute("isText", true);
		request.setAttribute("content", content);
		servletContext.getRequestDispatcher("/textview.jsp").forward(request, response);
	}
	
	private String parseTextContent(File outputFile) throws IOException{
		byte[] buf = new byte[4096];

		// (1)
		UniversalDetector detector = new UniversalDetector(new CharsetListener() {
			
			@Override
			public void report(String charset) {
				
			}
		});
		InputStream in = new FileInputStream(outputFile);
		InputStream dataIn = new FileInputStream(outputFile);
		// (2)
		try {
			int nread;
			while ((nread = in.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			// (3)
			detector.dataEnd();

			// (4)
			String encoding = detector.getDetectedCharset();
			if (encoding != null && !"WINDOWS-1252".equalsIgnoreCase(encoding)) {
				System.out.println("Detected encoding = " + encoding);
			} else if ("WINDOWS-1252".equalsIgnoreCase(encoding)){
				encoding = "GBK";
			} else {
			
				System.out.println("No encoding detected.");
			}
			// (5)
			detector.reset();
			return IOUtils.toString(dataIn, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			dataIn.close();
		}
		return null;
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
