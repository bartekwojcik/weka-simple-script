import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.clusterers.EM;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class NaiveBayesianTester {

    public void Test(Instances data)
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
            NaiveBayes bayes = new NaiveBayes();
            bayes.buildClassifier(dataClusterer);

            Evaluation eval = new Evaluation(filteredData);
            eval.evaluateModel(bayes,filteredData);
            String results = eval.toSummaryString();
            String fileName = "results\\Naive bayes.txt";
            Utils.SaveToFile(fileName, results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
