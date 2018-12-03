package com.utils;


import com.Info.ResultInfo;
import com.entities.UserInfo;
import com.webservices.TranBookingInfo;
import com.webservices.TranBookingInfoPortType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class CardIdentityUtil {

    public static ResultInfo<UserInfo> getUserInfoByDLL(){
        ResultInfo<UserInfo> resultInfo = new ResultInfo<>();
        UserInfo userInfo;
        System.out.println("s000");
        userInfo = DLLUtil.getId();
        if (userInfo!=null){
            resultInfo.setStatus(1);
            resultInfo.setT(userInfo);
            resultInfo.setInformation("查询成功，病人名为："+userInfo.getUserName());
        } else {
            resultInfo.setStatus(0);
            resultInfo.setInformation("没有信息");
        }
        return resultInfo;
    }

    public static ResultInfo<UserInfo> getUserInfoByCardString(String cardString){
        ResultInfo<UserInfo> resultInfo = new ResultInfo<>();
        UserInfo userInfo;
        resultInfo.setT(null);
        // 判断输入框的字符串长度
        if (cardString.length()==15){
            System.out.println("自费走his");
            String result = getInformationFromHis(cardString);
            System.out.println(result);
            // 拆分result的值
            String pname[] = {"Result","ResultInfo","CustomerName","CustomerNo","CustomerTel","CustomerMobile"};
            String[][] resultArray = getData(result,pname,0);
            if (resultArray.length>1){
                if (resultArray[0][0].equals("1")){
                    userInfo = new UserInfo();
                    userInfo.setId(cardString);
                    userInfo.setSFZ(resultArray[1][3]);
                    userInfo.setUserName(resultArray[1][2]);
                    resultInfo.setInformation(resultArray[0][1]+",病人名为："+userInfo.getUserName());
                    resultInfo.setT(userInfo);
                    resultInfo.setStatus(1);
                } else {
                    resultInfo.setStatus(0);
                    resultInfo.setInformation(resultArray[0][1]);
                }
            } else {
                if (resultArray[0][0].equals("1")){
                    resultInfo.setStatus(0);
                    resultInfo.setInformation("没有病人信息");
                } else {
                    resultInfo.setStatus(0);
                    resultInfo.setInformation(resultArray[0][1]);
                }
            }
        } else {
            System.out.println("白玉兰卡走his");
            String result = getInformationFromHis(cardString);
            // 拆分result的值
            String pname[] = {"Result","ResultInfo","CustomerName","CustomerNo","CustomerTel","CustomerMobile"};
            String[][] resultArray = getData(result,pname,0);
            if (resultArray[0][0].equals("1")){
                // 判断是否有信息
                if (resultArray.length>1){
                    if (resultArray[1][2]!=null&&!resultArray[1][2].equals("")&&resultArray[1][2].length()>1){
                        // his有信息
                        userInfo = new UserInfo();
                        userInfo.setId(cardString);
                        userInfo.setUserName(resultArray[1][2]);
                        userInfo.setSFZ(resultArray[1][3]);
                        resultInfo.setInformation(resultArray[0][1]+",病人名为："+userInfo.getUserName());
                        resultInfo.setT(userInfo);
                        resultInfo.setStatus(1);
                    }else {
                        resultInfo.setInformation(resultArray[0][1]);
                        resultInfo.setStatus(0);
                    }
                } else{
                    if (!resultArray[0][0].equals("1")){
                        resultInfo.setStatus(0);
                        resultInfo.setInformation(resultArray[0][1]);
                    } else {
                        resultInfo.setStatus(0);
                        resultInfo.setInformation("没有病人信息");
                    }
                }
            }
//              else {
//                ResultInfo.setFlag(false);
//                ResultInfo.setInformation(resultArray[0][1]);
//            }
        }
        return resultInfo;
    }

    public static ResultInfo<UserInfo> getUserInfoByCustomerCard(){
//        System.out.println("走身份证");
//        ResultInfo<UserInfo> ResultInfo = new ResultInfo<>();
//        UserInfo userInfo = new UserInfo();
//        int handle = LBIDCardReader.openReader();
//        IDCardResult.ByReference info = IccDllIDCardReader.readIDCard(handle);
//        LBIDCardReader.closeReader(handle);
//        if (info!=null){
//            String id = "0";
//            String SFZ = new String(info.pid).trim();
//            String name = new String(info.name).trim();
//            userInfo.setSFZ(SFZ);
//            userInfo.setId(id);
//            userInfo.setUserName(name);
//        }
//        ResultInfo.setT(userInfo);
//        return ResultInfo;
        return null;
    }

    //执行his的接口返回数据
    private static String getInformationFromHis(String customerId){
        TranBookingInfoPortType tranBookingInfoPortType = new TranBookingInfo().getTranBookingInfoHttpPort();
        String str="<?xml version='1.0' encoding='gbk'?>"
                + "<SERVICES sname='HSBY.BOOKING.GetCustomerInfo'>"
                + "<PARAMS><PARAM pname='IdentifyID' pval='6AA36B715F8CEC7D8B79FFB6D16C4DD4' />"
                + "<PARAM pname='CustomerID' pval='"+customerId+"' />"
                + "</PARAMS></SERVICES>";
        System.out.println(str);
        String backInfo = tranBookingInfoPortType.execXML(str);
        System.out.println(backInfo);
        return backInfo;
    }

    private static String[][] getData(String execXML, String[] Pname, int location) {
        // 获得xml文件内容
        StringReader sr = new StringReader(execXML);
        InputSource is = new InputSource(sr);
        // 返回2元数组
        String[][] pval = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            Element rootElement = doc.getDocumentElement();
            NodeList list = rootElement.getChildNodes();
            // 修改pname的值即可针对不同的xml
            pval = new String[list.getLength()][Pname.length];
            for (int i = location; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                NodeList DistrictList = element.getElementsByTagName("PARAM");
                for (int j = 0; j < DistrictList.getLength(); j++) {
                    Element element1 = (Element) DistrictList.item(j);
                    String pname = element1.getAttribute("pname");
                    for (int k = 0; k < Pname.length; k++) {
                        if (pname.equals(Pname[k])) {
                            pval[i][k] = element1.getAttribute("pval");
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return pval;
    }




}
