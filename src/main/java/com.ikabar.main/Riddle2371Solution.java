package com.ikabar.main;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;

/**
 * Created by ikabar on 10/04/2016.
 */
public class Riddle2371Solution {

    static {

    }
    static Solver solver = new Solver();

    static IntVar Xa = VariableFactory.bounded("Xa", 0, 9, solver);
    static IntVar Xb = VariableFactory.bounded("Xb", 0, 9, solver);
    static IntVar Xc = VariableFactory.bounded("Xc", 0, 9, solver);
    static IntVar Xd = VariableFactory.bounded("Xd", 0, 9, solver);
    static IntVar Xe = VariableFactory.bounded("Xe", 0, 9, solver);
    static IntVar Xf = VariableFactory.bounded("Xf", 0, 9, solver);
    static IntVar Xg = VariableFactory.bounded("Xg", 0, 9, solver);
    static IntVar Xh = VariableFactory.bounded("Xh", 0, 9, solver);
    static IntVar Xi = VariableFactory.bounded("Xi", 0, 9, solver);
    static IntVar Xj = VariableFactory.bounded("Xj", 0, 9, solver);
    static IntVar Xk = VariableFactory.bounded("Xk", 0, 9, solver);
    static IntVar Xl = VariableFactory.bounded("Xl", 0, 9, solver);
    static IntVar Xm = VariableFactory.bounded("Xm", 0, 9, solver);
    static IntVar Xn = VariableFactory.bounded("Xn", 0, 9, solver);
    static IntVar Xo = VariableFactory.bounded("Xo", 0, 9, solver);
    static IntVar Xp = VariableFactory.bounded("Xp", 0, 9, solver);
    static IntVar Xq = VariableFactory.bounded("Xq", 0, 9, solver);
    static IntVar Xr = VariableFactory.bounded("Xr", 0, 9, solver);
    static IntVar Xs = VariableFactory.bounded("Xs", 0, 9, solver);
    static IntVar Xt = VariableFactory.bounded("Xt", 0, 9, solver);
    static IntVar Xx = VariableFactory.bounded("Xx", 0, 9, solver);

    static IntVar Xabc = VariableFactory.bounded("Xabc", 100, 999, solver);
    static IntVar Xdef = VariableFactory.bounded("Xdef", 100, 999, solver);
    static IntVar Xghxi = VariableFactory.bounded("Xghxi", 1000, 9999, solver);
    static IntVar Xxxjk = VariableFactory.bounded("Xxxjk", 1000, 9999, solver);
    static IntVar Xxlmn = VariableFactory.bounded("Xlmn", 1000, 9999, solver);
    static IntVar Xopqrst = VariableFactory.bounded("Xopqrst", 100000, 999999, solver);


    public static void main(String[] args){
        printRiddle();

        solver.post(ICF.scalar(new IntVar[]{Xa, Xb, Xc}, new int[] {100, 10, 1}, Xabc));
        solver.post(ICF.scalar(new IntVar[]{Xd, Xe, Xf}, new int[] {100, 10, 1}, Xdef));
        solver.post(ICF.scalar(new IntVar[]{Xg, Xh, Xx, Xi}, new int[] {1000, 100, 10, 1}, Xghxi));
        solver.post(ICF.scalar(new IntVar[]{Xx, Xx, Xj, Xk}, new int[] {1000, 100, 10, 1}, Xxxjk));
        solver.post(ICF.scalar(new IntVar[]{Xx, Xl, Xm, Xn}, new int[]{1000, 100, 10, 1}, Xxlmn));
        solver.post(ICF.scalar(new IntVar[]{Xo, Xp, Xq, Xr, Xs, Xt}, new int[]{100000, 10000,1000, 100, 10, 1}, Xopqrst));

        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xa}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xb}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xc}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xd}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xe}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xf}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xg}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xh}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xi}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xj}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xk}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xl}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xm}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xn}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xo}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xp}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xq}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xr}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xs}));
        solver.post(ICF.alldifferent(new IntVar[]{Xx, Xt}));

        solver.post(ICF.times(Xc, Xdef, Xghxi));
        solver.post(ICF.times(Xb, Xdef, Xxxjk));
        solver.post(ICF.times(Xa, Xdef, Xxlmn));

        solver.post(ICF.times(Xabc, Xdef, Xopqrst));
        solver.findSolution();

        printSolution();
    }



    private static void printRiddle(){
        System.out.println("Find the numbers that makes this calculation assuming X appears only in the specified locations\n");
        System.out.println("   ---");
        System.out.println("     *");
        System.out.println("   ---");
        System.out.println("=============");
        System.out.println("  --X-");
        System.out.println(" XX-- ");
        System.out.println("X---  ");
        System.out.println("=============");
        System.out.println("------");


    }

    private static void printSolution(){
        System.out.println("   " + Xabc.getValue());
        System.out.println("   " + Xdef.getValue());
        System.out.println("=============");
        System.out.println("  " + Xghxi.getValue());
        System.out.println(" " + Xxxjk.getValue());
        System.out.println("" + Xxlmn.getValue());
        System.out.println("=============");
        System.out.println(Xopqrst.getValue());
    }
}
