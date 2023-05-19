package com.nnk.springboot.security;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe implémentant le ContraintValidator
 * Permettant la vérification avant enregistrement qu'un mot de passe est saisie au bon format soit
 * D'une longueur >= à 8
 * Qui comprend au moins un caractère spécial
 * Qui comprend au moins un chiffre
 * Qui comprend au moins une majuscule
 * Qui comprend au moins un caractère classique
 */
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
    private static final String PASSWORD_PATTERN
            = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
