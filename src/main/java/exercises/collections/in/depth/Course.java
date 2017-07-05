package exercises.collections.in.depth;

import java.util.List;

public class Course {

    private final String id;
    private final String title;
    private final CourseType type;
    private final int duration;
    private final List<Trainer> instructors;

    public Course(String id, String title, CourseType type, int duration, List<Trainer> instructors) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.instructors = instructors;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CourseType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public List<Trainer> getInstructors() {
        return instructors;
    }
}
