package pl.slowikowski.demo.security;

import java.util.Set;

public enum Roles {
    ROLE_USER(Set.of("ROLE_USER")),
    ROLE_MODERATOR(Set.of("ROLE_USER", "ROLE_MODERATOR")),
    ROLE_ADMIN(Set.of("ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN"));

    private final Set<String> roles;

    Roles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public static boolean contains(String name) {

        for (Roles role : Roles.values()) {
            if (role.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
