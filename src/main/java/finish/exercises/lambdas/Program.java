package finish.exercises.lambdas;

import static finish.exercises.lambdas.FileUtils.doWithFileWriter;

import java.io.File;

public class Program {
    public static void main(String[] args) {
        File validPath = new File("input/lambdasExercise.txt");
        File invalidPath = new File("~/nonsense/foo.txt");

        doWithFileWriter(validPath, () -> "wibble", (e) -> System.err.println(e.getMessage()));
        doWithFileWriter(validPath, Program::helper, (e) -> System.err.println(e.getMessage()));
        doWithFileWriter(invalidPath, () -> "wobble", (e) -> System.err.println(e.getMessage()));
        doWithFileWriter(invalidPath, () -> "wobble", Program::handler);
        doWithFileWriter(invalidPath, Program::helper, Program::handler);
        System.out.println("All done...");
    }

    private static String helper() {
        return "supercalafragilisticexpyalidotious";
    }

    private static void handler(Exception ex) {
        System.err.println("Cant write to file because: " + ex.getMessage());
    }
}
