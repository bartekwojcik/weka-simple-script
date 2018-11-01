import weka.core.Instances;

import java.io.*;

public class Utils {

    public static void SaveToFile(String fileName,String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static Instances GetData() throws IOException {
        String path_to_file = "C:\\Users\\kicjo\\Desktop\\STUDIES\\_DATA MINING\\_PART2\\ASSIGNMENT2\\data\\20_balanced_pixels_file.arff";
        FileReader reader = new FileReader(path_to_file);
        Instances data = new Instances(reader);
        return data;
    }
}
