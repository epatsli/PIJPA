package pl.poznan.put.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.poznan.put.entity.User;
import pl.poznan.put.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService  {

	 @Autowired
	    private UserRepository userRepository;
	
	 @Override
	    public User findByLogin(String login) {
	        return userRepository.findByLogin(login);
	    }
}
