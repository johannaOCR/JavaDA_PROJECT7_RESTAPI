package com.nnk.springboot.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class PasswordSecurity {
    private static final Logger logger = LogManager.getLogger("PasswordSecurity");

    private static final String PASSWORD_PATTERN
            = "^(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=])(?=.{8,}).*$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()) {
            logger.info("Password is valid");
            return true;
        }
        logger.info("Password is not valid");
        return false;
    }
}
