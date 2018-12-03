package com.controller;

import com.Info.ResultInfo;
import com.entities.CallResult;
import com.entities.CustomerQueueJCRecording;
import com.entities.SrvGroupJC;
import com.nodes.CellLabel;
import com.services.SrvGroupJCService;
import com.services.impl.SrvGroupKCServiceImpl;
import com.task.CustomInfoTask;
import com.entities.UserInfo;
import com.nodes.ProgressFrom;
import com.nodes.Toast;
import com.task.RegisterTask;
import com.utils.AsyncService;
import com.utils.IniReader;
import com.utils.LogUtil;
import com.utils.TicketPane;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.util.List;

/**
 * 2018年11月01日10:35:08
 * 更新非超声系列检查 添加检查项目 226-234 346-354
 */
public class CSBaseController {

    public static Stage stage;

    @FXML
    private TextField customerIdTextField;              //卡号文本输入框
    @FXML
    private TextField customerNameField;                //病人信息名输入框
    @FXML
    private Button SSCReadButton;                       //社保卡读卡按钮
    @FXML
    private Button MSCReadButton;                       //磁卡读卡按钮
    @FXML
    private Button HNReadButton;                        //住院号读卡按钮
    @FXML
    private Button registerButton;                      //登记按钮
    @FXML
    private Button clearButton;                         //清除按钮
    @FXML
    private Button searchButton;                        //搜索按钮
    @FXML
    private Button searchCustomerButton;                //快速定位患者
    @FXML
    private ToggleGroup selectType;                     //检查类型单选框组
    @FXML
    private TableView<String> mzTypeTable;
    @FXML
    private TableColumn<String, String> mzTypeColumn;
    @FXML
    private TableView<SrvGroupJC> srvGroupJCTable;      //项目列表
    @FXML
    private TableColumn<SrvGroupJC, String> srvGroupJCColumn;
    @FXML
    private ComboBox<String> itemComboBox;              //项目下拉列表框
    @FXML
    private TableView<CustomerQueueJCRecording> customerTableView;        //病人列表
    @FXML
    private TableColumn<CustomerQueueJCRecording, String> serialNumberColumn;
    @FXML
    private TableColumn<CustomerQueueJCRecording, CellLabel> customerNameColumn;
    @FXML
    private TableColumn<CustomerQueueJCRecording, CellLabel> customerTypeColumn;
    @FXML
    private TableColumn<CustomerQueueJCRecording, CellLabel> queueNumberColumn;
    @FXML
    private TableColumn<CustomerQueueJCRecording, CellLabel> itemNameColumn;
    @FXML
    private TableColumn<CustomerQueueJCRecording, CellLabel> statusColumn;
    @FXML
    private TableColumn<CustomerQueueJCRecording, CellLabel> typeColumn;

    private UserInfo userInfo;                          //当前病人的信息
    private ResultInfo<UserInfo> resultInfo;            //接口返回病人信息
    private ObservableList<SrvGroupJC> srvGroupJCS = FXCollections.observableArrayList();
    private SrvGroupJCService srvGroupJCServices = new SrvGroupKCServiceImpl();// 检查服务
    // 检查记录
    private ObservableList<CustomerQueueJCRecording> recordings = FXCollections.observableArrayList();

    private Toast toast = null;

    @FXML
    private void initialize() {
        customerNameField.requestFocus();
        bindEvent();
        showJCData();
        bindComboBox();
        queryQueue("超声检查");
    }

    private void bindComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (SrvGroupJC jc : srvGroupJCS) {
            list.add(jc.getSrvGroupName());
        }
        itemComboBox.setItems(list);
    }

    private void makeToast(String message){
        if (toast==null){
            toast = new Toast(stage, message, 2000, 500, 500);
            toast.doSomething(() -> System.out.println(message));
        } else {
            System.out.println("a");
            toast.closeStage();
            toast = new Toast(stage, message, 2000, 500, 500);
            toast.doSomething(() -> System.out.println(message));
            toast = null;
        }
        System.out.println(toast);
    }

    private void showJCData() {
        mzTypeTable.setItems(FXCollections.observableArrayList("门诊"));
        mzTypeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue()));
        ResultInfo<List<SrvGroupJC>> resultInfo = srvGroupJCServices.getSrvGroupJC();
        if (resultInfo.getStatus() != 1) {
            makeToast(resultInfo.getInformation());
        } else {
            srvGroupJCS.addAll(resultInfo.getT());
            srvGroupJCColumn.setCellValueFactory(cell -> cell.getValue().srvGroupNameProperty());
            srvGroupJCTable.setItems(srvGroupJCS);
        }
    }

    private void bindEvent() {
        //卡号文本输入框回车键
        customerIdTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println("回车键");
                swipeCard(customerIdTextField.getText(), "0");
            }
        });
        //磁卡按钮
        MSCReadButton.setOnMouseClicked(event -> {
            if (customerIdTextField.getText().equals("")) {
                makeToast("磁卡读卡时，患者卡号不能为空。");
            } else {
                swipeCard(customerIdTextField.getText(), "0");
            }
        });
        //社保卡按钮
        SSCReadButton.setOnMouseClicked(event -> {
            swipeCard("-1", "-1");
            verifyInfo(resultInfo);
        });
        //住院号按钮
        HNReadButton.setOnMouseClicked(event -> {
            if (customerIdTextField.getText().equals("")) {
                makeToast("住院号读取，患者住院号不能为空。");
            } else {
                swipeCard(customerIdTextField.getText(), "1");
            }
        });
        //登记按钮
        registerButton.setOnMouseClicked(event -> {
            if (userInfo != null && userInfo.getUserName() != null) {
                SrvGroupJC jc = srvGroupJCTable.getSelectionModel().getSelectedItem();
                register(jc);
            } else {
                UserInfo u = new UserInfo();
                u.setUserName("检查人员");
                u.setId("123456");
                u.setSFZ("123456");
                userInfo = u;
                SrvGroupJC jc = srvGroupJCTable.getSelectionModel().getSelectedItem();
                register(jc);
            }
        });
        //清除信息按钮
        clearButton.setOnMouseClicked(event -> cleanDate());
        //搜索数据
        searchButton.setOnMouseClicked(event -> {
            String str = itemComboBox.getSelectionModel().getSelectedItem();
            queryQueue(str);
        });
        // 查找患者 快速定位
        searchCustomerButton.setOnMouseClicked(event -> {
            Integer scrollIndex = null;
            for (int i = 0; i < recordings.size(); i++) {
                if (recordings.get(i).getCustomerId().equals(customerIdTextField.getText())) {
                    scrollIndex = i;
                }
            }
            if (scrollIndex == null) {
                makeToast("该患者未进行登记。");
            } else {
                customerTableView.scrollTo(scrollIndex);
            }
        });
    }

    private void register(SrvGroupJC jc){
        if (jc != null && jc.getSrvGroupId() != null && jc.getSrvGroupCode() != null) {
            RegisterTask registerTask = new RegisterTask();
            registerTask.setUserInfo(userInfo);
            registerTask.setCustomerType((String) selectType.getSelectedToggle().getUserData());
            registerTask.setSrvGroupJC(jc);
            ProgressFrom progressFrom = new ProgressFrom(registerTask, stage, "数据加载中, 请稍后...");
            progressFrom.activateProgressBar();
            registerTask.valueProperty().addListener(listener -> {
                ResultInfo<CallResult> resultInfo = registerTask.getResultInfo();
                if (resultInfo.getStatus() < 1) {
                    makeToast(resultInfo.getInformation());
                } else {
                    String queueNo = "";
                    String srvGroupName = "";
                    if (("1").equals(selectType.getSelectedToggle().getUserData())) {
                        queueNo = resultInfo.getT().getSrvGroupLetter()+"" + resultInfo.getT().getQueueNo();
                    } else if ((selectType.getSelectedToggle().getUserData()).equals("2")) {
                        queueNo = resultInfo.getT().getSrvGroupLetter() + resultInfo.getT().getQueueNo();
                    }
                    String[] noLetterIds = IniReader.readIniNoLetter();
                    if (noLetterIds != null) {
                        for (String s :noLetterIds){
                            if (s.equals(jc.getSrvGroupId())){
                                srvGroupName = jc.getSrvGroupName();
                                break;
                            }
                        }
                    }
                    TicketPane ticketPane = new TicketPane(queueNo,srvGroupName);
                    ticketPane.closeStage();
                    userInfo = null;
                    customerIdTextField.setText("");
                    customerNameField.setText("");
                }
            });
        } else {
            makeToast("没有选择检查。");
        }
    }

    //清除当前患者的数据
    private void cleanDate() {
        userInfo = null;
        customerNameField.setText("");
        customerIdTextField.setText("");
    }

    private void swipeCard(String idFieldContent, String type) {
        CustomInfoTask customInfoTask = new CustomInfoTask();
        customInfoTask.setType(type);
        customInfoTask.setCardNumber(idFieldContent);
        ProgressFrom progressFrom = new ProgressFrom(customInfoTask, stage, "数据加载中, 请稍后...");
        progressFrom.activateProgressBar();
        customInfoTask.valueProperty().addListener(listener -> {
            resultInfo = customInfoTask.getResultInfo();
            verifyInfo(resultInfo);
        });
    }

    private void verifyInfo(ResultInfo<UserInfo> resultInfo) {
        if (resultInfo.getStatus() < 1) {
            //没有信息或者信息返回有误
            makeToast(resultInfo.getInformation());
        } else {
            userInfo = resultInfo.getT();
            customerNameField.setText(userInfo.getUserName());
        }
    }

    private void queryQueue(String str) {
        AsyncService asyncService = new AsyncService(() -> {
            ResultInfo<List<CustomerQueueJCRecording>> resultInfo = srvGroupJCServices.getCustomerQueueList(str);
            if (resultInfo.getStatus() < 1) {
                makeToast(resultInfo.getInformation());
            } else {
                recordings.remove(0, recordings.size());
                recordings.addAll(resultInfo.getT());
                bindTableView();
            }
        });
        asyncService.start();
    }

    private void bindTableView() {
        // 序号
        serialNumberColumn.setCellFactory((col) -> new TableCell<CustomerQueueJCRecording, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    int rowIndex = getIndex() + 1;
                    setText(String.valueOf(rowIndex));
                }
            }
        });
        customerNameColumn.setCellValueFactory(cell -> {
            CellLabel cellLabel = new CellLabel(cell.getValue(), cell.getValue().getCustomerName());
            return new SimpleObjectProperty<>(cellLabel);
        });
        customerTypeColumn.setCellValueFactory(cell -> {
            CellLabel cellLabel = new CellLabel(cell.getValue(), cell.getValue().getCustomerTypeDesc());
            return new SimpleObjectProperty<>(cellLabel);
        });
        queueNumberColumn.setCellValueFactory(cell -> {
            CellLabel cellLabel = new CellLabel(cell.getValue(), cell.getValue().getSrvGroupLetter()+cell.getValue().getQueueNo());
            return new SimpleObjectProperty<>(cellLabel);
        });
        itemNameColumn.setCellValueFactory(cell -> {
            CellLabel cellLabel = new CellLabel(cell.getValue(), cell.getValue().getSrvGroupName());
            return new SimpleObjectProperty<>(cellLabel);
        });
        statusColumn.setCellValueFactory(cell -> {
            CellLabel cellLabel = new CellLabel(cell.getValue(), cell.getValue().getStatusTypeDesc());
            return new SimpleObjectProperty<>(cellLabel);
        });
        typeColumn.setCellValueFactory(cell -> {
            CellLabel cellLabel = new CellLabel(cell.getValue(), cell.getValue().getSrvCodeName());
            return new SimpleObjectProperty<>(cellLabel);
        });
        customerTableView.setItems(recordings);
        final ContextMenu cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("补打号票");
        MenuItem cmItem2 = new MenuItem("过号激活");
        cm.getItems().addAll(cmItem1, cmItem2);
        cmItem1.setOnAction(event -> {
            CustomerQueueJCRecording c = customerTableView.getSelectionModel().getSelectedItem();
            String queueNo = "";
            String srvGroupName = "";
            String[] ids = IniReader.readIniNoLetter();
            if (ids!=null){
                for (String s:ids) {
                    if (c.getSrvGroupId().equals(s)){
                        c.setSrvGroupLetter("");
                    }
                }
            }
            if (c.getCustomerType().equals("1")) {
                queueNo =c.getSrvGroupLetter()+c.getQueueNo();
            } else if (c.getCustomerType().equals("2")) {
                queueNo = c.getSrvGroupLetter() + c.getQueueNo();
            }
            String[] noLetterIds = IniReader.readIniNoLetter();
            if (noLetterIds != null) {
                for (String s :noLetterIds){
                    if (s.equals(c.getSrvGroupId())){
                        srvGroupName = c.getSrvGroupName();
                        break;
                    }
                }
            }
            TicketPane ticketPane = new TicketPane(queueNo,srvGroupName);
            ticketPane.closeStage();
        });
        cmItem2.setOnAction(event -> {
            CustomerQueueJCRecording customerQueueJCRecording = customerTableView.getSelectionModel().getSelectedItem();
            ResultInfo<Integer> resultInfo = srvGroupJCServices.restartCallStatement(customerQueueJCRecording);
            if (resultInfo.getStatus()!=1){
                LogUtil.markLog(0,resultInfo.getInformation());
                makeToast(resultInfo.getInformation());
            } else {
                LogUtil.markLog(1,customerQueueJCRecording.getCustomerName()+"已经过号激活。");
            }
        });
        customerTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                cm.show(customerTableView, event.getScreenX(), event.getScreenY());
            }
        });
    }

}
