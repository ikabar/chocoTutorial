package com.ikabar.main;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;
import sun.jvm.hotspot.runtime.VFrame;

/**
 * Created by ikabar on 23/03/2016.
 */
public class Main {

    private static final int n = 2;
    public static void main(String[] args) {

        //19*24=456
        //0=a, 1=b, .. 9=j
        // bj
        //   *
        // ce
        //----
        //cbe
        //   +
        //ce
        //====
        //efg
        Solver solver = new Solver();

        //Variables
        IntVar b = VariableFactory.bounded("b", 0, 9, solver);
        IntVar j = VariableFactory.bounded("j", 0, 9, solver);
        IntVar c = VariableFactory.bounded("c", 0, 9, solver);
        IntVar e = VariableFactory.bounded("e", 0, 9, solver);
        IntVar f = VariableFactory.bounded("f", 0, 9, solver);
        IntVar g = VariableFactory.bounded("g", 0, 9, solver);

        //Complex variables
        IntVar Xbj = VariableFactory.bounded("Xbj", 10, 99, solver);
        IntVar Xcb = VariableFactory.bounded("Xcb", 10, 99, solver);
        IntVar Xcbe = VariableFactory.bounded("Xcbe", 100, 999, solver);
        IntVar Xce = VariableFactory.bounded("Xce", 10, 99, solver);
        IntVar Xce2 = VariableFactory.bounded("Xce2", 0, 999, solver);

        IntVar Xefg = VariableFactory.bounded("Xefg", 100, 999, solver);

        solver.post(ICF.scalar(new IntVar[]{b, j},new int[]{10, 1}, Xbj));
        solver.post(ICF.scalar(new IntVar[]{c, e},new int[]{10, 1}, Xce));
        solver.post(ICF.scalar(new IntVar[]{c, b, e},new int[]{100, 10, 1}, Xcbe));
        solver.post(ICF.scalar(new IntVar[]{e, f, g},new int[]{100, 10, 1}, Xefg));
        solver.post(ICF.scalar(new IntVar[]{Xce},new int[]{10}, Xce2));


        solver.post(ICF.sum(new IntVar[]{Xcbe, Xce2}, Xefg));
        solver.post(ICF.times(j, Xce, Xcbe));
        solver.post(ICF.times(Xbj, Xce, Xefg));



        solver.post(IntConstraintFactory.arithm(b, "!=", 0));
        solver.post(IntConstraintFactory.arithm(e, "!=", 0));
        solver.post(IntConstraintFactory.arithm(c, "!=", 0));
        solver.post(IntConstraintFactory.alldifferent(new IntVar[]{b, j, c, f, g, e,}, "BC"));


//        boolean solution = solver.findSolution();
        long numOfSolutions = solver.findAllSolutions();
        System.out.println("Solution found = " + numOfSolutions);
        System.out.println("Expected 19*24=456");

        IntVar[] all = new IntVar[]{b, j, c, e, f, g, Xbj, Xcb, Xcbe, Xce, Xefg};
        for (IntVar intVar : all) {
            System.out.println(intVar.getName() + " = " + intVar.getValue());
        }

//        System.out.println("Solution = " + solution + " b = " + b.getValue() + "c = " + c.getValue() + "e = " + e.getValue()
//        + " f = " + f.getValue() + " g = " + g.getValue() + " j = " + j.getValue());
        Chatterbox.printStatistics(solver);

//        int n = 4;
//        System.out.println("Magic Square Problem with n = " + n);
//        Solver myPb = new Solver();
//
//
//        IntVar[] vars = new IntVar[n * n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++) {
//                vars[i * n + j] = myPb.makeEnumIntVar("C" + i + "_" + j, 1, n * n);
//            }
//        IntVar sum = myPb.makeEnumIntVar("S", 1, n * n * (n * n + 1) / 2);
//
//        myPb.post(myPb.eq(sum, n * (n*n + 1) / 2));
//        for (int i = 0; i < n * n; i++)
//            for (int j = 0; j < i; j++)
//                myPb.post(myPb.neq(vars[i], vars[j]));
//
//        int[] coeffs = new int[n];
//        for (int i = 0; i < n; i++) {
//            coeffs[i] = 1;
//        }
//
//        for (int i = 0; i < n; i++) {
//            IntVar[] col = new IntVar[n];
//            IntVar[] row = new IntVar[n];
//
//            for (int j = 0; j < n; j++) {
//                col[j] = vars[i * n + j];
//                row[j] = vars[j * n + i];
//            }
//
//            myPb.post(myPb.eq(myPb.scalar(coeffs, row), sum));
//            myPb.post(myPb.eq(myPb.scalar(coeffs, col), sum));
//        }
//        myPb.solve();
    }

    private void constaintTwoVars(IntVar v1, IntVar v2, int v1Index, int v2Index, Solver solver) {
        IntVar digAtIndexV1 = getVarInIndex(v1, v1Index);
        IntVar digAtIndexV2 = getVarInIndex(v2, v2Index);
    }

    private IntVar[] intVarToArray(IntVar var, Solver solver) {
        int range = var.getRange();
        int length = Integer.valueOf(range).toString().length();
        IntVar[] varAsArray = VariableFactory.integerArray("", length, 0, 9, solver);
        for (int i=0;i<length;i++) {

        }

        return varAsArray;
    }

    private IntVar getVarInIndex(IntVar v1, int v1Index) {
        return null;
    }
}
