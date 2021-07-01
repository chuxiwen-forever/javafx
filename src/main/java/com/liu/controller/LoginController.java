package com.liu.controller;

import com.liu.entity.Grade;
import com.liu.entity.MadeGrade;
import com.liu.entity.TextPaper;
import com.liu.mapper.GradeMapper;
import com.liu.mapper.TextPaperMapper;
import com.liu.mapper.UserMapper;
import com.liu.util.GetMassage;
import com.liu.util.MessageUtil;
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
public class LoginController implements Initializable {

    private ObservableList<Object> list1= FXCollections.observableArrayList();
    private ObservableList<Object>list2=FXCollections.observableArrayList();

    @Autowired
    private TextPaperMapper textPaperMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GradeMapper gradeMapper;

//    第一个lab
    public Label reName;
    public TableView n_table1;
    public TableColumn n_column1;
    public TableColumn n_column2;
    public TextField n_text1;
    public TextField n_text2;
//    第二个lab
    public TableView n_table2;
    public TableColumn n_column3;
    public TableColumn n_column4;
    public TableColumn n_column5;

//    第三个lab
    public PasswordField n_pass1;
    public PasswordField n_pass2;
    public PasswordField n_pass3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        location.getPath();
        reName.setText(GetMassage.getUsername());
        List<TextPaper> textPapers = textPaperMapper.selectAllTextPaper();
        for (TextPaper textPaper:textPapers) {
            list1.add(textPaper);
        }
        n_column1.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        n_column2.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        n_table1.setItems(list1);
        List<Grade> grades = gradeMapper.selectAllGrade(GetMassage.getUsername());
        for (Grade grade:grades) {
            list2.add(grade);
        }
        n_column3.setCellValueFactory(new PropertyValueFactory<>("t_paperName"));
        n_column4.setCellValueFactory(new PropertyValueFactory<>("t_teacher"));
        n_column5.setCellValueFactory(new PropertyValueFactory<>("grade"));
        n_table2.setItems(list2);
    }
    public void comeText(){
        Integer t_id=textPaperMapper.selectIdByName(n_text1.getText());
        Grade grade = gradeMapper.selectBeTrue(GetMassage.getUsername(), n_text1.getText());
        if(grade==null){
            if(t_id==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！！");
                alert.setHeaderText("你输入的卷子不存在！！！");
                alert.setContentText("");
                alert.showAndWait();
            }else{
                if(!MessageUtil.RegisterMessage(n_text2.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("警告！！");
                    alert.setHeaderText("分数有误！！！");
                    alert.setContentText("");
                    alert.showAndWait();
                }else{
                    int i = gradeMapper.insertGrade(new MadeGrade(
                            t_id,
                            userMapper.selectIdByUsername(GetMassage.getUsername()),
                            Integer.parseInt(n_text2.getText())
                    ));
                    if(i!=0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("恭喜你！！");
                        alert.setHeaderText("考试结束！！！");
                        alert.setContentText("你本次的成绩是"+n_text2.getText()+"\n重新登录系统可以刷新成绩");
                        alert.showAndWait();
                    }
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！！");
            alert.setHeaderText("这场考试你已经考过了!!");
            alert.setContentText("这场你的分数是"+grade.getGrade());
            alert.showAndWait();
        }
        n_text1.setText("");
        n_text2.setText("");
    }

    public void rePassword(){
        String s = userMapper.selectPasswordByUsername(GetMassage.getUsername());
        if(s.equals(n_pass1.getText())){
            if(!MessageUtil.RegisterMessageByPassword(n_pass2.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！！");
                alert.setHeaderText("密码格式不对！！！");
                alert.setContentText("");
                alert.showAndWait();
            }
            else {
                if(n_pass2.getText().equals(n_pass3.getText())){
                    int i = userMapper.updatePassword(GetMassage.getUsername(), n_pass2.getText());
                    if(i==1){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("恭喜！！");
                        alert.setHeaderText("密码修改成功！！！");
                        alert.setContentText("");
                        alert.showAndWait();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("警告！！");
                    alert.setHeaderText("两次密码不一致！！！");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！！");
            alert.setHeaderText("原密码有误！！！");
            alert.setContentText("");
            alert.showAndWait();
        }
        n_pass1.setText("");
        n_pass2.setText("");
        n_pass3.setText("");
    }
}
