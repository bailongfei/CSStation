package com.task;

import com.Info.ResultInfo;
import com.entities.CustomerQueueJCRecording;
import com.services.SrvGroupJCService;
import com.services.impl.SrvGroupKCServiceImpl;
import com.utils.LogUtil;
import javafx.concurrent.Task;

public class ActivateTask extends Task<ResultInfo> {

    private CustomerQueueJCRecording customerQueueJCRecording;

    public void setCustomerQueueJCRecording(CustomerQueueJCRecording customerQueueJCRecording) {
        this.customerQueueJCRecording = customerQueueJCRecording;
    }

    private SrvGroupJCService srvGroupJCService = new SrvGroupKCServiceImpl();

    private ResultInfo<Integer> resultInfo;

    public ResultInfo<Integer> getResultInfo() {
        return resultInfo;
    }

    @Override
    protected ResultInfo call() {

        return resultInfo;
    }
}
