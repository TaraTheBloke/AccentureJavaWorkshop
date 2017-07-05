package finish.exercises.collections.in.depth;

import static exercises.collections.in.depth.CourseType.ADVANCED;
import static exercises.collections.in.depth.CourseType.BEGINNER;
import static exercises.collections.in.depth.CourseType.INTERMEDIATE;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import exercises.collections.in.depth.Course;
import exercises.collections.in.depth.CourseType;
import exercises.collections.in.depth.Pair;
import exercises.collections.in.depth.Trainer;

public class Program {

	public static void main(String[] args) {
	    List<Course> courses = courses();
		titlesOfCourses(courses);
	    titlesOfCoursesWithoutATrainer(courses);
	    namesAndRatesOfTrainers(courses);
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV1(courses);
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV2(courses);
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV3(courses);
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV4(courses);
	    everyPairOfTrainersThatCouldDeliverJava(courses);
	    possibleCostsOfJeeWebDevelopment(courses);
	    coursesIdsAndTitlesGroupedByType(courses);
	}

	private static void titlesOfCourses(List<Course> courses) {
		System.out.println("The titles of all the courses are:");
		courses.stream()
		       .map(Course::getTitle)
			   .forEach(Program::printTabbed);
	}

	private static void titlesOfCoursesWithoutATrainer(List<Course> courses) {
		System.out.println("The titles of all the courses without a trainer are:");
		courses.stream()
		       .filter(c -> c.getInstructors().isEmpty())
			   .map(Course::getTitle)
			   .forEach(Program::printTabbed);
	}

	private static void namesAndRatesOfTrainers(List<Course> courses) {
		System.out.println("The names and rates of all trainers are:");
		courses.stream()
		       .flatMap(c -> c.getInstructors().stream())
			   .distinct()
			   .map(t -> new Pair<>(t.getName(),t.getRate()))
			   .forEach(Program::printTabbed);
	}

	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV1(List<Course> courses) {
		System.out.println("Attempt 1: Total course durations are:");
		totalDurationOfBeginnerCourses(courses);
		totalDurationOfNonBeginnerCourses(courses);
	}

	private static void totalDurationOfBeginnerCourses(List<Course> courses) {
		int result = courses.stream()
		                    .filter(c -> c.getType() == BEGINNER)
					 		.mapToInt(Course::getDuration)
					 		.sum();
		System.out.println("\tTotal for beginners is: " + result);
	}

	private static void totalDurationOfNonBeginnerCourses(List<Course> courses) {
		int result = courses.stream()
		                    .filter(c -> c.getType() != BEGINNER)
					 		.mapToInt(Course::getDuration)
					 		.sum();
		System.out.println("\tTotal for non-beginners is: " + result);
	}

	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV2(List<Course> courses) {
		Pair<Integer,Integer> result = courses.stream()
		                                      .reduce(new Pair<>(0,0),Program::totalDurations,(a,b) -> null);
		System.out.println("Attempt 2: Total course durations are:");
		System.out.println("\tTotal for beginners is: " + result.getFirst());
		System.out.println("\tTotal for non-beginners is: " + result.getSecond());
	}

	private static Pair<Integer,Integer> totalDurations(Pair<Integer,Integer> total, Course course) {
		int totalBeginners = total.getFirst();
		int totalNonBeginners = total.getSecond();
		int duration = course.getDuration();
		if (course.getType() == BEGINNER) {
			return new Pair<>(totalBeginners + duration, totalNonBeginners);
		}
        return new Pair<>(totalBeginners, totalNonBeginners + duration);
	}

	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV3(List<Course> courses) {
		int durationForBeginner = courses.stream()
										 .filter(c -> c.getType() == BEGINNER)
										 .collect(summingInt(Course::getDuration));
		int durationForNonBeginner = courses.stream()
											.filter(c -> c.getType() != BEGINNER)
				 							.collect(summingInt(Course::getDuration));

		System.out.println("Attempt 3: Total course durations are:");
		System.out.println("\tTotal for beginners is: " + durationForBeginner);
		System.out.println("\tTotal for non-beginners is: " + durationForNonBeginner);
	}

	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV4(List<Course> courses) {
		Map<Boolean, Integer> results = courses.stream()
		                                       .collect(partitioningBy(c -> c.getType() == BEGINNER, summingInt(Course::getDuration)));
		System.out.println("Attempt 4: Total course durations are:");
		System.out.println("\tTotal for beginners is: " + results.get(true));
		System.out.println("\tTotal for non-beginners is: " + results.get(false));
	}

	private static void everyPairOfTrainersThatCouldDeliverJava(List<Course> courses) {
		System.out.println("Every pair of trainers that could deliver Java:");
		javaTrainers(courses).flatMap(t1 -> javaTrainers(courses).filter(t2 -> t1 != t2)
			   							   					    .map(t2 -> new Pair<>(t1,t2)))
			   				 .map(p -> new Pair<>(p.getFirst().getName(),p.getSecond().getName()))
			   				 .distinct()
			   				 .forEach(Program::printTabbed);
	}

	private static Stream<Trainer> javaTrainers(List<Course> courses) {
		return courses.stream()
					  .flatMap(c -> c.getInstructors().stream())
			   	   	  .distinct()
			   	   	  .filter(t -> t.getSkills().contains("Java"));
	}

	private static void possibleCostsOfJeeWebDevelopment(List<Course> buildData) {
		System.out.println("Possible costs of 'JEE Web Development'");
		Optional<Course> selected = buildData.stream()
				 				   			 .filter(c -> c.getTitle().equals("JEE Web Development"))
				 				   			 .findFirst();

		String msg = "\t%s at a cost of %.2f\n";
		int duration = selected.map(c -> c.getDuration())
							   .orElse(0);
		selected.ifPresent(c -> c.getInstructors()
								 .stream()
								 .forEach(t -> System.out.printf(msg, t.getName(), t.getRate() * duration)));
	}

	private static void coursesIdsAndTitlesGroupedByType(List<Course> courses) {
		System.out.println("Course id's and titles grouped by type are:");
		String msg = "\tCourses of type %s are %s\n";
		Map<CourseType, List<String>> coursesByType = courses.stream()
		                                                     .collect(groupingBy(Course::getType, mapping(Course::getTitle, toList())));
		coursesByType.forEach((key,values) ->  System.out.printf(msg, key, values));
	}

	private static <T> void printTabbed(T input) {
		System.out.print("\t");
		System.out.println(input.toString());
	}

	private static List<Course> courses() {
		List<Course> courses = new ArrayList<>();
		Trainer jane = new Trainer("Jane Smith", 750.0, asList("SQL", "Java", "JEE"));
		Trainer pete = new Trainer("Pete Hughes", 1000.0, asList("Java", "JEE", "C#", "Scala"));
		Trainer mary = new Trainer("Mary Donaghy", 1250.0, asList("Java", "JEE", "C#", "C++"));
		courses.add(new Course("AB12", "Intro to Scala", BEGINNER, 4, asList(pete)));
		courses.add(new Course("CD34", "JEE Web Development", INTERMEDIATE, 3, asList(pete, mary, jane)));
		courses.add(new Course("EF56", "Meta-Programming in Ruby", ADVANCED, 2, asList()));
		courses.add(new Course("GH78", "OO Design with UML", BEGINNER, 3, asList(jane, pete, mary)));
		courses.add(new Course("IJ90", "Database Access with JPA", INTERMEDIATE, 3, asList(jane)));
		courses.add(new Course("KL12", "Design Patterns in C#", ADVANCED, 2, asList(pete, mary)));
		courses.add(new Course("MN34", "Relational Database Design", BEGINNER, 4, asList(jane)));
		courses.add(new Course("OP56", "Writing MySql Stored Procedures", INTERMEDIATE, 1, asList(jane)));
		courses.add(new Course("QR78", "Patterns of Parallel Programming", ADVANCED, 2, asList(pete, mary)));
		courses.add(new Course("ST90", "C++ Programming for Beginners", BEGINNER, 5, asList(mary)));
		courses.add(new Course("UV12", "UNIX Threading with PThreads", INTERMEDIATE, 2, asList()));
		courses.add(new Course("WX34", "Writing Linux Device Drivers", ADVANCED, 3, asList(mary)));

		return courses;
	}
}
