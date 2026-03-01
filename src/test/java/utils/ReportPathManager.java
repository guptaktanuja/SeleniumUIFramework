package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportPathManager {

    private static String reportFolderPath;

    public static String getReportFolderPath() {

        if (reportFolderPath == null) {

            String timestamp =
                    new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            reportFolderPath =
                    System.getProperty("user.dir")
                            + File.separator
                            + "reports"
                            + File.separator
                            + timestamp;

            createDirectory(reportFolderPath);
            createDirectory(reportFolderPath + File.separator + "screenshots");
        }

        return reportFolderPath;
    }

    private static void createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String getScreenshotPath() {
        return getReportFolderPath() + File.separator + "screenshots";
    }
}
