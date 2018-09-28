package com.poc.frontend.facade;

import com.poc.frontend.backingbean.BKTestUser;

public interface TestUserFacade {

	void initial(BKTestUser bkTestUser) throws Exception;

	void saveNewUser(BKTestUser bkTestUser) throws Exception;

	void findAllUser(BKTestUser bkTestUser) throws Exception;

	void deleteUser(BKTestUser bkTestUser) throws Exception;

	void updateUser(BKTestUser bkTestUser) throws Exception;

}
