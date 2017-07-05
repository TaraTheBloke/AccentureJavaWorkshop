package finish.emphasizer;

public class UpperCaseEmphasizer implements WordEmphasizer {

    @Override
    public String emphasizeWord(String word) {
        return word.toUpperCase();
    }
}
