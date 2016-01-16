package docpre.preview;

import docpre.adapters.FileServiceAdapter;
import docpre.entities.PreviewFile;
import org.apache.http.client.ClientProtocolException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class BasePreviewHandler implements PreviewHandler {
	protected PreviewFile mFile;
	protected String mFileId;
	protected File mPreviewFile;
	protected ServletContext mServletContext;
	protected HttpServletRequest mRequest;
	protected HttpServletResponse mResponse;
	protected FileServiceAdapter mFileServiceAdapter;

	public BasePreviewHandler(FileServiceAdapter mFileServiceAdapter) {
		this.mFileServiceAdapter = mFileServiceAdapter;
	}

	@Override
	public void handle(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, String fileId) throws ServletException, IOException {
		mServletContext = servletContext;
		mRequest = request;
		mResponse = response;
		mFileId = fileId;
		mFile = get();
		mPreviewFile = getPreviewFile();
		if (mPreviewFile.exists()) {
			preview();
			return;
		}

		process();
		preview();
	}

	@Override
	public void download() {
		InputStream in = mFileServiceAdapter.get(mFileId);
		mFile.setContent(in);
	}

	@Override
	public PreviewFile get() throws ClientProtocolException, IOException {
		return mFileServiceAdapter.info(mFileId);
	}

}
