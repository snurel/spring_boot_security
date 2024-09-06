package dev.samet.security.service;

import dev.samet.security.models.AppUser;
import dev.samet.security.models.UserPrincipals;
import dev.samet.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipals(user);
    }
}
