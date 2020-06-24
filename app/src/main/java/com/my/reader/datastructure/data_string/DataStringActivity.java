package com.my.reader.datastructure.data_string;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.my.reader.datastructure.R;

import java.util.Arrays;

/**
 * 字符串相关算法
 */
public class DataStringActivity extends AppCompatActivity {
    public static final String TAG = "DataStringActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_string);
        // 算法一
        exchangeString();
        // 算法二  判断 字母异位词
        letterEctopic("anagrama", "nagaram");
    }


    /**
     * 算法一  反转字符串   "datastring"
     * 思路一  ：字符串转数组  判断条件  两个相邻   定义2 变量 前后两两交换
     */
    String testOne = "datastring1";

    private void exchangeString() {
        char[] testArray = testOne.toCharArray();
        for (int i = 0; i < testArray.length - 1 - i; i++) {
            char temp = testArray[i];
            testArray[i] = testArray[testArray.length - 1 - i];
            testArray[testArray.length - 1 - i] = temp;
            Log.e("exchangeString", "exchangeString 头 i：" + i + "----尾部：" + (testArray.length - 1 - i) + new String(testArray));
        }
        Log.e("exchangeString", "exchangeString  原字符串: datastring反转后：" + new String(testArray));
    }

    /**
     * 算法二  编写程序判断2个字符串 s ,t  是否是字母异位词  注意 假设两个字符串只包含小写字母 【字母异位词：  两个字符串的长度一样   包含字母及字母个数也一样   只是字母排序不同导致异位词
     * 如 anagram  和  nagaram】
     * 思路一  用2个  26小写字母作为数组  判断  字母出现的个数
     * 思路二  用一个 26位小写字母数组   用s里小字母对比数组 对应位置加1   用t字符串 对应的数组减1    最后判断 指定位置是否为0   即可
     */
    private boolean letterEctopic(String strOne, String strTwo) {
        // 思路一  用2个  26小写字母作为数组  判断  字母出现的个数
//        // 1 先将字符串转成数组
//        char[] charArrayOne = strOne.toCharArray();
//        char[] charArrayTwo = strTwo.toCharArray();
//        // 2声明 长度为26的int数组
//        int[] letterOne = new int[26];
//        int[] letterTwo = new int[26];
//        // 3 统计  字符数组 1的 字母出现个数
//        for (int i = 0; i < charArrayOne.length; i++) {
//            letterOne[getCharIndex(charArrayOne[i])] += 1;
//        }
//        //  统计  字符数组 2 的 字母出现个数
//        for (int i = 0; i < charArrayTwo.length; i++) {
//            letterTwo[getCharIndex(charArrayOne[i])] += 1;
//        }
//        // 对比字母出现个数是否相等
////        Arrays.equals()方法比较两个数组元素是否相等
//        Log.e(TAG, strOne + "和" + strTwo + (Arrays.equals(letterOne, letterTwo) ? "是" : "不是") + "异位词");
//        return Arrays.equals(letterOne, letterTwo);


        //思路二 判断0 的那个
        // 1 先将字符串转成数组
        char[] charArrayOne = strOne.toCharArray();
        char[] charArrayTwo = strTwo.toCharArray();
        // 定义一个长度为26的  数组  有就加一 没有就减一
        int[] letterOne = new int[26];
        for (int i = 0; i < charArrayOne.length; i++) {
            letterOne[getCharIndex(charArrayOne[i])] += 1;
        }
        for (int i = 0; i < charArrayTwo.length; i++) {
            letterOne[getCharIndex(charArrayTwo[i])] -= 1;
        }
        boolean isOneAllZero = true;
        boolean isTwoAllZero = true;
        // 判断1  里指定位置是否全部为0
        for (int i = 0; i < charArrayOne.length; i++) {
            if (letterOne[getCharIndex(charArrayOne[i])] != 0) {
                isOneAllZero = false;
            }
        }
        // 判断2  里指定位置是否全部为0
        for (int i = 0; i < charArrayTwo.length; i++) {
            if (letterOne[getCharIndex(charArrayTwo[i])] != 0) {
                isTwoAllZero = false;
            }
        }
        Log.e(TAG, strOne + "和" + strTwo + (isOneAllZero && isTwoAllZero ? "是" : "不是") + "异位词");
        return isOneAllZero && isTwoAllZero;
    }

    /**
     * 判断字符 在26英文字母的位置下标
     *
     * @param letter
     * @return
     */
    private int getCharIndex(char letter) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        return letters.indexOf(letter);
    }
}
