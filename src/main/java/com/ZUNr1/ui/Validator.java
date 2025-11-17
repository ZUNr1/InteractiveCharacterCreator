package com.ZUNr1.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Map;

public class Validator {
    public boolean validateRequiredFields(CharacterMainController controller){
        if (!validateBasicInformation(controller)){
            return false;
        }
        if (!validateAttributeInformation(controller)){
            return false;
        }
        if (!validateSkillsInformation(controller)){
            return false;
        }
        if (!validateInheritanceInformation(controller)){
            return false;
        }
        if (!validatePortraitInformation(controller)){
            return false;
        }
        return true;
    }
    private static boolean validateBasicInformation(CharacterMainController controller){
        if (controller.getGenderComboBox().getValue() == null) {
            showValidationAlert("请选择角色性别");
            //ComboBox 的设计是，用户没有主动选择时，getValue() 返回 null 表示"无选择"状态。
            controller.getGenderComboBox().requestFocus();
            //这是一个方法，用于请求将键盘焦点设置到这个组件上
            //显示为获得焦点的状态（可能有边框高亮）
            //如果用户开始打字，输入会直接进入这个下拉框
            //可以通过键盘方向键或空格键来操作下拉选项
            return false;
        }

        if (controller.getAfflatusComboBox().getValue() == null) {
            showValidationAlert("请选择灵感类型");
            controller.getAfflatusComboBox().requestFocus();
            return false;
        }

        if (controller.getDamageTypeComboBox().getValue() == null) {
            showValidationAlert("请选择创伤类型");
            controller.getDamageTypeComboBox().requestFocus();
            return false;
        }
        if (controller.getCreatorField().getText() == null || controller.getCreatorField().getText().trim().isEmpty()){
            showValidationAlert("创作者名称不能为空");
            controller.getCreatorField().requestFocus();
            return false;
        }

        if (controller.getIdField().getText() == null || controller.getIdField().getText().trim().isEmpty()) {
            showValidationAlert("角色ID不能为空");
            controller.getIdField().requestFocus();
            return false;
        }
        if (controller.getNameField().getText() == null || controller.getNameField().getText().trim().isEmpty()) {
            showValidationAlert("角色姓名不能为空");
            controller.getNameField().requestFocus();
            return false;
        }
        if (controller.getRaritySpinner().getValue() == null){
            showValidationAlert("请选择稀有度");
            controller.getRaritySpinner().requestFocus();
            return false;
        }
        return true;

    }
    private static boolean validateAttributeInformation(CharacterMainController controller){
        // 验证生命值
        if (!validateAttributeField(controller.getHealthField(), "生命值")) {
            controller.getHealthField().requestFocus();
            return false;
        }
        // 验证攻击力
        if (!validateAttributeField(controller.getAttackField(), "攻击力")) {
            controller.getAttackField().requestFocus();
            return false;
        }
        // 验证现实防御
        if (!validateAttributeField(controller.getRealityDefenseField(), "现实防御")) {
            controller.getRealityDefenseField().requestFocus();
            return false;
        }
        // 验证精神防御
        if (!validateAttributeField(controller.getMentalDefenseField(), "精神防御")) {
            controller.getMentalDefenseField().requestFocus();
            return false;
        }
        // 验证暴击技巧
        if (!validateAttributeField(controller.getTechniqueField(), "暴击技巧")) {
            controller.getTechniqueField().requestFocus();
            return false;
        }
        return true;
    }
    private static boolean validateAttributeField(TextField field, String fieldName) {
        if (field.getText() == null || field.getText().trim().isEmpty()) {
            showValidationAlert(fieldName + "不能为空");
            return false;
        }
        return true;
    }
    private boolean validateSkillsInformation(CharacterMainController controller){
        if (!validateSkillName(controller.getSkillNameFields().get("神秘术I"), "神秘术I")) {
            return false;
        }
        if (!validateSkillName(controller.getSkillNameFields().get("神秘术II"), "神秘术II")) {
            return false;
        }
        if (!validateSkillName(controller.getSkillNameFields().get("至终的仪式"), "至终的仪式")) {
            return false;
        }
        if (!validateSkillsDetails(controller, "神秘术I") ||
                !validateSkillsDetails(controller, "神秘术II") ||
                !validateSkillsDetails(controller, "至终的仪式")) {
            return false;
        }
        for (String skillKey : controller.getSkillNameFields().keySet()) {
            if (skillKey.startsWith("额外神秘术_")) {
                //startWith，以什么字符串开头
                if (!validateExtraSkill(controller, skillKey)) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean validateSkillName(TextField nameField, String skillName) {
        if (nameField == null || nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            showValidationAlert(skillName + "的名称不能为空");
            if (nameField != null) {
                nameField.requestFocus();
            }
            return false;
        }
        return true;
    }
    private static boolean validateSkillsDetails(CharacterMainController controller,String skillKey){
        Map<String, TextArea> describeMap = controller.getSkillDescribeFields().get(skillKey);
        if (describeMap != null){
            for (String level : describeMap.keySet()){
                TextArea describeArea = describeMap.get(level);
                if (describeArea == null || describeArea.getText() == null || describeArea.getText().trim().isEmpty()) {
                    showValidationAlert(skillKey + "的" + level + "描述不能为空");
                    if (describeArea != null) {
                        describeArea.requestFocus();
                    }
                    return false;
                }
            }
        }
        Map<String,TextArea> storyMap = controller.getSkillStoryFields().get(skillKey);
        if (storyMap != null){
            for (String level : storyMap.keySet()){
                TextArea storyArea = storyMap.get(level);
                if (storyArea == null || storyArea.getText() == null || storyArea.getText().trim().isEmpty()) {
                    showValidationAlert(skillKey + "的" + level + "故事不能为空");
                    if (storyArea != null) {
                        storyArea.requestFocus();
                    }
                    return false;
                }
            }
        }
        Map<String, ComboBox<String>> typeMap = controller.getSkillTypeFields().get(skillKey);
        if (typeMap != null) {
            for (String level : typeMap.keySet()) {
                ComboBox<String> typeComboBox = typeMap.get(level);
                if (typeComboBox == null || typeComboBox.getValue() == null) {
                    showValidationAlert(skillKey + "的" + level + "类型必须选择");
                    if (typeComboBox != null) {
                        typeComboBox.requestFocus();
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean validateExtraSkill(CharacterMainController controller, String skillKey) {
        if (!validateSkillName(controller.getSkillNameFields().get(skillKey),"额外技能")){
            return false;
        }
        return validateSkillsDetails(controller,skillKey);
    }
    private static boolean validateInheritanceInformation(CharacterMainController controller){
        Map<String,TextArea> inheritanceMap = controller.getInheritanceFields();
        if (inheritanceMap == null || inheritanceMap.isEmpty()) {
            showValidationAlert("传承信息未初始化");
            return false;
        }
        String[] requiredInheritanceFields = {//基础传承可以为空
                "inheritance",      // 传承名称
                "firstInheritance", // 一阶传承
                "secondInheritance", // 二阶传承
                "thirdInheritance"  // 三阶传承
        };
        String[] fieldNames = {
                "传承名称",
                "一阶传承",
                "二阶传承",
                "三阶传承"
        };
        for (int i = 0;i < requiredInheritanceFields.length;i++){
            String fieldKey = requiredInheritanceFields[i];
            String fieldName = fieldNames[i];
            TextArea textArea = inheritanceMap.get(fieldKey);
            if (textArea == null || textArea.getText() == null || textArea.getText().trim().isEmpty()) {
                showValidationAlert(fieldName + "不能为空");
                if (textArea != null) {
                    textArea.requestFocus();
                }
                return false;
            }
        }
        return true;
    }
    private static boolean validatePortraitInformation(CharacterMainController controller){
        Map<String,TextArea> portraitMap = controller.getPortraitFields();
        if (portraitMap == null || portraitMap.isEmpty()){
            showValidationAlert("塑造信息未初始化");
            return false;
        }
        String[] requiredPortraitFields = {
                "portraitDescribe", // 塑造描述
                "firstPortrait",    // 一阶塑造
                "secondPortrait",   // 二阶塑造
                "thirdPortrait",    // 三阶塑造
                "fourthPortrait",   // 四阶塑造
                "fifthPortrait"     // 五阶塑造
        };
        String[] fieldNames = {
                "塑造描述",
                "一阶塑造",
                "二阶塑造",
                "三阶塑造",
                "四阶塑造",
                "五阶塑造"
        };
        for (int i = 0;i < requiredPortraitFields.length;i++){
            String fieldKey = requiredPortraitFields[i];
            String fieldName = fieldNames[i];
            TextArea textArea = portraitMap.get(fieldKey);
            if (textArea == null || textArea.getText() == null || textArea.getText().trim().isEmpty()) {
                showValidationAlert(fieldName + "不能为空");
                if (textArea != null) {
                    textArea.requestFocus();
                }
                return false;
            }
        }
        return true;

    }

    private static void showValidationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("输入验证");
        alert.setHeaderText("请完善信息");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
