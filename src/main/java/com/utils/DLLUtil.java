package com.utils;

import com.entities.UserInfo;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

/**
 * 调用dll工具
 */
class DLLUtil {

    private static String SendRcv2(String init, String request, String response)
            throws NativeException, IllegalAccessException {
        JNative n = null;
        n = new JNative("SendRcv2.dll", "SendRcv2");
        System.out.println("调用驱动");
        n.setRetVal(Type.STRING);
        int i = 0;
        n.setParameter(i++, Type.STRING, "" + init);
        n.setParameter(i++, Type.STRING, "" + request);
        n.setParameter(i++, Type.STRING, "" + response);
        n.invoke();
        return n.getRetVal();
    }


    static UserInfo getId() {
        UserInfo userInfo = new UserInfo();
        String id;
        String name;
        String SFZ;
        String sendMessage = "SSSS";
        sendMessage += "S000";
        sendMessage += "    ";
        sendMessage += "42504710300     ";
        sendMessage += "    ";
        sendMessage += "                ";
        sendMessage += "                ";
        for (int i = 0; i < 444; i++) {
            sendMessage += " ";
        }
        sendMessage += "ZZZZ";
        String info;
        try {
            System.out.println("获取ID：" + sendMessage);
            info = SendRcv2("12345678", sendMessage, sendMessage);
            System.out.println(info);
            id = info.substring(64, 73);
            System.out.println(id);
            System.out.println("卡号" + id.length());
            name = info.substring(73, 100);
            System.out.println(name);
            System.out.println("名字" + name.length());
            SFZ = info.substring(101, 119);
            System.out.println(SFZ);
            System.out.println("身份证" + SFZ.length());
            userInfo.setSFZ(SFZ);
            userInfo.setUserName(name.trim());
            userInfo.setId(id);
        } catch (NativeException | IllegalAccessException e1) {
            e1.printStackTrace();
            return null;
        }
        return userInfo;
    }

}

