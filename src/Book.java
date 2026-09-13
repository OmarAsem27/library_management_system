public class Book {
    int id;
    String title;
    boolean isAvailable = true; // available/unavailable

    public Book(int id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }


}
