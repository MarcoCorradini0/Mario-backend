package org.tuo.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hash(String raw){
        return BCrypt.hashpw(raw, BCrypt.gensalt(12));
    }
    public static boolean verify(String raw, String hash){
        return BCrypt.checkpw(raw, hash);
    }
}
