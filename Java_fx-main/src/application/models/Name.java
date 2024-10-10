package application.models;

public class Name {
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredName;

    // Constructors, Getters, and Setters

    public String getDisplayName() {
        return (preferredName != null && !preferredName.isEmpty()) ? preferredName : firstName;
    }
}
