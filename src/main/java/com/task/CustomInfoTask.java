package com.task;

import com.Info.ResultInfo;
import com.entities.UserInfo;
import com.services.HospCustomerService;
import com.services.impl.HospCustomerServicesImpl;
import com.utils.CardIdentityUtil;
import com.utils.LogUtil;
import javafx.concurrent.Task;

public class CustomInfoTask extends Task<ResultInfo> {

    private String cardNumber;

    private String type;    // 0磁卡，-1芯片卡，1住院号

    private ResultInfo<UserInfo> resultInfo;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public ResultInfo<UserInfo> getResultInfo() {
        return resultInfo;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected ResultInfo<UserInfo> call() {
        String typeStr = "";
        if (type.equals("-1")){
            resultInfo = CardIdentityUtil.getUserInfoByDLL();
            typeStr = "芯片卡查询：";
        }
        if (type.equals("0")){
            resultInfo = CardIdentityUtil.getUserInfoByCardString(cardNumber);
            typeStr = "磁卡查询：";
        }
        if (type.equals("1")){
            HospCustomerService hospCustomerService = new HospCustomerServicesImpl();
            resultInfo = hospCustomerService.queryHospCustomer(cardNumber);
            typeStr = "住院号查询：";
        }
        LogUtil.markLog(resultInfo.getStatus(),typeStr + resultInfo.getInformation());
        return resultInfo;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
    }

    @Override
    protected void failed() {
        super.failed();
    }

    @Override
    protected void cancelled() {
        super.cancelled();
    }

}
