package com.utils;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * 异步
 */
public class AsyncService{

    private Service<String> service;

    public AsyncService(AsyncServiceSoap asyncServiceSoap){
        service = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() {
                        return "succeeded";
                    }

                    protected void succeeded() {
                        asyncServiceSoap.execute();
                    }
                };
            }
        };
    }

    public void start(){
        service.start();
    }
}

