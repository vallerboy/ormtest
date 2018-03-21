package pl.oskarpolak.ormtest.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserDetails implements UserDetailsService {

    final UserRepository userRepository;

    @Autowired
    public UserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByLogin(s);
            if(userModel == null){
                throw new UsernameNotFoundException("User " + s + " not found");
            }
        GrantedAuthority authority = new SimpleGrantedAuthority(userModel.getUserType().name());
        return new User(userModel.getLogin(), userModel.getPassword(), Collections.singletonList(authority));
    }
}
