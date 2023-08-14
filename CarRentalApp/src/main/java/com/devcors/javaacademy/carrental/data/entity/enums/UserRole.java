package com.devcors.javaacademy.carrental.data.entity.enums;

public enum UserRole {

    USER("USER"), ADMIN("ADMIN"), GUEST("GUEST");

    private final String userRoleName;

    UserRole(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

}
