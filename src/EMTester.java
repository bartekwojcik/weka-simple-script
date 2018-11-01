import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

public class EMTester {

    public void Test(Instances data, int seed, int maxIters, int numOfClusters)
    {
        try {
            data.setClassIndex(data.numAttributes()-1);
            //nominal filter
            NumericToNominal filter = new NumericToNominal();
            filter.setInputFormat(data);
            Instances filteredData = Filter.useFilter(data, filter);

            //remove class filter
            Remove removeFilter = new Remove();
            removeFilter.setAttributeIndices("" + (data.classIndex()+1));
            removeFilter.setInputFormat(filteredData);
            Instances dataClusterer = Filter.useFilter(filteredData,removeFilter);

            //em cluster options
            EM emClusterer = new EM();
            emClusterer.setSeed(seed);
            emClusterer.setNumClusters(numOfClusters);
            emClusterer.setMaxIterations(maxIters);
            emClusterer.buildClusterer(dataClusterer);

            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(emClusterer);
            eval.evaluateClusterer(filteredData);
            String results = eval.clusterResultsToString();
            String fileName = String.format("results\\EM seed-%s clus-%s iters-%s.txt", seed,numOfClusters,maxIters);
            Utils.SaveToFile(fileName, results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
