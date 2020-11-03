package com.heu.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: qt
 * @Date: 2020/10/31 12:32
 * @Description:
 */
public class MyPassWordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
