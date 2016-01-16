package docpre.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class WebappContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		try {
			WebappContext.init(event.getServletContext());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		WebappContext.destroy(event.getServletContext());
	}

}
