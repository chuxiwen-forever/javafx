package com.liu.controller;

import com.liu.Application;
import com.liu.entity.Common;
import com.liu.entity.TextPaper;
import com.liu.entity.User;
import com.liu.mapper.CommonMapper;
import com.liu.mapper.TextPaperMapper;
import com.liu.mapper.UserMapper;
import com.liu.util.MessageUtil;
import com.liu.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class RootController implements Initializable {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TextPaperMapper textPaperMapper;
    @Autowired
    private CommonMapper commonMapper;

    private ObservableList<Object> list1= FXCollections.observableArrayList();
    private ObservableList<Object> list2= FXCollections.observableArrayList();
    private ObservableList<Object> list3= FXCollections.observableArrayList();
//    第一个tab的组件
    public TableColumn o_text_name1;
    public TableColumn o_text_teacher1;
    public TextField o_addTextName;
    public TextField o_addTeName;
    public TextField o_delTextF;
    public TextField o_teacherName;
    public TableView table1;
//    第二个tab的组件
    public TableView table2;
    public TableColumn o_stu_name1;
    public TableColumn o_stu_sex1;
    public TableColumn o_stu_num1;
    public TextField o_stuId;
//   第三个tab的组件
    public TableView table3;
    public TableColumn o_stu_num2;
    public TableColumn o_stu_name2;
    public TableColumn o_text_name2;
    public TableColumn o_text_teacher2;
    public TableColumn o_stu_grade;
    public TextField o_stuId2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        location.getPath();
        //第一个tab
        List<TextPaper> textPapers = textPaperMapper.selectAllTextPaper();
        for (TextPaper textPaper:textPapers) {
            list1.add(textPaper);
        }
        o_text_name1.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        o_text_teacher1.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        table1.setItems(list1);
        //第二个tab
        List<User> users = userMapper.selectAllUser();
        for (User user:users) {
            list2.add(user);
        }
        o_stu_name1.setCellValueFactory(new PropertyValueFactory<>("u_username"));
        o_stu_num1.setCellValueFactory(new PropertyValueFactory<>("u_number"));
        o_stu_sex1.setCellValueFactory(new PropertyValueFactory<>("u_sex"));
        table2.setItems(list2);
        //第三个tab
        List<Common> commons = commonMapper.selectAllMessage();
        for (Common common:commons) {
            list3.add(common);
        }
        o_stu_num2.setCellValueFactory(new PropertyValueFactory<>("u_number"));
        o_stu_name2.setCellValueFactory(new PropertyValueFactory<>("u_username"));
        o_text_name2.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        o_text_teacher2.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        o_stu_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        table3.setItems(list3);
    }

    public void selText(){
        String teacherName = o_teacherName.getText();
        List<TextPaper> textPapers = textPaperMapper.selectAllTextPaperByName(teacherName);
        list1.clear();
        for (TextPaper textPaper:textPapers) {
            list1.add(textPaper);
        }
        o_text_name1.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        o_text_teacher1.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        table1.setItems(list1);
        o_teacherName.setText("");
    }
    public void delText(){
        String delF = o_delTextF.getText();
        int i = textPaperMapper.delTextPaper(delF);
        if(i!=0){
            o_delTextF.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("恭喜！");
            alert.setHeaderText("试卷删除成功！");
            alert.setContentText("");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！");
            alert.setHeaderText("试卷不存在！");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    public void addText(){
        String textName = o_addTextName.getText();
        String teacherName = o_addTeName.getText();
        if(MessageUtil.RegisterMessageByUsername(textName)&&MessageUtil.RegisterMessageByUsername(teacherName)){
            int i = textPaperMapper.insertTextPaper(new TextPaper(textName, teacherName));
            if(i!=0){
                o_addTeName.setText("");
                o_addTextName.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("恭喜！");
                alert.setHeaderText("试卷添加成功！");
                alert.setContentText("");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！");
            alert.setHeaderText("试卷添加方式不正确!");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    public void selStu1(){
        String user = o_stuId.getText();
        if(MessageUtil.RegisterMessageByNumber(user)){
            if(user!=null){
                User user1 = userMapper.selectAllUserById(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("查询成功！");
                alert.setHeaderText(user1.getU_number()+"信息如下:");
                alert.setContentText("姓名:"+user1.getU_username()+"\n" +
                        "性别:"+user1.getU_sex()+"\n");
                alert.showAndWait();
                o_stuId.setText("");
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("查询失败！");
                alert.setHeaderText("查无此人");
                alert.setContentText("");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！");
            alert.setHeaderText("请输入正确的学号格式");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    public void selStu2(){
        String StudentNum = o_stuId2.getText();
        if(MessageUtil.RegisterMessageByNumber(StudentNum)){
            List<Common> commons = commonMapper.selectMessageById(StudentNum);
            list3.clear();
            for (Common common1:commons) {
                list3.add(common1);
            }
            o_stu_num2.setCellValueFactory(new PropertyValueFactory<>("u_number"));
            o_stu_name2.setCellValueFactory(new PropertyValueFactory<>("u_username"));
            o_text_name2.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
            o_text_teacher2.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
            o_stu_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
            table3.setItems(list3);
            o_stuId2.setText("");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！");
            alert.setHeaderText("请输入正确的学号格式");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    public void eveBegin(){
        list1.clear();
        list2.clear();
        list3.clear();
        //第一个tab
        List<TextPaper> textPapers = textPaperMapper.selectAllTextPaper();
        for (TextPaper textPaper:textPapers) {
            list1.add(textPaper);
        }
        o_text_name1.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        o_text_teacher1.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        table1.setItems(list1);
        //第二个tab
        List<User> users = userMapper.selectAllUser();
        for (User user:users) {
            list2.add(user);
        }
        o_stu_name1.setCellValueFactory(new PropertyValueFactory<>("u_username"));
        o_stu_num1.setCellValueFactory(new PropertyValueFactory<>("u_number"));
        o_stu_sex1.setCellValueFactory(new PropertyValueFactory<>("u_sex"));
        table2.setItems(list2);
        //第三个tab
        List<Common> commons = commonMapper.selectAllMessage();
        for (Common common:commons) {
            list3.add(common);
        }
        o_stu_num2.setCellValueFactory(new PropertyValueFactory<>("u_number"));
        o_stu_name2.setCellValueFactory(new PropertyValueFactory<>("u_username"));
        o_text_name2.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        o_text_teacher2.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        o_stu_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        table3.setItems(list3);
    }
    public void reMain(){
        Application.showView(MainView.class);
    }
}
