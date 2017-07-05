package exercises.collections.in.depth;

import java.util.List;

public class Trainer {

    private final String name;
    private final double rate;
    private final List<String> skills;

    public Trainer(String name, double rate, List<String> skills) {
        this.name = name;
        this.rate = rate;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name;
    }
}
