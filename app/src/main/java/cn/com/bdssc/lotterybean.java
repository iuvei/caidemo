package cn.com.bdssc;

/**
 * Created by Administrator on 2018/4/10.
 */

public class lotterybean {
    /**
     * 开奖时间
     */
    private String kaijiangDate;
    /**
     * 开奖号码
     */
    private String kaijiangNum;

    @Override
    public String toString() {
        return "lotterybean{" +
                "kaijiangDate='" + kaijiangDate + '\'' +
                ", kaijiangNum='" + kaijiangNum + '\'' +
                ", kaijiangCount='" + kaijiangCount + '\'' +
                '}';
    }

    /**
     * 开奖期号
     */
    private String kaijiangCount;

    public String getKaijiangCount() {
        return kaijiangCount;
    }

    public String getKaijiangDate() {
        return kaijiangDate;
    }

    public String getKaijiangNum() {
        return kaijiangNum;
    }

    public void setKaijiangCount(String kaijiangCount) {
        this.kaijiangCount = kaijiangCount;
    }

    public void setKaijiangDate(String kaijiangDate) {
        this.kaijiangDate = kaijiangDate;
    }

    public void setKaijiangNum(String kaijiangNum) {
        this.kaijiangNum = kaijiangNum;
    }
}
