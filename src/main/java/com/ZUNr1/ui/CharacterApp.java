package com.ZUNr1.ui;

import com.ZUNr1.ui.controller.CharacterChangeController;
import com.ZUNr1.ui.controller.CharacterMainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class CharacterApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CharacterChangeController controller = new CharacterChangeController(stage);
        Scene scene = new Scene(controller.getRoot(), 800, 600);
        stage.setTitle("角色管理");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            // 设置窗口关闭事件监听器
            // 当用户点击窗口的X按钮时，会触发这个事件
            event.consume();
            //这是阻止事件的默认执行，默认是关闭程序，我们阻止，然后才能运行后面的代码显示提示框
            showExitConfirmation();
            //这是执行操作，我们在里面实现显示提示框与关闭程序
        });
        stage.show();
    }
    private void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("退出程序");
        alert.setHeaderText("真的要离开我吗 😢");
        alert.setContentText("是否要退出程序？未保存的数据将会丢失。");

        // 普通按钮版本（防止误按Enter）
        ButtonType exitButton = new ButtonType("狠心离开");
        ButtonType stayButton = new ButtonType("再陪陪你");
        alert.getButtonTypes().setAll(exitButton, stayButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == exitButton) {
            Platform.exit(); // 退出程序
        }
        // 如果点击"再陪陪你"或关闭对话框，什么都不做（窗口保持打开）
    }

    private Parent createBasicInterface() {
        return new VBox();
    }
}
