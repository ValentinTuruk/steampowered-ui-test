package framework.utils;

import framework.Setup;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public final class DownloadMaster {
    private static Duration shortTime = Duration.ofSeconds(Long.valueOf(PropertiesReader.getProperty("config", "short.download.waiter")));
    private static WebDriverWait wait = new WebDriverWait(Setup.driver, shortTime);
    
    public static void verifyFileDownloaded(String fileName, String extension) {
        File folder = new File(Setup.downloadPath);
        String regex = String.format("%s\\.%s$", fileName, extension);
        try {
            wait.until(webDriver -> {
                File[] files = folder.listFiles((dir, name) -> name.matches(regex));
                return files != null && files.length != 0;
            });
        } catch (Exception e) {
            Assert.fail(String.format("FILE IS NOT DOWNLOADED: %s.%s", fileName, extension));
        }
    }
}
