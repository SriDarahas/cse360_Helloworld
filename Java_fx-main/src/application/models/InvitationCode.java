package application.models;

import java.util.Set;

public class InvitationCode {
    private String code;
    private Set<Role> roles;
    private boolean isUsed;

    public InvitationCode(String code, Set<Role> roles) {
        this.code = code;
        this.roles = roles;
        this.isUsed = false;
    }

    // Getters and Setters
}
