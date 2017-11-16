import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        RedBlackTree<Integer> tree = new RedBlackTree<>();
        String str = new String(Files.readAllBytes(Paths.get("tree.txt")));
        String[] valuesStr = str.split("\\s");
        for (String value: valuesStr) {
            tree.insert(Integer.valueOf(value));
        }
        System.out.print(tree);
    }
}
