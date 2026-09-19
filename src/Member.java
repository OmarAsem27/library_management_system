public class Member {
    int id;
    String name;
    boolean isActive = true; // active/inactive

    public Member(int id, String name) throws Exception {
        this.validateName(name);

        this.id = id;
        this.name = name;
    }

    private void validateName(String name) throws Exception {
        if (name.length() < 3 || !name.matches("^[a-zA-Z\\s]+$")) {
            throw new Exception("The name must be at least 3 characters long and does not contain any numbers.");
        }
    }

    public void markInactive() throws Exception {
        if (!this.isActive) {
            throw new Exception("This member is already inactive.");
        }
        this.isActive = false;
    }
}
