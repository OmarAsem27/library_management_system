public class Member {
    int id;
    String name;
    boolean isActive = true; // active/inactive

    
    public Member(int id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}
