package p1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by alshevchuk on 30.01.2016.
 */
public class Saver {

    public static void save(String path, String fName, String content) {
        File f = new File(path, fName);
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            if (f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f.getAbsolutePath());
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
