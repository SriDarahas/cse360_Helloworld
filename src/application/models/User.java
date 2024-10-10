package application.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String email;
    private String username;
    private byte[] passwordHash;
    private byte[] salt;
    private boolean isOneTimePassword;
    private LocalDateTime otpExpiry;
    private Name name;
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // ... (Other getters and setters)

    public void addRole(Role role) {
        roles.add(role);
    }
    public void removeRole(Role role) {
        roles.remove(role);
    }
    public Set<Role> getRoles() {
        return roles;
    }

    // ... (Other methods as needed)
}
