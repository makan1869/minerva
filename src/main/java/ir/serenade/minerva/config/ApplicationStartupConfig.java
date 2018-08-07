package ir.serenade.minerva.config;

import ir.serenade.minerva.domain.Role;
import ir.serenade.minerva.domain.User;
import ir.serenade.minerva.repository.RoleRepository;
import ir.serenade.minerva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by serenade on 8/6/18.
 */

@Component
public class ApplicationStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Role adminRole = roleRepository.findByAuthority("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
        }

        Role userRole = roleRepository.findByAuthority("ROLE_USER");
        if (userRole == null) {
            userRole = roleRepository.save(new Role("ROLE_USER"));
        }

        if (userService.findUserByEmail("hanieh@aban.mobi") == null) {
            User admin = new User();
            admin.setEmailAddress("hanieh@aban.mobi");
            admin.setPassword("hanieh");
            admin.addKeyword("آبان");
            admin.addRole(userRole);
            userService.saveUser(admin);
        }

    }

    public static String prettify(String str) {
        return str.replace('0', '\u06F0')
                .replace('1', '\u06F1')
                .replace('2', '\u06F2')
                .replace('3', '\u06F3')
                .replace('4', '\u06F4')
                .replace('5', '\u06F5')
                .replace('6', '\u06F6')
                .replace('7', '\u06F7')
                .replace('8', '\u06F8')
                .replace('9', '\u06F9');
    }
}
