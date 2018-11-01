import weka.core.Instances;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Instances data = Utils.GetData();
            int seed[] = {100, 50, 10};
            int iters = 5;
            int clusters[] = {-1, 3, 5, 10};
            double cutoff[] = {0.05, 0.04};
            double acuity[] = {1, 2};
            EMTester emTester = new EMTester();
            CobwebTester cobwebTester = new CobwebTester();

            //search grid for EM
            for (int s : seed) {
                for (int cl : clusters) {
                    String info1 = String.format("EM seed-%s clus-%s iters-%s", s, cl, iters);
                    System.out.println(info1);
                    emTester.Test(data, s, iters, cl);
                }
            }

            //search grid for Cobweb
            for (double co : cutoff) {
                for (double ac : acuity) {
                    String info1 = String.format("Cobweb seed-%s cutoff-%s acuity-%s", 42, co, ac);
                    System.out.println(info1);
                    cobwebTester.Test(data, 42, co, ac);
                }
            }


            // naive bayes
            System.out.println("Bayes");
            NaiveBayesianTester naiveBayesianTester = new NaiveBayesianTester();
            naiveBayesianTester.Test(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

