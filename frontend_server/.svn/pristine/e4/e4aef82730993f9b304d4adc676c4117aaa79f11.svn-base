package base.spring.security;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.utils.AppUtil;
import base.utils.FacadeException;

public class LdapConnector {
	private static final Logger log = LogManager.getLogger(LdapConnector.class);
	private static String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static String PROVIDER_URL = AppUtil.getAppConfigByKey("LDAP_PROVIDER_URL");
	private static String MANAGER_DN = AppUtil.getAppConfigByKey("LDAP_MANAGER_DN");
	private static String MANAGER_PASSWORD = AppUtil.getAppConfigByKey("LDAP_MANAGER_PASSWORD");
	private static String SEARCH_NAME = AppUtil.getAppConfigByKey("LDAP_SEARCH_NAME");
	private static String SEARCH_ATTRIBUTE = AppUtil.getAppConfigByKey("LDAP_SEARCH_ATTRIBUTE");

	@SuppressWarnings("unchecked")
	public static void connectWithManager(String uid, String password) throws Exception {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, MANAGER_DN);
		env.put(Context.SECURITY_CREDENTIALS, MANAGER_PASSWORD);
		env.put("java.naming.ldap.version", "3");

		DirContext ctx = null;
		DirContext bindCtx = null;
		NamingEnumeration results = null;
		try {
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search(SEARCH_NAME, "(&(" + SEARCH_ATTRIBUTE + "=" + uid + "))", controls);
			if (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				javax.naming.directory.Attributes attributes = searchResult.getAttributes();
				NamingEnumeration atts = attributes.getAll();
				while (atts.hasMoreElements()) {
					log.debug(atts.nextElement().toString());
				}
//				Attribute attr1 = attributes.get("sAMAccountName");
//				String sAMAccountName = (String) attr1.get();
//				log.debug("sAMAccountName = " + sAMAccountName);
				log.debug("DN = " + searchResult.getNameInNamespace());
				Hashtable<String, Object> bindEnv = new Hashtable<String, Object>();
				bindEnv.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
				bindEnv.put(Context.PROVIDER_URL, PROVIDER_URL);
				bindEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
				bindEnv.put(Context.SECURITY_PRINCIPAL, searchResult.getNameInNamespace());
				bindEnv.put(Context.SECURITY_CREDENTIALS, password);
				bindEnv.put("java.naming.ldap.version", "3");
				bindCtx = new InitialDirContext(bindEnv);
			} else {
				throw new FacadeException("error.login.ldap.userNotFound");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
			if (bindCtx != null) {
				try {
					bindCtx.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void connect(String uid, String password) throws Exception {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + uid + ",ou=People,ou=st,dc=kmutt,dc=ac,dc=th");
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put("java.naming.ldap.version", "3");

		DirContext ctx = null;
		NamingEnumeration results = null;
		try {
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("dc=kmutt,dc=ac,dc=th", "(&(uid=" + uid + "))", controls);
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				javax.naming.directory.Attributes attributes = searchResult.getAttributes();
				NamingEnumeration atts = attributes.getAll();
				while (atts.hasMoreElements()) {
					log.debug(atts.nextElement().toString());
				}
//				Attribute attr1 = attributes.get("uid");
//				String uidAttr = (String) attr1.get();
//				log.debug("uid = " + uidAttr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
