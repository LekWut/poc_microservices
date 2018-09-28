package com.poc.frontend.facade;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.frontend.backingbean.BKTestUser;
import com.poc.frontend.dto.UserDto;

import base.facade.BaseFacade;

@Service("testUserFacade")
public class TestUserFacadeImpl extends BaseFacade implements TestUserFacade {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(TestUserFacadeImpl.class);
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void initial(BKTestUser bkTestUser) throws Exception {
		findAllTestUser(bkTestUser);
	}

	@Override
	public void saveNewUser(BKTestUser bkTestUser) throws Exception {
		/*
		UserDto responseUser = restTemplate.postForObject(getApiGatewayHost() + "/admin/testUser/",bkTestUser.getUserDto(), UserDto.class);
		*/
		
		/*
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<UserDto> request = new HttpEntity<UserDto>(bkTestUser.getUserDto(), headers);
		ResponseEntity<UserDto> response = restTemplate.postForEntity(getApiGatewayHost() + "/admin/testUser/", request,
				UserDto.class);
		UserDto responseUser = response.getBody();
		*/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", getCurrentUser().getCurrentToken());
		HttpEntity<UserDto> request = new HttpEntity<UserDto>(bkTestUser.getUserDto(), headers);
		ResponseEntity<UserDto> response = restTemplate.exchange(getApiGatewayHost() + "/admin/testUser/", HttpMethod.POST,
				request, UserDto.class);
		UserDto responseUser = response.getBody();

		log.debug("responseUser.getId()=" + responseUser.getId());
	}

	@Override
	public void findAllUser(BKTestUser bkTestUser) throws Exception {
		findAllTestUser(bkTestUser);
	}

	private void findAllTestUser(BKTestUser bkTestUser) throws Exception {
		/*
		ResponseEntity<Iterable<UserDto>> response = restTemplate.exchange(getApiGatewayHost() + "/admin/testUser/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<UserDto>>() {
				});
		Iterable<UserDto> iterable = response.getBody();
		List<UserDto> tempList = new ArrayList<UserDto>();
		iterable.iterator().forEachRemaining(tempList::add);
		bkTestUser.setUserList(tempList);
		*/
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", getCurrentUser().getCurrentToken());
		HttpEntity<List<UserDto>> request = new HttpEntity<List<UserDto>>(headers);
		ResponseEntity<List<UserDto>> response = restTemplate.exchange(getApiGatewayHost() + "/admin/testUser/all",
				HttpMethod.GET, request, new ParameterizedTypeReference<List<UserDto>>() {
				});
		List<UserDto> resultList = response.getBody();
		bkTestUser.setUserList(resultList);

		for (UserDto user : bkTestUser.getUserList()) {
			log.debug("user.getId()=" + user.getId());
		}
	}

	@Override
	public void deleteUser(BKTestUser bkTestUser) throws Exception {
		/*
		restTemplate.delete(getApiGatewayHost() + "/admin/testUser/" + bkTestUser.getUserDto().getId());
		*/
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", getCurrentUser().getCurrentToken());
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(getApiGatewayHost() + "/admin/testUser/" + bkTestUser.getUserDto().getId(), HttpMethod.DELETE, request, String.class);
		String responseBody = response.getBody();	
		log.debug("responseBody=" + responseBody);
	}

	@Override
	public void updateUser(BKTestUser bkTestUser) throws Exception {
		/*
		restTemplate.put(getApiGatewayHost() + "/admin/testUser/" + bkTestUser.getUserDto().getId(), bkTestUser.getUserDto());
		*/
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", getCurrentUser().getCurrentToken());
		HttpEntity<UserDto> request = new HttpEntity<UserDto>(bkTestUser.getUserDto(), headers);
		ResponseEntity<UserDto> response = restTemplate.exchange(
				getApiGatewayHost() + "/admin/testUser/" + bkTestUser.getUserDto().getId(), HttpMethod.PUT, request,
				UserDto.class);
		UserDto responseUser = response.getBody();	
		log.debug("responseUser.getId()=" + responseUser.getId());
		
	}
	
}
