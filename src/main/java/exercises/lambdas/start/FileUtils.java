package exercises.lambdas.start;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.function.Supplier;

public class FileUtils {
    public static <T> void doWithFileWriter(File path, Supplier<T> job) {
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(path, true))) {
            fw.write(job.get().toString());
            fw.newLine();
        } catch (Exception ex) {
            System.err.println("Cant write to file because: " + ex.getMessage());
        }
    }
}
