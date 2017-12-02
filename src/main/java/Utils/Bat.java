package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Bat {
    private static String dir = new File("").getAbsolutePath() + "/bats/";

    public void createBat(String name, String game) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        java.io.File template = new java.io.File(classLoader.getResource("template.bat").getFile());
        String content = new String(Files.readAllBytes(Paths.get(template.getAbsolutePath())));
        content = content.replace("{REPLACE}", game);


//        Arrays.stream(new File(dir).listFiles()).forEach(File::delete);
        File file = new File(dir + name + ".bat");
        Files.write(file.toPath(), content.getBytes());
    }


    public static void clear() {
        Arrays.stream(new File(dir).listFiles()).forEach(File::delete);
    }
}
