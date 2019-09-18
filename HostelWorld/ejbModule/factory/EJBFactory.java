package factory;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBFactory {
	private static Object getEJB(String jndipath) {
		try {
			final Hashtable<String, Object> jndiProps = new Hashtable<String, Object>();
			jndiProps.put("jboss.naming.client.ejb.context", true);
			jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProps);
			return context.lookup(jndipath);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T getServiceEJB(Class<T> cls) {
		return getEJB(cls,"service");
	}
	
	public static <T> T getDaoEJB(Class<T> cls) {
		return getEJB(cls,"dao");
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getEJB(Class<T> cls, String type){
		String classSimpleName = cls.getSimpleName();
		String jndipath = "ejb:/HostelWorld/"
				+ classSimpleName + "Impl!" + type
				+ "." + classSimpleName;
		return (T) EJBFactory.getEJB(jndipath);
	}
}
