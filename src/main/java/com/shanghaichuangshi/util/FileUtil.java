package com.shanghaichuangshi.util;

import com.shanghaichuangshi.constant.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static String webRootPath;

    static {
        createPath(getWebRootPath() + "/" + Key.ASSETS + "/" + Key.UPLOAD);
    }

    public static String getWebRootPath() {
        if (webRootPath == null)
            try {
                String path = FileUtil.class.getResource("/").toURI().getPath();
                webRootPath = new File(path).getParentFile().getParentFile().getCanonicalPath();
            } catch (Exception e) {
                throw new RuntimeException("Exception: " + e.toString());
            }
        return webRootPath;
    }

    public static boolean createPath(String path) {
        File file = new File(path);

        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();

            return true;
        }

        return false;
    }

    public static void resizeImage(File image, String suffix, String path, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image);

            int originalWidth = bufferedImage.getWidth();
            int originalHeight = bufferedImage.getHeight();

            int newWidth = 0;
            int newHeight = 0;


            if (originalWidth > width && width > 0) {
//                if (originalWidth > originalHeight) {
                    newWidth = width;

                    double scale = (double) originalWidth / (double) newWidth;
                    newHeight = (int) (originalHeight / scale);
//                } else {
//                    newHeight = width;
//
//                    double scale = (double) originalHeight / (double) newHeight;
//                    newWidth = (int) (originalWidth / scale);
//                }
            } else {
                newWidth = originalWidth;
                newHeight = originalHeight;
            }

            if (suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
                BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = to.createGraphics();
                to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
                g2d.dispose();

                g2d = to.createGraphics();
                Image from = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
                g2d.drawImage(from, 0, 0, null);
                g2d.dispose();

                ImageIO.write(to, suffix, new File(path));
            } else {
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, bufferedImage.getType());
                Graphics g = newImage.getGraphics();
                g.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
                g.dispose();
                ImageIO.write(newImage, suffix, new File(path));
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.toString());
        }

    }

}
