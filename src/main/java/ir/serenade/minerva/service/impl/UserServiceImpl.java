package ir.serenade.minerva.service.impl;

import java.util.Arrays;
import java.util.HashSet;

import ir.serenade.minerva.domain.Role;
import ir.serenade.minerva.domain.User;
import ir.serenade.minerva.repository.RoleRepository;
import ir.serenade.minerva.repository.UserRepository;
import ir.serenade.minerva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmailAddress(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       // Role userRole = roleRepository.findByAuthority("ROLE_ADMIN");
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User loadUserByUsername(String s) throws UsernameNotFoundException {
		User user =  findUserByEmail(s);
		if(user == null) {
			throw new UsernameNotFoundException("User "+ s + " Not Found");
		} else {
			return user;
		}
	}
}
