package finish.exercises.lambdas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FileUtils {

    public static <T> void doWithFileWriter(File path, Supplier<T> job, Consumer<Exception> errorHandler) {
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(path, true))) {
            fw.write(job.get().toString());
            fw.newLine();
        } catch (Exception ex) {
            errorHandler.accept(ex);
        }
    }
}
