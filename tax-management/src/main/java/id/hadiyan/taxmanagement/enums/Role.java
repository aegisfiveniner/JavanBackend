package id.hadiyan.taxmanagement.enums;

public enum Role {
    ADMIN,
    MAKER,
    CHECKER,
    APPROVER
    ;

    public String scope() {
        return "SCOPE_ROLE_" + this.name();
    }
}
