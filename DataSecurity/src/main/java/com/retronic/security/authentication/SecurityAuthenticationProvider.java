package com.retronic.security.authentication;

import com.retronic.business.services.core.IUserService;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.utils.PasswordEncoder;
import com.retronic.security.entities.SecurityUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("securityAuthenticationProvider")
public class SecurityAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private IUserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        String name = usernamePasswordAuthenticationToken.getName();
        String pass = (String) usernamePasswordAuthenticationToken.getCredentials();
        User user = userService.getByLogin(name);

        if (!user.getPassword().equals(PasswordEncoder.encodePassword(pass, name))) {
            setTrialCounts(user, false);
            throw new BadCredentialsException("The given password is wrong!");
        }

        setTrialCounts(user, true);
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        User user = userService.getByLogin(usernamePasswordAuthenticationToken.getName());

        if (user == null) {
            throw new UsernameNotFoundException("Username does not exist.");
        }

        if (user.getLoginTrials() > 5) {
            throw new UsernameNotFoundException("User is locked");
        }

        return new SecurityUserDetails(user);
    }

    private void setTrialCounts(User user, boolean reset) {
        if (reset) {
            user.resetTrialCount();
        } else {
            user.pushTrialCount();
        }

        userService.update(user);
    }
}
