package Test;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestA {
    public static Date fixYear(String b007, Date batchDate) {
        Calendar batchDateTime = Calendar.getInstance();
        //对于交易日期大于当前营业日期2天及以内的交易，视为正常交易，交易年份按当日顺推处理
        batchDateTime.setTime(DateUtils.addDays(batchDate, 2));
        Calendar txnDateTime = Calendar.getInstance();
        System.out.println(b007.substring(0,2));
        System.out.println(Integer.parseInt(b007.substring(2, 4)));
        txnDateTime.set(batchDateTime.get(Calendar.YEAR),
               1,
              2,
                Integer.parseInt(b007.substring(4, 6)),
                Integer.parseInt(b007.substring(6, 8)),
                Integer.parseInt(b007.substring(8, 10)));
        System.out.println(txnDateTime.getTime().toLocaleString());
        if(txnDateTime.get(Calendar.DAY_OF_YEAR) > batchDateTime.get(Calendar.DAY_OF_YEAR)){
            System.out.println(txnDateTime.getTime().toLocaleString());
            txnDateTime.add(Calendar.YEAR, -1);
        }
        return txnDateTime.getTime();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date parse = sdf.parse("20190207 102211");

        Date date = fixYear("0226628160", parse);
        System.out.println(date.toLocaleString());

        Calendar instance = Calendar.getInstance();

        instance.set(2018, 1, 9, 10, 10, 10);
        System.out.println(instance.getTime().toLocaleString());


    }
}
