package base.test;

//import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;

import base.backingbean.BackingBean;
import base.test.facade.JunitTestFacade;

/*@ContextConfiguration({ "file:WebContent/WEB-INF/applicationContext.xml" })*/
//@ContextConfiguration(locations = "/applicationContext.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@Commit //@Rollback
public class JunitTest extends BackingBean {

	private static final Logger log = LogManager.getLogger(JunitTest.class);
//	@Autowired
//	private HibernateTemplate hibernateTemplate;
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	@Autowired
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//	@Autowired
//	protected SessionFactory sessionFactory;
	@Autowired
	private JunitTestFacade junitTestFacade;

	//@Test
	public void run() {
		try {
			test3();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void test1() throws Exception {
		junitTestFacade.testMethod();
	}

	private void test2() throws Exception {
		junitTestFacade.addTestUser();
		log.debug("saveComplete");
	}

	private void test3() throws Exception {
		junitTestFacade.findAllTestUser();
	}

}