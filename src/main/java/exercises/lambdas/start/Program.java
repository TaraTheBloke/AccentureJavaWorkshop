package exercises.lambdas.start;

import static exercises.lambdas.start.FileUtils.doWithFileWriter;

import java.io.File;

public class Program {

	public static void main(String[] args) {
		File validPath = new File("input/lambdasExercise.txt");
		File invalidPath = new File("~/nonsense/foo.txt");

		doWithFileWriter(validPath, () -> "wibble");
		doWithFileWriter(validPath, Program::helper);
		doWithFileWriter(invalidPath, () -> "wobble");

		System.out.println("All done...");
	}
	private static String helper() {
		return "supercalafragilisticexpyalidotious";
	}
}
