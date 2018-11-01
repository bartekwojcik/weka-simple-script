import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Cobweb;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

public class CobwebTester {

    public void Test(Instances data, int seed, double cutoff,double acuity )
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
            Cobweb cobweb = new Cobweb();
            cobweb.setSeed(seed);
            cobweb.buildClusterer(dataClusterer);
            cobweb.setCutoff(cutoff);
            cobweb.setAcuity(acuity);

            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(cobweb);
            eval.evaluateClusterer(filteredData);
            String results = eval.clusterResultsToString();
            String fileName = String.format("results\\Cobweb seed-%s.txt", seed);
            Utils.SaveToFile(fileName, results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
