import utils.Normaliser;

public class Main {
    public static void main(String[] args) {
        String jt = "Accountant engineer";
        Normaliser n = new Normaliser();

        String normalisedTitle = n.normalise(jt);
        System.out.println(normalisedTitle);

        jt = "C# engineer";
        normalisedTitle = n.normalise(jt);
        System.out.println(normalisedTitle);

        jt = "Chief Accountant";
        normalisedTitle = n.normalise(jt);
        System.out.println(normalisedTitle);
    }
}