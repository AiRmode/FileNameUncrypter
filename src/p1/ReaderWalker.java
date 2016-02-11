package p1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alshevchuk on 30.01.2016.
 */
public class ReaderWalker {
    private static String virVersion = "CL 1.2.0.0";
    private static String fileExtention = ".cbf";
    private static String emailPrefix = "email-iizomer@aol.com";
    private static int maxFileSize = 99999999;//by default - up to 100mb
    private String VER_REGEXP = "(?<=\\{" + virVersion + "\\}\\{).*?(?=\\})";//(?<=\{CL 1.2.0.0\}\{).*?(?=\})
    private String basePath;
    private Map<String, String> files = new HashMap<String, String>();
    private Map<String, String> largeFiles = new HashMap<String, String>();
    private static Pattern pattern;

    public ReaderWalker(String basePath) {
        this.basePath = basePath;
        pattern = Pattern.compile(VER_REGEXP);
    }

    public void walk() {
        walk(basePath);
    }

    public void walk(String baseOSPath) {
        File f = new File(baseOSPath);
        if (f.exists() && f.isDirectory()) {
            File[] fList = f.listFiles();
            if (fList == null)
                return;
            for (File file : fList) {
                if (file.isDirectory()) {
                    String absolutePath = file.getAbsolutePath();
                    walk(absolutePath);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(fileExtention) && fileName.startsWith(emailPrefix)) {
                        if (file.length() > maxFileSize)
                            largeFiles.put(file.getAbsolutePath(), String.valueOf(file.length()) + " ? " + String.valueOf(maxFileSize)); //prevent out of memory
                        else {
                            files.put(file.getAbsolutePath(), getRealFileName(file));
                        }
                    }
                }
            }
        }
    }

    public String getRealFileName(File file) {
        String realName;
        try {
            Path path = file.toPath();
            byte[] data = Files.readAllBytes(path);
            String stringFile = new String(data);
            Matcher matcher = pattern.matcher(stringFile);
            if (matcher.find()) {
                realName = matcher.group();
                return realName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "UNKNOWN";
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public Map<String, String> getLargeFiles() {
        return largeFiles;
    }

    public static String getVirVersion() {
        return virVersion;
    }

    public static void setVirVersion(String virVersion) {
        ReaderWalker.virVersion = virVersion;
    }

    public static String getFileExtention() {
        return fileExtention;
    }

    public static void setFileExtention(String fileExtention) {
        ReaderWalker.fileExtention = fileExtention;
    }

    public static String getEmailPrefix() {
        return emailPrefix;
    }

    public static void setEmailPrefix(String emailPrefix) {
        ReaderWalker.emailPrefix = emailPrefix;
    }

    public static int getMaxFileSize() {
        return maxFileSize;
    }

    public static void setMaxFileSize(int maxFileSize) {
        ReaderWalker.maxFileSize = maxFileSize;
    }
}
