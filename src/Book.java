public class Book {
    int id;
    String title;
    boolean isAvailable = true; // available/unavailable

    public Book(int id, String title) throws Exception {
        this.validateTitle(title);

        this.id = id;
        this.title = title;
    }

    private void validateTitle(String title) throws Exception {
        if (title.length() < 2 || !title.matches("^[a-zA-Z0-9\\s]+$")) {
            throw new Exception("The title must be at least 2 characters long.");
        }
    }

    public void markUnavailable() throws Exception {
        if (!this.isAvailable) {
            throw new Exception("The book is unavailable.");
        }
        this.isAvailable = false;
    }

    public void markAvailable() throws Exception {
        if (this.isAvailable) {
            throw new Exception("The book is already available.");
        }
        this.isAvailable = true;
    }

}
