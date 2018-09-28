package base.test.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import base.facade.BaseFacade;
import base.test.backingbean.BKSecurityTesting;
import base.test.domain.TestUser;

@Service("securityTestingFacade")
public class SecurityTestingFacadeImpl extends BaseFacade implements SecurityTestingFacade {
	private static final Logger log = LogManager.getLogger(SecurityTestingFacadeImpl.class);
//	@Autowired
//	private FacultyDao facultyDao;
//	@Autowired
//	private TestNameDao testNameDao;
//	@Autowired
//	protected SessionFactory sessionFactory;

//	@Transactional(readOnly = true, rollbackFor = java.lang.Exception.class)
//	public List<Faculty> fetchAllFaculty(BKTest bkBean) throws Exception {
//		return facultyDao.findAll();
//	}
//
//	@Transactional(readOnly = true, rollbackFor = java.lang.Exception.class)
//	public List<TestName> fetchAllTestName(BKTest bkBean) throws Exception {
//		return testNameDao.findAll();
//	}
//
//	@Override
//	public void validateSave(BKTest bkBean) throws Exception {
//		if (true) {
//			addErrorMessage("error.update.version");
//		}
//		if (JSFUtil.hasMessages()) {
//			return;
//		}
//		try {
//
//		} catch (HibernateOptimisticLockingFailureException ex) {
//			throw new FacadeException("error.update.version");
//		}
//	}
//
//	@Override
//	public void prepareSave(BKTest bkBean) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
//	public void save(BKTest bkBean) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
//	public void testSaveNameTest() throws Exception {
//		for (int uuid = 13; uuid <= 14; uuid++) {
//			if (uuid == 14) {
//				throw new Exception();
//			}
//			TestName item = new TestName();
//			item.setUuid(uuid + "");
//			item.setFirstName(item.getUuid());
//			item.setLastName(item.getUuid());
//			testNameDao.save(item);
//		}
//	}

//	@Transactional(readOnly = true, rollbackFor = java.lang.Exception.class)
	public void sqlInjection(BKSecurityTesting bkBean) throws Exception {
//		String sql = "select USERNAME, VERSION, PASSWORD, ";
//		sql += "USER_TYPE, USER_NAME_TH, USER_NAME_EN, EMAIL, ACTIVE_STAUS, ";
//		sql += "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE ";
//		sql += "from TEST_USER ";
//		sql += "where USERNAME = '" + bkBean.getUsername() + "' ";
//		sql += "and USER_TYPE = '" + bkBean.getUserType() + "' ";
//		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
//		List<Object[]> list = sqlQuery.list();
//		List<TestUser> userList = new ArrayList<TestUser>();
//		for (Object[] obj : list) {
//			TestUser item = new TestUser();
//			item.setUsername((String) obj[0]);
//			item.setUserType((String) obj[3]);
//			userList.add(item);
//		}
//		bkBean.setUserList(userList);
	}

//	@Transactional(readOnly = true, rollbackFor = java.lang.Exception.class)
	public void preventSqlInjection(BKSecurityTesting bkBean) throws Exception {
//		String hql = "from TestUser where username = :username and userType = :userType ";
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		query.setParameter("username", bkBean.getUsername());
//		query.setParameter("userType", bkBean.getUserType());
//		List<TestUser> userList = query.list();
//		bkBean.setUserList(userList);
	}

}
