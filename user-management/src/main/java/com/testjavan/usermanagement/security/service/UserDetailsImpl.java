package com.testjavan.usermanagement.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testjavan.usermanagement.models.User;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

    private Long idUser;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String role;

    public UserDetailsImpl(Long idUser, String username, String email, String password, String role) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserDetailsImpl build(User employee) {
        return new UserDetailsImpl(employee.getIdUser(), employee.getUsername(), employee.getEmail(),
                employee.getPassword(), employee.getRole().getRoleName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.hasText(role)) {
            String[] splits = role.replaceAll(" ", "").split(",");
            for (String string : splits) {
                authorities.add(new SimpleGrantedAuthority(string));
            }
        }
        return authorities;
    }

    public Long getIdUser() {
		return idUser;
	}
    // @Override
    // public String getPassword() {
    // // TODO Auto-generated method stub
    // return null;
    // }

    // @Override
    // public String getUsername() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    @Override
    public String getPassword() {
      return password;
    }
  
    @Override
    public String getUsername() {
      return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}