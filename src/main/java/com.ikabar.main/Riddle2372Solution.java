package com.ikabar.main;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ikabar on 21/04/2016.
 */
public class Riddle2372Solution {

    //Riddle description
    //b s h n
    //-------
    //l b u y
    //y t h v
    //-------
    //m y n s

    static Solver solver = new Solver();

    static IntVar b = VariableFactory.bounded("b", 0, 9, solver);
    static IntVar s = VariableFactory.bounded("s", 0, 9, solver);
    static IntVar h = VariableFactory.bounded("h", 0, 9, solver);
    static IntVar n = VariableFactory.bounded("n", 0, 9, solver);
    static IntVar l = VariableFactory.bounded("l", 0, 9, solver);
    static IntVar u = VariableFactory.bounded("u", 0, 9, solver);
    static IntVar y = VariableFactory.bounded("y", 0, 9, solver);
    static IntVar t = VariableFactory.bounded("t", 0, 9, solver);
    static IntVar m = VariableFactory.bounded("m", 0, 9, solver);

    static IntVar Xbshn = VariableFactory.bounded("Xbshn", 1000, 9999, solver);
    static IntVar Xlbuy = VariableFactory.bounded("Xlbuy", 1000, 9999, solver);
    static IntVar Xythv = VariableFactory.bounded("Xythv", 1000, 9999, solver);
    static IntVar Xmyns = VariableFactory.bounded("Xmyns", 1000, 9999, solver);


    public static void main(String[] args) {

        printRiddle();

        solver.post(ICF.scalar(new IntVar[]{b, s, h, n}, new int[]{1000, 100, 10, 1}, Xbshn));
        solver.post(ICF.scalar(new IntVar[]{l, b, u, y}, new int[]{1000, 100, 10, 1}, Xlbuy));
        solver.post(ICF.scalar(new IntVar[]{y, t, h, u}, new int[]{1000, 100, 10, 1}, Xythv));
        solver.post(ICF.scalar(new IntVar[]{m, y, n, s}, new int[]{1000, 100, 10, 1}, Xmyns));

        solver.post(ICF.sum(new IntVar[]{Xlbuy, Xythv}, Xbshn));
        solver.post(ICF.sum(new IntVar[]{Xythv, Xmyns}, Xlbuy));

        solver.post(ICF.alldifferent(new IntVar[]{b, s, h, n, l, u, y, t, m}));
        if (solver.findSolution()) {
            printSolution();
        }else{
            System.out.println("No solution found");
        }
    }

    public static void printRiddle(){
        System.out.println("b s h n");
        System.out.println("-------");
        System.out.println("l b u y");
        System.out.println("y t h v");
        System.out.println("-------");
        System.out.println("m y n s");
    }

    public static void printSolution(){
        System.out.println(Xbshn.getValue());
        System.out.println("-------");
        System.out.println(Xlbuy.getValue());
        System.out.println(Xythv.getValue());
        System.out.println("-------");
        System.out.println(Xmyns.getValue());
        List<Integer> foundNumbers = Arrays.asList(b, s, h, n, l, u, y, t, m).stream().map(num -> num.getValue()).collect(Collectors.toList());
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).stream().filter(num -> !foundNumbers.contains(num.intValue())).collect(Collectors.toList());
        System.out.println("Result = " + result);
    }
}