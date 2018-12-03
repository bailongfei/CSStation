package com.task;

import com.Info.ResultInfo;
import com.entities.CallResult;
import com.entities.SrvGroupJC;
import com.entities.UserInfo;
import com.services.SrvGroupJCService;
import com.services.impl.SrvGroupKCServiceImpl;
import com.utils.IniReader;
import javafx.concurrent.Task;

public class RegisterTask extends Task<ResultInfo> {

    private UserInfo userInfo;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private String customerType;

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    private SrvGroupJC srvGroupJC;

    public void setSrvGroupJC(SrvGroupJC srvGroupJC) {
        this.srvGroupJC = srvGroupJC;
    }

    private ResultInfo<CallResult> resultInfo;

    public ResultInfo<CallResult> getResultInfo() {
        return resultInfo;
    }

    private SrvGroupJCService srvGroupJCServices = new SrvGroupKCServiceImpl();

    @Override
    protected ResultInfo call(){
        resultInfo = srvGroupJCServices.registerCallStatement(userInfo,customerType,srvGroupJC);
        String[] ids = IniReader.readIniNoLetter();
        if (ids!=null){
            for (String s:ids) {
                if (srvGroupJC.getSrvGroupId().equals(s)){
                    resultInfo.getT().setSrvGroupLetter("");
                }
            }
        }
        return resultInfo;
    }
}
