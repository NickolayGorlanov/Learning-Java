import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizer implements Runnable {
    private final File[] sourceFiles; // Используем массив файлов
    private final File destinationFolder;
    private final int targetWidth;
    private final int targetHeight;

    public ImageResizer(File[] sourceFiles, File destinationFolder, int targetWidth, int targetHeight) {
        this.sourceFiles = sourceFiles;
        this.destinationFolder = destinationFolder;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    @Override
    public void run() {
        for (File sourceFile : sourceFiles) {
            try {
                BufferedImage image = ImageIO.read(sourceFile);
                if (image != null) {
                    BufferedImage resizedImage = Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, targetWidth, targetHeight);
                    String fileName = sourceFile.getName();
                    File newFile = new File(destinationFolder, fileName);
                    ImageIO.write(resizedImage, "jpg", newFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
