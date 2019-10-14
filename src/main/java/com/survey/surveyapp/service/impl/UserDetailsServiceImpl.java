package com.survey.surveyapp.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.surveyapp.entity.UserDetail;
import com.survey.surveyapp.model.UserInfo;
import com.survey.surveyapp.repository.UserDetailsRepository;
import com.survey.surveyapp.service.UserDetailsService;
/**
 * @author Harsh Jain
 *
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetail findUserById(Long id) {
		return userDetailsRepository.findFirstById(id);
	}

	@Override
	public void createUser(UserInfo userInfo) {

		UserDetail userDetail = new UserDetail();
		userDetail.setName(userInfo.getName());
		userDetail.setEmail(userInfo.getEmail());
		userDetail.setIsactive(true);
		userDetail.setLastLogin(new Date());

		userDetailsRepository.save(userDetail);
	}

	@Override
	public UserDetail findUserByEmail(String email) {
		return userDetailsRepository.findFirstByEmail(email);
	}

}
