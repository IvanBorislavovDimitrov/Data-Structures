import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Arrays.stream(input.readLine()
                .split("\\s+"))
                .sorted(String::compareTo)
                .forEach(x -> System.out.printf("%s ", x));
        System.out.println();
    }
}
