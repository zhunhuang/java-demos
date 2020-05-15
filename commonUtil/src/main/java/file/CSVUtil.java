package file;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 生成csv文件工具类
 * @author lijiaheng
 * @createTime 2019年11月12日 22:19:00
 */
public class CSVUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtil.class);

    private static final Marker MARKER = MarkerFactory.getMarker("CSVUtil");

    private static final String DEFAULT_PLACE_HOLDER = "-";

    private CSVUtil() {}

    /**
     * @param path 生成文件路径和文件名
     * @param titles 表头名称
     * @param contentList 写入内容列表
     * @param <T>
     * @return
     */
    public static <T> File exportCsvFile(String path, String[] titles, List<T> contentList) {
        if (StringUtils.isBlank(path) || CollectionUtils.isEmpty(contentList)) {
            return null;
        }

        File file = new File(path);
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            // 写表头
            for (int i = 0; i < titles.length; i++) {
                bw.write(titles[i]);
                bw.write(",");
            }
            bw.newLine();
            // 写内容
            for (Object obj : contentList) {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.get(obj) == null) {
                        bw.write(DEFAULT_PLACE_HOLDER);
                    } else if (f.isSynthetic()) {
                        continue;
                    } else {
                        bw.write(f.get(obj).toString());
                    }
                    bw.write(",");
                }
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
            LOGGER.error("exportCsvFile() error. exception is", e);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }

                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                LOGGER.error("stream close error. exception is", e);
            }
        }

        return file;
    }

    public static <T> void exportCsvFile(String filePath, String fileName, String[] titles,List<T> dataList) {
        File dirFile = new File(filePath);
        if (! dirFile.exists()) {
            if (! dirFile.mkdirs()) {
                LOGGER.error(MARKER, "创建导出文件夹失败. fileName = {}", fileName);
                return;
            }
        }

        exportCsvFile(fileName, titles, dataList);
    }
}

