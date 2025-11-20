package com.ZUNr1.ui.view;

import com.ZUNr1.enums.Afflatus;
import com.ZUNr1.model.Characters;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CharacterCard extends VBox {
    private final Characters character;
    public CharacterCard(Characters character){
        this.character = character;
        initializeUi();
        setupInteraction();
    }
    private void initializeUi(){
        setStyle("-fx-background-color: #f5f5f5; " +
                "-fx-border-color: #ddd; -fx-border-width: 1px; " +
                "-fx-padding: 10px; -fx-spacing: 5px;");
        setPrefSize(150, 120);
        addBasicInformation();
    }

    private Color getRarityColor(int rarity) {
        switch (rarity) {
            case 6: return Color.web("#FF7F00"); // 6星 = 橙色
            case 5: return Color.web("#FFD700"); // 5星 = 金色
            case 4: return Color.web("#9B30FF"); // 4星 = 紫色
            case 3: return Color.web("#1E90FF"); // 3星 = 蓝色
            case 2: return Color.web("#32CD32"); // 2星 = 绿色
            default: return Color.GRAY;
        }
    }
    private Color getAfflatusColor(Afflatus afflatus){
        switch (afflatus) {
            case STAR -> {
                return Color.rgb(48,64,88);
            }
            case MINERAL -> {
                return Color.rgb(86,70,38);
            }
            case BEAST -> {
                return Color.rgb(122,60,59);
            }
            case PLANT -> {
                return Color.rgb(57,100,61);
            }
            case SPIRIT -> {
                return Color.rgb(101,62,112);
            }
            case INTELLECT -> {
                return Color.rgb(129,121,66);
            }default -> {
                return Color.rgb(255,255,255);
            }
        }
    }
    private void addBasicInformation(){
        Label nameLabel = new Label(character.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label enNameLabel = new Label(character.getEnName());
        enNameLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

        Label rarityLabel = new Label("★".repeat(character.getRarity()));
        //字符串的repeat方法，是重复几次的意思，括号里面放重复的数
        rarityLabel.setTextFill(getRarityColor(character.getRarity()));
        //设置显示的颜色，setTextFill就是设置颜色Color的
        Text afflatusText = new Text("灵感类型：");
        Text afflatusNameText = new Text(character.getAfflatus().getChineseName());
        afflatusNameText.setFill(getAfflatusColor(character.getAfflatus()));
        //设置颜色
        TextFlow afflatusFlow = new TextFlow(afflatusText, afflatusNameText);
        //TextFlow可以连接几个Text，这里不使用Label，因为我们要设置后面的颜色而不修改前面的颜色
        String damageTypeName = character.getDamageType().getChineseName();
        Label damageTypeLabel = new Label("创伤类型：" + damageTypeName);
        String genderName = character.getGender().getChineseName();
        Label genderLabel = new Label("性别：" + genderName);

        Label creatorLabel = new Label(character.getCreator());
        getChildren().addAll(nameLabel,enNameLabel,rarityLabel,afflatusFlow,damageTypeLabel,genderLabel,creatorLabel);
        if (character.isCustom()) {
            Label customLabel = new Label("✎ 自制角色");
            customLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #E91E63; -fx-font-weight: bold;");
            getChildren().add(customLabel);
        }
    }

    private void setupInteraction(){
        setOnMouseEntered(mouseEvent -> {
            setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #999; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 8px;");
        });
        setOnMouseExited(mouseEvent -> {
            setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 8px;");
        });
        setOnMouseClicked(mouseEvent -> {

        });
    }

    public Characters getCharacter() {
        return character;
    }
}
