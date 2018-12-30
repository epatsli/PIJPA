package pl.poznan.put.service;

import pl.poznan.put.entity.User;

public interface UserService {
	
	 User findByLogin(String login);
}
