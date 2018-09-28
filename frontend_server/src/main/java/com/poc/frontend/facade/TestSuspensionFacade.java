package com.poc.frontend.facade;

import com.poc.frontend.backingbean.BKTestSuspension;

public interface TestSuspensionFacade {

	void initial(BKTestSuspension bkBean) throws Exception;

	void saveNewSuspension(BKTestSuspension bkBean) throws Exception;

	void findAllSuspension(BKTestSuspension bkBean) throws Exception;

	void deleteSuspension(BKTestSuspension bkBean) throws Exception;

	void updateSuspension(BKTestSuspension bkBean) throws Exception;

}
