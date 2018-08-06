package ir.serenade.minerva.service;


import ir.serenade.minerva.domain.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
