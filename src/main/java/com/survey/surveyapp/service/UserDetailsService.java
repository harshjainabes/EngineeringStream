package com.survey.surveyapp.service;

import com.survey.surveyapp.entity.UserDetail;
import com.survey.surveyapp.model.UserInfo;
/**
 * @author Harsh Jain
 *
 * 
 */
public interface UserDetailsService {

	UserDetail findUserById(Long Id);

	UserDetail findUserByEmail(String email);

	void createUser(UserInfo userInfo);

}
