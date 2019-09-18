package listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import factory.EJBFactory;
import service.UserInfoService;

/**
 * Application Lifecycle Listener implementation class TimeListener
 *
 */
@WebListener("/")
public class TimeListener implements ServletContextListener {

	
	private Timer timer = null;
    /**
     * Default constructor. 
     */
    public TimeListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
    	timer.cancel();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         timer = new Timer(true);
         timer.schedule(new DisplayDate(), 0, 1000*60*60*24);
    }
	
}

class DisplayDate extends TimerTask{
	private static UserInfoService userInfoService = EJBFactory.getServiceEJB(UserInfoService.class);
	@Override
	public void run() {
		userInfoService.testState();
	}
}
