package ir.serenade.minerva.service;


import ir.serenade.minerva.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
