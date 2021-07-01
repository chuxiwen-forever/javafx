package com.liu.controller;

import com.liu.Application;
import com.liu.entity.User;
import com.liu.mapper.UserMapper;
import com.liu.util.MessageUtil;
import com.liu.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@FXMLController
public class RegisterController implements Initializable {

    @Autowired
    private UserMapper userMapper;

    public TextField r_username;
    public TextField r_number;
    public RadioButton r_sex1;
    public RadioButton r_sex2;
    public PasswordField r_password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        location.getPath();
        r_username.setPromptText("输入你的名字");
        r_number.setPromptText("输入你的学号：12位");
        r_password.setPromptText("输入你的密码,10<长度<30");
    }

    public void registerAction(){

        String StuNum = r_number.getText();
        String StuName = r_username.getText();
        String StuSex = null;
        String StuPassword = r_password.getText();

        boolean selected = r_sex1.isSelected();
        if(selected==true)StuSex="男";
        else StuSex="女";

        User user = userMapper.selectAllUserById(r_number.getText());
        if(user!=null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告！");
            alert.setHeaderText("该学号已经被注册！");
            alert.setContentText("");
            alert.showAndWait();
        }else{
            if(!MessageUtil.RegisterMessageByNumber(StuNum)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！");
                alert.setHeaderText("学号不符合要求");
                alert.setContentText("12位数字!!");
                alert.showAndWait();
            }else if(!MessageUtil.RegisterMessageByUsername(StuName)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！");
                alert.setHeaderText("姓名不符合要求");
                alert.setContentText("姓名不能为空且不能只输入空格!!");
                alert.showAndWait();
            }else if(!MessageUtil.RegisterMessageByPassword(StuPassword)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！");
                alert.setHeaderText("密码不符合要求");
                alert.setContentText("密码格式大于10小于30！！");
                alert.showAndWait();
            }else if(StuSex==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！");
                alert.setHeaderText("请选择你的性别!");
                alert.setContentText("");
                alert.showAndWait();
            }else{
                int i = userMapper.insertUser(new User(StuName, StuPassword, StuSex, StuNum));
                if(i!=0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("恭喜");
                    alert.setHeaderText("注册成功");
                    alert.setContentText("");
                    alert.showAndWait();
                    //回到主页面
                    Application.showView(MainView.class);
                }
            }
        }

        r_number.setText("");
        r_password.setText("");
        r_username.setText("");
    }
    public void reMain(){
        Application.showView(MainView.class);
        r_number.setText("");
        r_password.setText("");
        r_username.setText("");
    }
}
