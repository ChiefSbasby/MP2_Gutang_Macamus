package Listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Mico Gutang
 */
public class DateListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
 
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        arg0.getServletContext().setAttribute("date", new java.util.Date());
    }
}
