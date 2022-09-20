package chillrain.launcher.util;

import java.io.*;

/**
 * @author ChillRain 2022 09 18
 */
public class FileIOUtil {

    public static void copyDir(File sourceDir, File targetDir) throws IOException {
//        确定源文件夹是否存在 不存在提示不存在并直接返回
        if (!sourceDir.exists() || !sourceDir.isDirectory()){
            System.out.println("Directory is not exist!");
            return;
        }
//        确认目标文件夹是否存在 不存在则创建 存在则提示文件夹已存在
        if (!targetDir.exists()){
            targetDir.mkdirs();
        }else {
            System.out.println("target is have existed!");
            return;
        }
        File[] files = sourceDir.listFiles();
        for (File file : files) {
            File sFile = null;
            File gFile = null;
            if (file.isDirectory()){
                sFile = new File(sourceDir.getAbsoluteFile(), file.getName());
                gFile = new File(targetDir.getAbsoluteFile(), file.getName());
                FileIOUtil.copyDir(sFile, gFile);
            }else {
                sFile = file;
                gFile = new File(targetDir, file.getName());
                FileIOUtil.copyFile(sFile, gFile);
            }
        }

    }
    private static void copyFile(File sourceFile, File targetFile) throws IOException {
        try(BufferedInputStream sourceIO = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream targetIO = new BufferedOutputStream(new FileOutputStream(targetFile))){
            targetIO.write(sourceIO.read());
            targetIO.flush();
        }
    }
}
