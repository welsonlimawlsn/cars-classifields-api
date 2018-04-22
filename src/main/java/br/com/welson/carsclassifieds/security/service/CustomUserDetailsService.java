package br.com.welson.carsclassifieds.security.service;

import br.com.welson.carsclassifieds.persistence.model.user.ApplicationUser;
import br.com.welson.carsclassifieds.persistence.repositoty.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public CustomUserDetailsService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = loadApplicationUserByUsername(username);
        return new CustomUserDetails(applicationUser);
    }

    public ApplicationUser loadApplicationUserByUsername(String username) {
        return applicationUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("ApplicationUser not found"));
    }

    private final static class CustomUserDetails extends ApplicationUser implements UserDetails {

        CustomUserDetails(ApplicationUser applicationUser) {
            super(applicationUser);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorityListAdmin = createAuthorityList("ROLE_ADMIN");
            List<GrantedAuthority> authorityListSeller = createAuthorityList("ROLE_SELLER");
            return this.getAdmin() != null ? authorityListAdmin : authorityListSeller;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
