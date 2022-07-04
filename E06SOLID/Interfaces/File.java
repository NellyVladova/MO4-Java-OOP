package E06SOLID.Interfaces;

public interface File {
    int getSize();

    boolean write(String text);

    void append(String text);
}
