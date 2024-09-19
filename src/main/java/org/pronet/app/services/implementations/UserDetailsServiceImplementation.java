package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User foundedUser = userRepository.findByUsernameContainingIgnoreCase(username);
        return CustomUserDetails.create(foundedUser);
    }

    public UserDetails loadUserById(Long userId)
            throws UsernameNotFoundException {
        Optional<User> foundedUser = userRepository.findById(userId);
        if (foundedUser.isPresent()) {
            User user = foundedUser.get();
            return CustomUserDetails.create(user);
        }
        return null;
    }
}
