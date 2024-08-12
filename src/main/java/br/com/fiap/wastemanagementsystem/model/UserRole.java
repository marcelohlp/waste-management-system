package br.com.fiap.wastemanagementsystem.model;

public enum UserRole {

    ADMIN("admin"),
    COLLECTOR("collector"),
    SYSTEM("system"),
    USER("user");

    private String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

}
