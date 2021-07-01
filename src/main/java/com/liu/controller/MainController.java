package com.liu.controller;

import com.liu.Application;
import com.liu.entity.User;
import com.liu.mapper.UserMapper;
import com.liu.util.GetMassage;
import com.liu.view.LoginView;
import com.liu.view.RegisterView;
import com.liu.view.RootView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 主界面控制器
 *
 * @author westinyang
 * @date 2019/4/23 2:01
 */
@Slf4j
@FXMLController
public class MainController implements Initializable {

    @Autowired
    private UserMapper userMapper;

    // 主容器
    public Pane rootPane;

    public Button m_button;

    public PasswordField m_password;

    public TextField m_username;

    public Label m_register;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("initialize: {}", location.getPath());
    }

    public void login(ActionEvent actionEvent){
        String username = m_username.getText();
        String password = m_password.getText();
        if(username.equals("root")&&password.equals("123456"))
        {
            Application.showView(RootView.class);
        }else{
            User user = userMapper.selectUserByIf(username,password);
            if(user!=null){
                GetMassage.setUsername(username);
                Application.showView(LoginView.class);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告！");
                alert.setHeaderText("无法登录");
                alert.setContentText("姓名或密码错误!!!");
                alert.showAndWait();
            }
        }
        m_username.setText("");
        m_password.setText("");

    }

    public void register(MouseEvent mouseEvent){
        Application.showView(RegisterView.class);
    }
}
