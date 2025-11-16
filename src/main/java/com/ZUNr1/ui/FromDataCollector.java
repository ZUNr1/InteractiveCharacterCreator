/*
package com.ZUNr1.ui;

import com.ZUNr1.enums.Gender;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FromDataCollector {
    public static CharacterFormData collectFromController(CharacterMainController controller){
        CharacterFormData formData = new CharacterFormData();
        // 收集基本信息
        collectBasicInfo(formData, controller);

        // 收集属性信息
        collectAttributes(formData, controller);

        // 收集传承信息
        collectInheritance(formData, controller);

        // 收集塑造信息
        collectPortrait(formData, controller);

        return formData;
    }
    private static void collectBasicInfo(CharacterFormData formData, CharacterMainController controller) {
        formData.setId(getTextFieldText(controller.getIdField()));
        formData.setName(getTextFieldText(controller.getNameField()));
        formData.setGender(getComboBoxValue(controller.getGenderComboBox()));
        formData.setAfflatus(getComboBoxValue(controller.getAfflatusComboBox()));
        formData.setDamageType(getComboBoxValue(controller.getDamageTypeComboBox()));
        formData.setRarity(getSpinnerValue(controller.getRaritySpinner()));
    }
    private static String getTextFieldText(TextField textField){
        if (textField == null){
            return "";
        }
        String text = textField.getText();
        return text != null ? text.trim() : "";
    }
    private static Gender convertToGender(ComboBox<String> genderComboBox){
        if (genderComboBox == null){
            return Gender.OTHER;
        }
        String genderText = genderComboBox.getValue();
        if (genderText == null) {
            return Gender.OTHER;
        }
        switch (genderText.trim()) {
            case "男": return Gender.MAN;
            case "女": return Gender.WOMAN;
            default: return Gender.OTHER;
        }
    }
}
*/
