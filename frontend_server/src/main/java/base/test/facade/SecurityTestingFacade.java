package base.test.facade;

import base.test.backingbean.BKSecurityTesting;

public interface SecurityTestingFacade {

//	public List<Faculty> fetchAllFaculty(BKTest bkBean) throws Exception;
//	
//	public List<TestName> fetchAllTestName(BKTest bkBean) throws Exception;
//
//	public void validateSave(BKTest bkBean) throws Exception;
//
//	public void prepareSave(BKTest bkBean) throws Exception;
//
//	public void save(BKTest bkBean) throws Exception;
//
//	public void testSaveNameTest() throws Exception;

	public void sqlInjection(BKSecurityTesting bkBean) throws Exception;

	public void preventSqlInjection(BKSecurityTesting bkBean) throws Exception;

}
