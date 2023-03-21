package com.example.demomonna;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DemoMonnaApplication {

    public static void main(String[] args) throws Exception {
      double i = -99;
      double j = -99.0;
        System.out.println(i == j);
        System.out.println("==========");
        System.out.println(i+"==="+j);



    }

    public void testJson(){
        Param param1  = new Param();
        param1.setS("sssssdfdsf");
        param1.setCount(12);
        System.out.println(param1);
        System.out.println("===========");
        Param param2  = new Param();
        BeanCopierWithCacheUtil.copy(param1,param2,false,null);
        param2.setS("我是222");
        System.out.println(param2);
        Param param3  = new Param();
        BeanCopierWithCacheUtil.copy(param1,param3,false,null);
        param3.setS("3333333");
        System.out.println(param1+"======"+param2+"===="+param3);
    }

    public void numTest(){
        Double value = 22.0;
        System.out.println(value);
        Double value2 = Double.valueOf("22.0");
        System.out.println(value2);
        JSONObject json = new JSONObject();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("key1:map1","1");
        JSONObject cusField =  new JSONObject();
        cusField.putAll(map1);
        json.put("PL_Derive_New",cusField);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("key1:map12","2");
        JSONObject cusField2 =  new JSONObject();
        cusField2.putAll(map2);
        json.getJSONObject("PL_Derive_New").putAll(cusField2);
        System.out.println(json);
    }

    public static void jsonTest() {
        Param param = new Param();
        JSONObject phoneJson = new JSONObject();
//        retMessageJson.putAll((JSONObject) JSON.toJSON(param));
        phoneJson.putAll((JSONObject) JSON.toJSON(param));
        System.out.println(phoneJson);
    }


    public static void mergeSort(int[] array, int from, int to) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSort(array, from, middle);
            mergeSort(array, middle + 1, to);
            mergeSort(array, from, middle, to);
        }
    }

    private static void mergeSort(int[] array, int from, int middle, int to) {
        int n1 = middle - from + 1;
        int n2 = to - middle;
        int[] a1 = new int[n1];
        int[] a2 = new int[n2];
        for (int i = 0; i <= n1 - 1; i++) {
            a1[i] = array[from + i];
        }
        for (int j = 0; j <= n2 - 1; j++) {
            a2[j] = array[middle + j + 1];
        }
        int i = 0, j = 0;
        for (int k = from; k <= to; k++) {
            if (i <= a1.length - 1 && j <= a2.length - 1) {
                if (a1[i] <= a2[j]) {
                    array[k] = a1[i];
                    i++;
                } else {
                    array[k] = a2[j];
                    j++;
                }
            } else if (i > a1.length - 1 && j <= a2.length - 1) {
                array[k] = a2[j];
                j++;
            } else if (j > a2.length - 1 && i <= a1.length - 1) {
                array[k] = a1[i];
                i++;
            }
        }
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i <= array.length - 1; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int key = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = key;
                }
            }
        }
    }


}
