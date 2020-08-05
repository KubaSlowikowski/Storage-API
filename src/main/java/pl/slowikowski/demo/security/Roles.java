package pl.slowikowski.demo.security;

public enum Roles {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;

    public static boolean contains(String name) {

        for (Roles role : Roles.values()) {
            if (role.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
