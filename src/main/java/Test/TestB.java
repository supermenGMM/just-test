package Test;

public class TestB {
    /**
     * 判断账户逾期状态 {@link com.sunline.ccs.service.busimpl.LoanServiceImpl.isOverdue}
     * @param time1	toltime月内超过time1次逾期
     * @param time2	toltime月内连续超过time2次逾期
     * @param toltime
     * @return false为未逾期，true为逾期
     */
    public static boolean isOverdue(String PaymentHist,int time1,int time2,int toltime){
        String payHis="";
        char u='U';//U|还款未达最小还款额
        char n='N';//N|未还款
        boolean overdue=false;
        if(PaymentHist==null) PaymentHist="";
        if(PaymentHist.length()>=toltime){
            payHis=PaymentHist.substring(0, toltime);
        }else{
            payHis=PaymentHist;
        }
        int times=counterTimes(payHis,u,n);
        if(times>=time1){
            overdue=true;
        }
        times=counterMaxTimes(payHis,u,n);
        if(times>=time2){
            overdue=true;
        }
        return overdue;
    }
    public static int counterTimes(String s,char c,char b){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c||s.charAt(i)==b)
                count++;
        }
        return count;
    }
    public  static int counterMaxTimes(String s,char c,char b){
        int count=0;
        int maxTimes=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c||s.charAt(i)==b){
                count++;
            }else{
                count=0;
            }
            if(count>maxTimes){
                maxTimes=count;
            }
        }
        return maxTimes;
    }
    public static void main(String[] args){
        boolean c = isOverdue("C", 3, 2, 6);
        System.out.println(c);
    }
}
