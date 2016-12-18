package day11;

import java.io.IOException;
import org.junit.Test;

import net.sf.tweety.logics.pl.parser.PlParser;
import net.sf.tweety.logics.pl.sat.Sat4jSolver;
import net.sf.tweety.logics.pl.semantics.PossibleWorld;
import net.sf.tweety.logics.pl.semantics.PossibleWorldIterator;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;
import net.sf.tweety.logics.pl.syntax.PropositionalSignature;

public class TweetyTest {


    @Test
    public void doStuff() throws IOException {
        PlParser parser = new PlParser();
        PropositionalFormula formula = (PropositionalFormula) parser.parseFormula("(b|| !d)&& (!a || (b && !(c||d) && e) || (a&& !c))");

        System.out.println(formula);

        Sat4jSolver solver = new Sat4jSolver();

        System.out.println(solver.isConsistent(formula));

        System.out.println(solver.getWitness(formula));
    }


    @Test
    public void exercise4() throws IOException {
        PropositionalSignature sig = new PropositionalSignature(4);
        PossibleWorldIterator it = new PossibleWorldIterator(sig);

        while(it.hasNext())
            System.out.println(it.next());

        PropositionalFormula formula = (PropositionalFormula) new PlParser().parseFormula("A1 || (A2 && !A3)");

        it = new PossibleWorldIterator(sig);

        System.out.println();
        while(it.hasNext()){
            PossibleWorld pw = it.next();
            if(pw.satisfies(formula))
                System.out.println(pw);
        }
    }


    @Test
    public void myEx() throws IOException {
        PropositionalSignature sig = new PropositionalSignature(4);
        PossibleWorldIterator it = new PossibleWorldIterator(sig);
int i = 3;
        while(it.hasNext())
            System.out.println(it.next());
        PropositionalFormula formula = (PropositionalFormula) new PlParser().parseFormula("A1 || (A2 && !A3)");

        it = new PossibleWorldIterator(sig);

        System.out.println();
        while(it.hasNext()){
            PossibleWorld pw = it.next();
            if(pw.satisfies(formula))
                System.out.println(pw);
        }
    }
}
