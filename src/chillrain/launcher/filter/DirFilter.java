package chillrain.launcher.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author ChillRain 2022 09 06
 */
public class DirFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        File file = new File(dir, name);
        return (file.isDirectory());
    }
}
