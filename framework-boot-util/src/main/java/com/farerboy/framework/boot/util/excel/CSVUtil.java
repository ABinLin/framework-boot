package com.farerboy.framework.boot.util.excel;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * CSV文件导出工具类
 *
 * @author farerboy
 */
public class CSVUtil {

    public static File createCSVFile(List list, Class clazz,String outPutPath, String filename) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //表头List
        List<Object> headList = new ArrayList<Object>();

        // 获取属性
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = new ArrayList<Field>();
        for (Field fie : fields) {
            if (fie.isAnnotationPresent(ExcelAnnotation.class)) {
                fieldList.add(fie);
                String[] name = fie.getAnnotation(ExcelAnnotation.class).name();
                headList.add(name[0]);
            }
        }
        // 按照id进行排序
        Collections.sort(fieldList, new Comparator<Field>() {
            @Override
            public int compare(Field f1, Field f2) {
                return f1.getAnnotation(ExcelAnnotation.class).id() - f2.getAnnotation(ExcelAnnotation.class).id();
            }
        });

        // 创建数据List
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        for(Object t : list){
            List<Object> cellContentList = new ArrayList<Object>();
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                //获取方法名
                String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method method = clazz.getMethod(methodName);
                Object result = method.invoke(t);
                cellContentList.add(result);
            }
            dataList.add(cellContentList);
        }
        return createCSVFile(headList,dataList,outPutPath,filename);
    }


    /**
     * CSV文件生成方法
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for(int i=0 ; i< row.size() ; i++){
            StringBuffer sb = new StringBuffer();
            String rowStr = "";
            if(i==row.size()-1){
                rowStr = sb.append(row.get(i)).toString();
            }else {
                rowStr = sb.append(row.get(i)).append(",").toString();
            }
            csvWriter.write(rowStr);
        }

        csvWriter.newLine();
    }


    /**
     * 测试数据
     */

    public static void main(String[] args) {
        List<Object> exportData = new ArrayList<Object>();
        exportData.add("第一列");
        exportData.add("第二列");
        exportData.add("第三列");
        List<List<Object>> datalist = new ArrayList<List<Object>>();
        List<Object> data=new ArrayList<Object>();
        data.add("111");
        data.add("222");
        data.add("333");
        List<Object> data1=new ArrayList<Object>();
        data1.add("444");
        data1.add("555");
        data1.add("666");
        datalist.add(data);
        datalist.add(data1);
        String path = "F:/export/";
        String fileName = "文件导出";
        File file = CSVUtil.createCSVFile(exportData, datalist, path, fileName);
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
    }
}
