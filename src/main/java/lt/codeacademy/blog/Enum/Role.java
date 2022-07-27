package lt.codeacademy.blog.Enum;

public enum Role {
    ADMIN("admin"),

    USER("user"),

    GUEST("guest");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
