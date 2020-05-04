
	
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


public class Main{
	


	
	public static void main(String[] args) {
		////////////
		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:18080");
		env.put("jboss.naming.client.ejb.context", true);
		try {
			DirContext ctx = new InitialDirContext(env);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String jndiName = "evaders-ear/evaders-ejb/ServiceEvent!services.ServiceEventRemote";
				Context context = null;
		try {
			context = new InitialContext(env);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
