package com.ZUNr1.ui.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CharacterChangeController {
    private Stage stage;
    private VBox root;
    private CharacterViewController characterViewController;
    private CharacterMainController characterMainController;
    public CharacterChangeController(Stage stage){
        this.characterMainController = new CharacterMainController();
        this.characterViewController = new CharacterViewController();
        this.root = new VBox();
        this.stage = stage;
        createRoot();
    }
    private void createRoot(){
        Button button1 = new Button("角色查看");
        Button button2 = new Button("角色录入");
        button1.setOnAction(actionEvent -> {
            // 创建一个新的 Scene 来包装 CharacterListView
            Scene characterListScene = new Scene(characterViewController.getListView().getRoot(),800,600);
            stage.setScene(characterListScene);
            //characterViewController.getListView().getRoot().getScene() 不可以使用
            //当一个布局Pane没有被添加到Scene中，使用getScene会获得null，因为没有Scene
        });
        button2.setOnAction(actionEvent -> {
            Scene mainScene = new Scene(characterMainController.getRoot(),800,600);
            stage.setScene(mainScene);
        });
        root.getChildren().addAll(button1,button2);
    }

    public VBox getRoot() {
        return root;
    }
}
