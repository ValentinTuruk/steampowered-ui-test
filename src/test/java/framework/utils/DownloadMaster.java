package framework.utils;

import framework.BrowserFactory;
import framework.Setup;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static framework.utils.PropertiesReader.getConfigProperty;

public final class DownloadMaster {
    private static Duration shortTime = Duration.ofSeconds(Long.valueOf(getConfigProperty("download.waiter")));
    
    public static WebDriverWait getShortDownloadWaiter() {
        return new WebDriverWait(Setup.driver, shortTime);
    }
    
    public static void verifyFileDownloaded(String fileName, String extension) {
        File folder = new File(BrowserFactory.downloadPath);
        String regex = String.format("%s\\.%s$", fileName, extension);
        try {
            getShortDownloadWaiter().until(webDriver -> {
                File[] files = folder.listFiles((dir, name) -> name.matches(regex));
                return files != null && files.length != 0;
            });
        } catch (Exception e) {
            SoftAsserts.softAssert.fail(String.format("FILE IS NOT DOWNLOADED: %s.%s", fileName, extension));
        }
    }
    
    public static void cleanDownloadedFiles() {
        File folder = new File(BrowserFactory.downloadPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(".gitkeep")) {
                    file.delete();
                }
            }
        }
    }
}
