package com.cui.tech.chaos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 金额处理工具类
 *
 * @author xiaoshiyilang
 * @version 1.0
 * @date 2018/11/15
 */
public class MoneyUtils {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 2;

    public final static String CURRENCY_FORMAT = "###,##0.00";

    // 这个类不能实例化
    private MoneyUtils() {
    }

    /**
     * 提供精确的加法运算（double类型）
     *
     * @param d
     * @param d1
     * @return
     */
    public static double add(double d, double d1) {
        BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
        return bigdecimal.add(bigdecimal1).doubleValue();
    }

    /**
     * 提供精确的假发运算（字符串类型）
     *
     * @param s
     * @param s1
     * @return
     */
    public static double add(String s, String s1) {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.add(bigdecimal1).doubleValue();
    }

    /**
     * 提供精确的减法运算（double类型）
     *
     * @param d
     * @param d1
     * @return
     */
    public static double sub(double d, double d1) {
        BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
        return bigdecimal.subtract(bigdecimal1).doubleValue();
    }

    /**
     * 提供精确的减法运算（字符串类型）
     *
     * @param s
     * @param s1
     * @return
     */
    public static double sub(String s, String s1) {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.subtract(bigdecimal1).doubleValue();
    }

    /**
     * 提供精确的乘法运算（double类型）
     *
     * @param d
     * @param d1
     * @return
     */
    public static double mul(double d, double d1) {
        BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
        return bigdecimal.multiply(bigdecimal1).doubleValue();
    }

    /**
     * 提供精确的乘法运算（String类型）
     *
     * @param s
     * @param s1
     * @return
     */
    public static double mul(String s, String s1) {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.multiply(bigdecimal1).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入(double类型)
     *
     * @param d  被除数
     * @param d1 除数
     * @return 两个参数的商
     */
    public static double div(double d, double d1) {
        return div(d, d1, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入(字符串类型)
     *
     * @param s  被除数
     * @param s1 除数
     * @return 两个参数的商
     */
    public static double div(String s, String s1) {
        return div(Double.parseDouble(s), Double.parseDouble(s1), DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入（double类型）
     *
     * @param d  被除数
     * @param d1 除数
     * @param i  表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double d, double d1, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
        return bigdecimal.divide(bigdecimal1, i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入（字符串类型）
     *
     * @param s
     * @param s1
     * @param i
     * @return
     */
    public static double div(String s, String s1, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.divide(bigdecimal1, i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param d 需要四舍五入的数字
     * @param i 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double d, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigdecimal1 = new BigDecimal("1");
        return bigdecimal.divide(bigdecimal1, i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param s 需要四舍五入的数字字符
     * @param i 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(String s, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal("1");
        return bigdecimal.divide(bigdecimal1, i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 金额格式化字符串
     *
     * @param d
     * @param i
     * @return
     */
    public static String roundformat(double d, int i) {
        String num = null;
        if (i == 1) {
            num = "0.0";
        }
        if (i == 2) {
            num = "0.00";
        }
        if (i == 3) {
            num = "0.000";
        }
        if (i == 4) {
            num = "0.0000";
        }
        if (i == 0) {
            num = "0";
        }
        DecimalFormat df = new DecimalFormat(num);
        return df.format(d);
    }

    /**
     * 输出数字的格式(四舍五入)
     *
     * @param bd     BigDecimal 要格式华的数字
     * @param format String 格式 "###,##0.##"
     * @return String
     */
    public static String numberFormat(double bd, String format) {
        DecimalFormat bf = new DecimalFormat(format);
        return bf.format(bd);
    }

    /**
     * 输出数字的格式 (非四舍五入)
     *
     * @param bd     BigDecimal 要格式华的数字
     * @param format String 格式 "###,##0.##"
     * @return String
     */
    public static String numberFormat2(double bd, String format) {
        DecimalFormat bf = new DecimalFormat(format);
        bf.setRoundingMode(RoundingMode.FLOOR);
        return bf.format(bd);
    }

}
