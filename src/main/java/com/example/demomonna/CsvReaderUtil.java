package com.example.demomonna;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import sun.nio.cs.ext.GBK;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Author： Monna
 * @CreateTime:2023-03-20 10:56:11
 * @Descrption: csv文件读取
 */
public class CsvReaderUtil {
    /**
     * CsvReader 读取
     *
     * @param filePath
     * @return
     */
    public static ArrayList<String> readCsvByCsvReaderOriginal(String filePath) {
        ArrayList<String> strList = null;
        try {
            ArrayList<String[]> arrList = new ArrayList<String[]>();
            strList = new ArrayList<String>();
            CsvReader reader = new CsvReader(filePath, '\\', Charset.forName("UTF-8"));
            while (reader.readRecord()) {
//                System.out.println(Arrays.asList(reader.getValues()));
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            for (int row = 1; row < 10; row++) {
//            for (int row = 0; row < arrList.size(); row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String ele = "'" + arrList.get(row)[0].split(",")[0] + "'";
                strList.add(ele);
//                System.out.println(ele);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    /**
     * @param updateFile
     * @param infoFile
     * @param destPath
     * @return {@link  List< String>}
     * @throws
     * @author Monna
     * @date 2023/3/21 11:04
     * @description: 读取文件并生成所需文件
     */
    public static List<String> readCsvByCsvReaderDataExpander1(String updateFile, String infoFile, String destPath) {

        List<String> strList = new ArrayList<String>();
        try {
            List<String> onLineList = readOriginalInfoOfArray(infoFile);
            Map<String, String> intoIdIdOnFirstMap = new HashMap<>();
            for (int i = 1; i < onLineList.size(); i++) {
                String[] split = onLineList.get(i).split(",");
                intoIdIdOnFirstMap.put(split[1], split[0]);
            }
            ArrayList<String[]> arrList = new ArrayList<>();
            CsvReader reader = new CsvReader(updateFile, '\\', Charset.forName("GBK"));
            while (reader.readRecord()) {
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            for (int row = 1; row < arrList.size(); row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String[] split = arrList.get(row)[0].split(",");
                String intoId = split[0];
                String realCode = split[1];
                String realName = split[2];
                if (Objects.nonNull(intoIdIdOnFirstMap.get(intoId))) {
                    String id = intoIdIdOnFirstMap.get(intoId);
                    //update eagle_jsd_intopieces set utm_term = , channel_type,channel_category = ,into_channel_name =  where into_id =
                    String sql = "update eagle_jsd_intopieces set utm_term = '"
                            + realCode + "' , channel_type = '" + realCode + "',channel_category = '" + realName + "' ,into_channel_name = '" + realName + "' where id = " + id + ";\r\n";
                    System.out.println(sql);
                    strList.add(sql);
                }
            }
            if (Objects.nonNull(destPath)) {
                saveContentCovered(strList, destPath);
            }

            System.out.println("=============");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    /**
     * @param updateFile 需修复数据文件
     * @param infoFile   线上数据，此处主要来获取主键ID
     * @param destPath   生成文件地址
     * @return {@link  List< String>}
     * @throws
     * @author Monna
     * @date 2023/3/21 11:19
     * @description:
     */
    public static List<String> readCsvByCsvReaderDataExpander(String updateFile, String infoFile, String destPath) {

        List<String> strList = new ArrayList<String>();
        try {
            List<String> onLineList = readOriginalInfoOfArray(infoFile);
            Map<String, String> intoIdIdOnFirstMap = new HashMap<>();
            for (int i = 1; i < onLineList.size(); i++) {
                String[] split = onLineList.get(i).split(",");
                intoIdIdOnFirstMap.put(split[2], split[0]);
            }
            ArrayList<String[]> arrList = new ArrayList<>();
            CsvReader reader = new CsvReader(updateFile, '\\', Charset.forName("GBK"));
            while (reader.readRecord()) {
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            for (int row = 1; row < arrList.size(); row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String[] split = arrList.get(row)[0].split(",");
                String intoId = split[0];
                String realCode = split[1];
                String realName = split[2];
                if (Objects.nonNull(intoIdIdOnFirstMap.get(intoId))) {
                    String id = intoIdIdOnFirstMap.get(intoId);
                    String sql = "update onfirst_input set utmCode = '"
                            + realCode + "' where id = " + id + ";\r\n";
                    System.out.println(sql);
                    strList.add(sql);
                }
            }
            if (Objects.nonNull(destPath)) {
                saveContentCovered(strList, destPath);
            }

            System.out.println("=============");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    public static List<String> readCsvByCsvReader(String updateFile, String infoFile, String destPath) {

        List<String> strList = new ArrayList<String>();
        try {
            List<String> onLineList = readOriginalInfoOfArray(infoFile);
            Map<String, String> intoIdIdOnFirstMap = new HashMap<>();
            for (int i = 1; i < onLineList.size(); i++) {
                String[] split = onLineList.get(i).split(",");
                intoIdIdOnFirstMap.put(split[1], split[0]);
            }
            ArrayList<String[]> arrList = new ArrayList<>();
            CsvReader reader = new CsvReader(updateFile, '\\', Charset.forName("GBK"));
            while (reader.readRecord()) {
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            for (int row = 1; row < arrList.size(); row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String[] split = arrList.get(row)[0].split(",");
                String intoId = split[0];
                String realCode = split[1];
                String realName = split[2];
                if (Objects.nonNull(intoIdIdOnFirstMap.get(intoId))) {
                    String id = intoIdIdOnFirstMap.get(intoId);
                    //update eagle_jsd_intopieces set utm_term = , channel_type,channel_category = ,into_channel_name =  where into_id =
                    String sql = "update eagle_jsd_intopieces set utm_term = '"
                            + realCode + "' , channel_type = '" + realCode + "',channel_category = '" + realName + "' ,into_channel_name = '" + realName + "' where id = " + id + ";\r\n";
                    System.out.println(sql);
                    strList.add(sql);
                }
            }
            if (Objects.nonNull(destPath)) {
                saveContentCovered(strList, destPath);
            }

            System.out.println("=============");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }


    /**
     * @param filePath
     * @param destPath
     * @return {@link  List< String>}
     * @throws
     * @author Monna
     * @date 2023/3/20 14:52
     * @description: 批量生成sql语句
     */


    public static List<String> readCsvByCsvReaderBatchSql(String filePath, String destPath) {
        List<String> strList = new ArrayList<String>();
        try {
            ArrayList<String[]> arrList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(filePath, '\\', Charset.forName("GBK"));
            while (reader.readRecord()) {
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            System.out.println("===========");

            for (int row = 1; row < 10 || row < arrList.size(); row++) {
//            for (int row = 0; row < arrList.size(); row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String[] split = arrList.get(row)[0].split(",");
                String intoId = "'" + split[0] + "'";
                strList.add(intoId);
            }
            System.out.println(strList.size());
            String join = String.join(",\r\n", strList);
            System.out.println(join);
            String sql = "SELECT id,trade_id,business_id,utmCode FROM `onfirst_input` where business_id in (" + join + ");";

            if (Objects.nonNull(destPath)) {
                saveContentCovered(Arrays.asList(sql), destPath);
            }

            System.out.println("=============");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    /**
     * @param file1
     * @param file2
     * @param destPath
     * @return {@link  List< String>}
     * @throws
     * @author Monna
     * @date 2023/3/20 15:23
     * @description: 对比两个文件，确认无需修改数据并输出至destPath
     */
    public static List<String> compareFile(String file1, String file2, String destPath) throws Exception {

        //需求中的intoId
        List<String> strList = new ArrayList<String>();
        //onfirst_input data
        List<String> onLineList = readOriginalInfoOfArray(file2);
        Map<String, String> intoIdIdOnFirstMap = new HashMap<>();
        for (int i = 1; i < onLineList.size(); i++) {
            String[] split = onLineList.get(i).split(",");
            intoIdIdOnFirstMap.put(split[2], split[0]);
        }
        Map<String, String> intoIdIdMap = new HashMap<>();
        List<String> arrList = readOriginalInfoOfArray(file1);
        for (int i = 1; i < arrList.size(); i++) {
            String[] split = arrList.get(i).split(",");
            if (Objects.nonNull(intoIdIdOnFirstMap.get(split[1]))) {
                //说明包含该intoId
                intoIdIdMap.put(split[1], split[0]);
            } else {
                strList.add(split[1]);
            }

        }
        System.out.println("需求修改数据行数为：" + intoIdIdMap.size());
        System.out.println("strList.size()=" + (strList.size()));
        String join = String.join(",", strList);
        if (Objects.nonNull(destPath)) {
            saveContentCovered(Arrays.asList(join), destPath);
        }
        System.out.println("=============");
        return strList;
    }

    public static void readCSVAndWrite(String inputCsvFileName1, String outputCsvFileName, String file3) throws Exception {
        try {
            //onfirst_input data
            List<String> onLineList = readOriginalInfoOfArray(file3);
            Map<String, String> intoIdIdOnFirstMap = new HashMap<>();
            for (int i = 1; i < onLineList.size(); i++) {
                String[] split = onLineList.get(i).split(",");
                intoIdIdOnFirstMap.put(split[2], split[0]);
            }
            // 创建CSV读对象
            CsvReader csvReader1 = new CsvReader(inputCsvFileName1);
            // 读表头
            csvReader1.readHeaders();
            CsvWriter csvWriter1 = new CsvWriter(outputCsvFileName, ',', Charset.forName("UTF-8"));
            String[] headers2 = {"id", "into_id", "utm_term", "channel_type", "into_channel_name", "missing"};
            csvWriter1.writeRecord(headers2);
            int n = 1;
            while (csvReader1.readRecord()) {
                String[] content2 = new String[headers2.length];
                for (int k = 0; k < headers2.length - 1; k++) {
                    String indexHeader = headers2[k];
                    String cell1_value_str = csvReader1.get(indexHeader);
                    content2[k] = cell1_value_str;
                    if (k == 1 && Objects.isNull(intoIdIdOnFirstMap.get(cell1_value_str))) {
                        content2[headers2.length - 1] = "该数据在onfirst_input表中缺失";
                    }
                }
                csvWriter1.writeRecord(content2);
                n++;
            }

            csvWriter1.flush();
            csvWriter1.close();

            csvReader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finish...");
        }
    }

    public static List<String[]> readOriginalInfo(String filePath) throws Exception {
        List<String[]> arrList = new ArrayList<>();
        CsvReader reader = new CsvReader(filePath, '\\', Charset.forName("GBK"));
        while (reader.readRecord()) {
            arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
        }
        reader.close();
        return arrList;
    }

    public static List<String> readOriginalInfoOfArray(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        List<String> result = Files.readAllLines(path, Charset.forName("UTF-8"));
        return result;
    }

    /**
     * @param contentList
     * @return {@link }
     * @throws
     * @author Monna
     * @date 2023/3/20 14:05
     * @description: 以覆盖形式保存文档
     */
    public static void saveContentCovered(List<String> contentList, String destPath) {

        try {

            File writeName = new File(destPath); // 相对路径，如果没有则要建立一个新的output.txt文件
            if (!writeName.exists()) {
                writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            }
            FileWriter writer = new FileWriter(writeName);
            BufferedWriter out = new BufferedWriter(writer);
            for (String content : contentList) {
                out.write(content);
            }
            out.flush(); // 把缓存区内容压入文件
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param contentList
     * @return {@link }
     * @throws
     * @author Monna
     * @date 2023/3/20 14:06
     * @description: 非覆盖，即更新形式保存(monna未验证 后续需要时再验证)
     */
    public static void saveContentNoCover(List<String> contentList) throws Exception {
        File file = new File("D:\\data.txt");
        if (!file.exists()) {
            file.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
        }
        FileOutputStream fos = new FileOutputStream(file, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(contentList.toString());
        bw.newLine();
        bw.flush();
        bw.close();
        osw.close();
        fos.close();

    }

    /**
     * @param
     * @return {@link }
     * @throws
     * @author Monna
     * @date 2023/3/20 14:09
     * @description: 读取文本方式1（JDK8）
     */
    public static void read1() throws Exception {
        Path path = Paths.get("D:/aa.txt");
        byte[] data = Files.readAllBytes(path);
        String result = new String(data, "utf-8");
    }

    /**
     * @param
     * @return {@link }
     * @throws
     * @author Monna
     * @date 2023/3/20 14:09
     * @description: 读取文本方式2（JDK8）
     */
    public static void read2() throws Exception {
        Path path = Paths.get("D:/aa.txt");
//        Files.readLines(path);
    }

    /**
     * @param
     * @return {@link }
     * @throws
     * @author Monna
     * @date 2023/3/20 14:11
     * @description: 读取文本方式2（JDK11）
     */
    public static void read3() throws Exception {
        Path path = Paths.get("D:/aa.txt");
//        String data = Files.readString(path);
//        System.out.println(data);
    }


}
