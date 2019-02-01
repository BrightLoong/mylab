package io.github.brightloong.java.all.learn;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clent class
 *
 * @author BrightLoong
 * @date 2018/6/28
 */
public class Clent {

    private int[] weight = {1,2,3};

    private int[] value = {60,100,120};

    private  int[] dp = new int[14000];


    public static void main(String[] args) {
        //new Clent().Solve(3, 5);
        RelateBenefit relateBenefit = new RelateBenefit();
        relateBenefit.setUid(123L);
        relateBenefit.setRate(new BigDecimal(0.6));
        RelateBenefit relateBenefit2 = new RelateBenefit();
        relateBenefit2.setUid(345L);
        relateBenefit2.setRate(new BigDecimal(0.2));
        List<RelateBenefit> relateBenefits = new ArrayList<>();
        relateBenefits.add(relateBenefit);
        relateBenefits.add(relateBenefit2);
        System.out.println(JSON.toJSONString(relateBenefits, true));

    }


    public void Solve(int n, int w) {
        for (int i = 0; i < n; i++) {
             for (int j=w; j>=weight[i]; j--) {
                if( dp[j-weight[i]] + value[i] > dp[j] )
                    dp[j] = dp[j-weight[i]] + value[i];
            }
        }
        System.out.println(dp[w]);
    }

    private static class RelateBenefit {
        private Long uid;
        private BigDecimal rate;

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }
    }
}
