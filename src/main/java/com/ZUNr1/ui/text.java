package com.ZUNr1.ui;

import com.ZUNr1.enums.Afflatus;
import com.ZUNr1.enums.DamageType;
import com.ZUNr1.enums.Gender;
import com.ZUNr1.model.Characters;
import com.ZUNr1.ui.view.CharacterCard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class text extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Characters testCharacter = new Characters.CharactersBuilder("001", "星锑", "Sputnik", Gender.WOMAN, true, "ZUNr1")
                .afflatus(Afflatus.STAR)
                .damageType(DamageType.REALITY)
                .rarity(6)
                .build();

// 创建卡片
        CharacterCard card = new CharacterCard(testCharacter);

// 添加到场景中测试
        StackPane root = new StackPane(card);
        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
