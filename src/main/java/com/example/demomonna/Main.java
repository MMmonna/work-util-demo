package com.example.demomonna;

import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @Author： Monna
 * @CreateTime:2023-02-22 16:15:29
 * @Descrption:
 */
public class Main {
public static void main(String[] args) throws Exception {
    //对比两文件并将不同数据以txt文档格式进行输出
//    CsvReaderUtil.compareFile("C:\\Users\\monna\\Desktop\\hc\\4616\\original_data_eagle_jsd_intopieces_0321.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\original_data_onfirst_input0321.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\missing.txt");
//    CsvReaderUtil.readCSVAndWrite("C:\\Users\\monna\\Desktop\\hc\\4616\\original_compare.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\compare_missing.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\original_data_onfirst_input0321.csv");
    //eagle_jsd_intopieces sql备份数据生成语句
//    CsvReaderUtil.readCsvByCsvReaderBatchSql("C:\\Users\\monna\\Desktop\\hc\\4616\\恒小花API问题数据--20230303.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\original_eagle_jsd_intopieces20230320.sql");
    //onfirst_input sql备份数据生成语句
//    CsvReaderUtil.readCsvByCsvReaderBatchSql("C:\\Users\\monna\\Desktop\\hc\\4616\\恒小花API问题数据--20230303.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\original_onfirst_input20230320.sql");
    //eagle_jsd_intopieces更新数据sql生成
//   CsvReaderUtil.readCsvByCsvReader("C:\\Users\\monna\\Desktop\\hc\\4616\\恒小花API问题数据--20230303.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\original_data_eagle_jsd_intopieces_0321.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\fix_eagle_jsd_intopieces20230321.sql");
    //onfirst_input sql语句生成
    CsvReaderUtil.readCsvByCsvReaderDataExpander("C:\\Users\\monna\\Desktop\\hc\\4616\\恒小花API问题数据--20230303.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\original_data_onfirst_input0321.csv","C:\\Users\\monna\\Desktop\\hc\\4616\\fix_onfirst_input20230321.sql");

}

}