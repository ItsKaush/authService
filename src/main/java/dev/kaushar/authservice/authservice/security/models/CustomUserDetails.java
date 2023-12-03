package dev.kaushar.authservice.authservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.kaushar.authservice.authservice.models.Role;
import dev.kaushar.authservice.authservice.models.User;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean accountNonLocked;
    private List<CustomGrantedAuthority> authorities;

    @Getter
    private Long userId;

    public CustomUserDetails(User user){
        authorities = new ArrayList<>();
        for(Role role : user.getRoles()){
            authorities.add(new CustomGrantedAuthority(role));
        }
        this.user = user;
        this.username=user.getEmailId();
        this.password = user.getPassword();
        this.accountNonLocked = true;
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.userId = user.getId();

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<CustomGrantedAuthority> authorities = new ArrayList<>();
//
//        for(Role role : user.getRoles()){
//            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
//        }

        return this.authorities;
    }


    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
