package finish.emphasizer;

public class AsteriskWordEmphasizer implements WordEmphasizer {

    @Override
    public String emphasizeWord(String word) {
        return "*" + word + "*";
    }
}
