package com.ZUNr1.ui.controller;

import com.ZUNr1.model.Characters;
import com.ZUNr1.service.CharacterConverter;
import com.ZUNr1.service.CharacterStorageService;
import com.ZUNr1.ui.service.CharacterDataService;
import com.ZUNr1.ui.service.CharacterFormData;
import com.ZUNr1.ui.validation.ValidationResult;
import com.ZUNr1.ui.tab.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Optional;

public class CharacterMainController {
    private BorderPane root;
    private BasicInformationTab basicInformationTab;
    private AttributesInformationTab attributesInformationTab;
    private ProgressionInformationTab progressionInformationTab;
    private UsedTermInformationTab usedTermInformationTab;
    private EuphoriaInformationTab euphoriaInformationTab;
    private SkillInformationTab skillInformationTab;
    private OtherInformationTab otherInformationTab;
    private CharacterFormData formData;

    private final CharacterStorageService storageService = new CharacterStorageService();

    public CharacterMainController(){
        this.formData = new CharacterFormData();
        this.basicInformationTab = new BasicInformationTab(formData);
        this.attributesInformationTab = new AttributesInformationTab(formData);
        this.progressionInformationTab = new ProgressionInformationTab(formData);
        this.usedTermInformationTab = new UsedTermInformationTab(formData);
        this.euphoriaInformationTab = new EuphoriaInformationTab(formData);
        this.skillInformationTab = new SkillInformationTab(formData);
        this.otherInformationTab = new OtherInformationTab(formData);
        createInterface();
    }

    private void createInterface(){
        root = new BorderPane();

        //中心标题
        Label titleLabel = new Label("角色信息录入系统");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 20px;");
        root.setTop(titleLabel);

        //选项卡，包含很多Tab标签选项
        TabPane tabPane = new TabPane();

        //角色基本信息
        Tab basicInformationTab = new Tab("角色基本信息");
        basicInformationTab.setContent(this.basicInformationTab.createTab());
        //设置内容
        basicInformationTab.setClosable(false);
        //这个可以把Tab标签页设为不可关闭，去掉x，防止不小心关了
        //包含id，name，稀有度，灵感类型，创伤类型，性别，
        Tab skillInformationTab = new Tab("神秘术信息");
        skillInformationTab.setContent(this.skillInformationTab.createTab());
        skillInformationTab.setClosable(false);
        //包含技能
        Tab attributesInformationTab = new Tab("属性信息（默认满级）");
        attributesInformationTab.setContent(this.attributesInformationTab.createTab());
        attributesInformationTab.setClosable(false);
        //包含属性
        Tab progressionInformationTab = new Tab("塑造与传承");
        progressionInformationTab.setContent(this.progressionInformationTab.createTab());
        progressionInformationTab.setClosable(false);
        //包含Portrait和Inheritance
        Tab usedTermInformationTab = new Tab("专有名词");
        usedTermInformationTab.setContent(this.usedTermInformationTab.createTab());
        usedTermInformationTab.setClosable(false);
        //包含usedTerm
        Tab euphoriaInformationTab = new Tab("狂想");
        euphoriaInformationTab.setContent(this.euphoriaInformationTab.createTab());
        euphoriaInformationTab.setClosable(false);
        Tab otherInformationTab = new Tab("其他信息");
        otherInformationTab.setContent(this.otherInformationTab.createTab());
        otherInformationTab.setClosable(false);
        tabPane.getTabs().addAll
                (basicInformationTab,skillInformationTab,attributesInformationTab,
                        progressionInformationTab,usedTermInformationTab,
                        euphoriaInformationTab,otherInformationTab);
        //这一行获得所有标签然后添加所有我们要加的标签
        root.setCenter(tabPane);

        HBox buttonBox = createButtonBox();
        root.setBottom(buttonBox);

    }


    private HBox createButtonBox(){
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(15));
        buttonBox.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 1 0 0 0;");
        buttonBox.setAlignment(Pos.CENTER);
        //setAlignment() 方法用于设置容器内所有子元素的对齐方式
        //Pos.CENTER 是一个常量，表示居中对齐
        Button confirmButton = new Button("确认录入");
        confirmButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 16;");
        confirmButton.setOnAction(actionEvent -> confirmInput());

        Button clearButton = new Button("重新开始");
        clearButton.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 16;");
        clearButton.setOnAction(actionEvent -> createNewCharacter());

        buttonBox.getChildren().addAll(confirmButton,clearButton);
        return buttonBox;
    }
    private void confirmInput(){
        // 1. 先收集数据到formData
        try {
            CharacterDataService dataService = new CharacterDataService();
            dataService.populateFormDataFromUI(formData, basicInformationTab, attributesInformationTab,
                    skillInformationTab, progressionInformationTab,
                    usedTermInformationTab, euphoriaInformationTab,
                    otherInformationTab);
        } catch (Exception e) {
            showAlert("数据错误", "数据收集失败: " + e.getMessage(), Alert.AlertType.ERROR);
            return;
        }
        //再进行验证
        ValidationResult result = validateAllTabs();
        if (!result.isValid()) {
            showValidationAlert(result.getMessage());
            if (result.getFocusNode() != null) {
                result.getFocusNode().requestFocus();
            }
            return;
        }

        String duplicateError = checkDuplicateCharacter();
        if (duplicateError != null) {
            showAlert("重复角色", duplicateError, Alert.AlertType.ERROR);
            return;
        }
        if (!showSaveConfirmation()) {
            return;
        }
        saveForCharacterData();

    }
    private String checkDuplicateCharacter() {//直接检查，过了才进行转换
        String id = formData.getId();
        String name = formData.getName();
        String enName = formData.getEnName();

        return storageService.checkDuplicateCharacter(id, name, enName);
    }
    private boolean showSaveConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认保存");
        alert.setHeaderText("确认保存角色信息");
        alert.setContentText("确定要保存这个角色吗？");

        ButtonType saveButton = new ButtonType("确认保存", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("再检查一下", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(saveButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == saveButton;//按下按钮，同时获得的是save，排除直接关掉对话框的情况
    }
    private void saveForCharacterData(){
        try{
            CharacterDataService dataService = new CharacterDataService();
            dataService.populateFormDataFromUI(formData,basicInformationTab,attributesInformationTab,
                    skillInformationTab,progressionInformationTab,
                    usedTermInformationTab,euphoriaInformationTab,
                    otherInformationTab);
            Characters character = CharacterConverter.convertToCharacter(formData);
            storageService.saveCharacter(character);
            showSaveSuccessAlert(character.getName());


        }catch (Exception e){
            showAlert("保存失败", "保存角色时出现错误: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    private void showSaveSuccessAlert(String characterName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("保存成功");
        alert.setHeaderText("角色保存成功！");
        alert.setContentText("角色 '" + characterName + "' 已成功保存到图鉴中。");

        ButtonType newCharacterButton = new ButtonType("继续添加", ButtonBar.ButtonData.OK_DONE);
        ButtonType finishButton = new ButtonType("完成", ButtonBar.ButtonData.FINISH);
        alert.getButtonTypes().setAll(newCharacterButton, finishButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == newCharacterButton) {
                createNewCharacter();
            } else {
                System.out.println("角色保存完成");
            }
        }
    }
    private ValidationResult validateAllTabs() {
        ValidationResult result;
        //这样写是可以的，我们没有公开构造器，而且这样写后续马上附上值，没有问题
        result = basicInformationTab.validate();
        if (!result.isValid()) {
            return result;
        }
        result = attributesInformationTab.validate();
        if (!result.isValid()) {
            return result;
        }
        result = skillInformationTab.validate();
        if (!result.isValid()) {
            return result;
        }
        result = progressionInformationTab.validate();
        if (!result.isValid()) {
            return result;
        }
        return ValidationResult.success();
    }
    private void showValidationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("输入验证");
        alert.setHeaderText("请完善信息");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void createNewCharacter(){
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //一个确认类型的对话框Alert
            alert.setTitle("新建角色");
            alert.setHeaderText("开始新的角色编辑");
            alert.setContentText("确定要开始编辑新角色吗？当前窗口的所有输入将被重置。");
            // 先获取默认的按钮类型
            ButtonType defaultOkButton = ButtonType.OK;
            ButtonType defaultCancelButton = ButtonType.CANCEL;
            // 移除默认按钮
            alert.getButtonTypes().removeAll(defaultOkButton, defaultCancelButton);
            //我们选择使用新的按钮，因为默认按钮不能做到按enter键确定
            ButtonType newCharacterButton = new ButtonType("新建角色", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("我手滑了", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().addAll(newCharacterButton, cancelButton);
            //按 Enter 键默认会触发：
            //具有 ButtonData.OK_DONE、ButtonData.YES、ButtonData.FINISH 类型的按钮
            //如果没有上述按钮，则触发第一个定义的按钮
            //OK_DONE, YES - 通常放在右侧，表示确认   CANCEL_CLOSE, NO - 通常放在左侧，表示取消
            //APPLY, FINISH - 应用或完成   HELP - 帮助按钮
            //LEFT, RIGHT - 控制左右位置   BIG_GAP, SMALL_GAP - 添加间距
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == newCharacterButton) {
                //检查用户是否做出了选择（不是直接关闭对话框）同时点击了"确定"按钮
                reuseCurrentWindows();
            }
        } catch (Exception e) {
            showAlert("错误", "重新开始失败: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    private void reuseCurrentWindows(){
        Stage currentStage = (Stage) root.getScene().getWindow();
        //getScene() 返回这个组件所在的 Scene 对象(CharacterApp类里面的scene)
        //scene.getWindow() 获取包含这个 Scene 的 Window
        // 我们使用Stage是因为 Window 只有基础功能 Stage 有更多控制方法，这样我们可以对窗口本身进行调整
        double currentWidth = currentStage.getWidth();
        double currentHeight = currentStage.getHeight();
        //获取窗口的长度和宽度
        double currentX = currentStage.getX();
        double currentY = currentStage.getY();
        //获得窗口在界面的x，y坐标，这样可以原位置新建窗口
        boolean isMaximized = currentStage.isMaximized();
        //窗口是否最大化
        CharacterMainController newController = new CharacterMainController();
        Scene newScene = new Scene(newController.getRoot(),currentWidth,currentHeight);//创建新场景
        currentStage.setScene(newScene);//把当前窗口的场景变为新的
        currentStage.setTitle("新建角色 - 角色信息录入系统");

        // 恢复窗口位置和大小（如果不是最大化状态）
        if (!isMaximized) {
            currentStage.setX(currentX);
            currentStage.setY(currentY);
            currentStage.setWidth(currentWidth);
            currentStage.setHeight(currentHeight);
        }
        showAlert("提示", "已开始编辑新角色", Alert.AlertType.INFORMATION);


    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        //写这个方法主要是为了方便新建提示框
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public BorderPane getRoot() {
        return root;
    }

    public TextField getCreatorField() {
        return basicInformationTab.getCreatorField();
    }

    public TextField getIdField() {
        return basicInformationTab.getIdField();
    }

    public TextField getNameField() {
        return basicInformationTab.getNameField();
    }

    public TextField getEnNameField() {
        return basicInformationTab.getEnNameField();
    }

    public ComboBox<String> getAfflatusComboBox() {
        return basicInformationTab.getAfflatusComboBox();
    }

    public ComboBox<String> getDamageTypeComboBox() {
        return basicInformationTab.getDamageTypeComboBox();
    }

    public ComboBox<String> getGenderComboBox() {
        return basicInformationTab.getGenderComboBox();
    }

    public Spinner<Integer> getRaritySpinner() {
        return basicInformationTab.getRaritySpinner();
    }

    public TextField getAttackField() {
        return attributesInformationTab.getAttackField();
    }

    public TextField getHealthField() {
        return attributesInformationTab.getHealthField();
    }

    public TextField getRealityDefenseField() {
        return attributesInformationTab.getRealityDefenseField();
    }

    public TextField getMentalDefenseField() {
        return attributesInformationTab.getMentalDefenseField();
    }

    public TextField getTechniqueField() {
        return attributesInformationTab.getTechniqueField();
    }

    public Map<String, TextField> getSkillNameFields() {
        return skillInformationTab.getSkillNameFields();
    }

    public Map<String, Map<String, TextArea>> getSkillDescribeFields() {
        return skillInformationTab.getSkillDescribeFields();
    }

    public Map<String, Map<String, TextArea>> getSkillStoryFields() {
        return skillInformationTab.getSkillStoryFields();
    }

    public Map<String, Map<String, ComboBox<String>>> getSkillTypeFields() {
        return skillInformationTab.getSkillTypeFields();
    }

    public Map<String, TextArea> getInheritanceFields() {
        return progressionInformationTab.getInheritanceFields();
    }

    public Map<String, TextArea> getPortraitFields() {
        return progressionInformationTab.getPortraitFields();
    }

    public Map<String, TextField> getUsedTermNameFields() {
        return usedTermInformationTab.getUsedTermNameFields();
    }

    public Map<String, TextArea> getUsedTermDescribeFields() {
        return usedTermInformationTab.getUsedTermDescribeFields();
    }
    public Map<String, TextArea> getCharacterCoverInformationFields() {
        return otherInformationTab.getCharacterCoverInformationFields();
    }

    public Map<String, TextField> getDressNameFields() {
        return otherInformationTab.getDressNameFields();
    }

    public Map<String, Map<String, TextArea>> getCharacterItemsFields() {
        return otherInformationTab.getCharacterItemsFields();
    }

    public Map<String, Map<String, TextArea>> getCharacterStoryFields() {
        return otherInformationTab.getCharacterStoryFields();
    }

    public Map<String, Map<String, TextArea>> getEuphoriaDescribeFields() {
        return euphoriaInformationTab.getEuphoriaDescribeFields();
    }

    public Map<String, Map<String, TextField>> getEuphoriaAttributesFields() {
        return euphoriaInformationTab.getEuphoriaAttributesFields();
    }

}
