package p1;

import java.io.File;
import java.util.Map;

/**
 * Created by alshevchuk on 30.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-basePath")) { //base path
                    roots = new File(args[i + 1]).listFiles();
                } else if (args[i].equals("-virVersion")) {//virus version, like CL 1.2.0.0
                    ReaderWalker.setVirVersion(args[i + 1]);
                } else if (args[i].equals("-fileExtension")) { //file extension
                    ReaderWalker.setFileExtention(args[i + 1]);
                } else if (args[i].equals("-emailPrefix")) {
                    ReaderWalker.setEmailPrefix(args[i + 1]);
                } else if (args[i].equals("-maxFileSize")) {
                    ReaderWalker.setMaxFileSize(Integer.parseInt(args[i + 1]));
                }
            }
        }

        for (File root : roots) {
            System.out.println("Scanning root: " + root.getAbsolutePath());
            ReaderWalker readerWalker = new ReaderWalker(root.getAbsolutePath());
            readerWalker.walk();

            Map<String, String> files = readerWalker.getFiles();
            Map<String, String> largeFiles = readerWalker.getLargeFiles();
            if (!largeFiles.isEmpty()) {
                long l = System.currentTimeMillis();
                String htmlLikeFile = FinePrinter.createHTMLLikeFile(largeFiles);
                System.out.println("Found " + largeFiles.size() + " injured out of size files");
                System.out.println("Report file: " + String.valueOf(l) + "OutOfSizeFilesReport.html");
                Saver.save("./", l + "OutOfSizeFilesReport.html", htmlLikeFile);
            }

            if (files.isEmpty()) {
                System.out.println("Found " + files.size() + " injured files");
                continue;
            }

            long l = System.currentTimeMillis();
            String htmlLikeFile = FinePrinter.createHTMLLikeFile(files);

            System.out.println("Found " + files.size() + " injured files");
            System.out.println("Report file: " + String.valueOf(l) + "report.html");
            Saver.save("./", l + "report.html", htmlLikeFile);
        }
    }
}
