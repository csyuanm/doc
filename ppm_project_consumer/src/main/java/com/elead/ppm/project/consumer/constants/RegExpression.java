package com.elead.ppm.project.consumer.constants;

import java.util.regex.Pattern;

/**
 * RegExpression
 *
 * @author wangxz
 * @date 2017/3/23
 */

public class RegExpression {
    public static final String id = "^(\\d{1,18})$";

    public static final String ids = "^(\\d{1,18})([ ]*,[ ]*(\\d{1,18}))*$"; //id列表,逗号分隔"
    public static final String admin_id = "^(\\d{0,18})$";//当id可以传递可以不传递时,在接口处设置默认值0
    //  public static final String price = "^0|(([1-9][0-9]{1,7})|(([0]\\.\\d{1,2})|([1-9][0-9]{1,7}\\.\\d{1,2})))$";//价格
    public static final String price = "([1-9]+[0-9]*|0)(\\.?[\\d]{0,2})?";//价格
    public static final String number = "^[0-9]\\d*$";//数字重0-99999....
    public static final String num="^[1-9]\\d*$";//数字重1-99999....
    public static final String pageNo ="^(\\d{1,11})$"; //page_no
    public static final String pageSize = "^(\\d{1,11})$";//page_size
    //分子/分母 表达式
    public static final String ratio = "^([1-9]/[1-9])$";
    public static final String round = "^[2|3|4]$";
    public  static  final String tinyint_range="(([0-9])|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))";
    public static final String prices = "([1-9]+[0-9]*|0)(\\.?[\\d]{0,4})?";//价格，四位
    public static final String status = "^-?[0-9]\\d*$";//
    /*yyyy-MM-dd HH:mm:ss*/
    public static final String checkDate ="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
            + "|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))"
            + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
            + "((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s"
            + "(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

    public static boolean matcher(String pattern,String object){
        if (object==null) {
            return false;
        }
        Pattern p = Pattern.compile(pattern);
        return  p.matcher(object).matches();
    }

    public static void main(String[] args) {
		/*Pattern p2 = Pattern.compile(price);
   	 System.out.println(" =======" + p2.matcher("0").matches());
   	 */
   /*	String pattern = "^[2|3|4]$";
   	Pattern p2 = Pattern.compile(pattern);
  	 System.out.println(" =======" + p2.matcher("4").matches()); */
        String pattern = price;
        Pattern p2 = Pattern.compile(pattern);
        System.out.println(" =======" + p2.matcher("1").matches());

    }
}
