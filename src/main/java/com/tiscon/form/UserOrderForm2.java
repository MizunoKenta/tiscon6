package com.tiscon.form;

import com.tiscon.validator.Numeric;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 顧客が入力する見積もり情報を保持するクラス。
 *
 * @author Oikawa Yumi
 */
public class UserOrderForm2{


    @NotBlank(message="氏名が未入力です。")
    private String customerName;

    @NotBlank(message="連絡先TELが未入力です。")
    @Numeric(message="連絡先TELは、半角数字で入力してください。")
    private String tel;

    @Email(message="連絡先メールアドレスは、メールの形式で入力してください。")
    @NotBlank(message="連絡先メールアドレスが未入力です。")
    private String email;

    @NotBlank(message="転居元住所（都道府県）が未選択です。")
    private String oldPrefectureId;

    @NotBlank(message="転居元住所（市区町村以下）が未入力です。")
    private String oldAddress;

    @NotBlank(message="転居先住所（都道府県）が未選択です。")
    private String newPrefectureId;

    @NotBlank(message="転居先住所（市区町村以下）が未入力です。")
    private String newAddress;

    @NotBlank(message="引越し月が未入力です。")
    @Size(max=12, min=1, message="引越し月は1から12までの数字で入力してください。")
    private String month;

    @Numeric(message="段ボールの個数は、半角数字で入力してください。")
    @NotBlank(message="段ボールの個数が未入力です。")
    private String box;

    @Numeric(message="ベッドの個数は、半角数字で入力してください。")
    @NotBlank(message="ベッドの個数が未入力です。")
    private String bed;

    @Numeric(message="自転車の個数は、半角数字で入力してください。")
    @NotBlank(message="自転車の個数が未入力です。")
    private String bicycle;

    @Numeric(message="洗濯機の個数は、半角数字で入力してください。")
    @NotBlank(message="洗濯機の個数が未入力です。")
    private String washingMachine;

    @NotNull(message="洗濯機の設置工事申し込みが未選択です。")
    private boolean washingMachineInstallation;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getOldPrefectureId() {
        return oldPrefectureId;
    }

    public void setOldPrefectureId(String oldPrefectureId) {
        this.oldPrefectureId = oldPrefectureId;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }

    public String getNewPrefectureId() {
        return newPrefectureId;
    }

    public void setNewPrefectureId(String newPrefectureId) {
        this.newPrefectureId = newPrefectureId;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getMonth(){ return month; }

    public void setMonth(String month){this.month=month;}


    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getBicycle() {
        return bicycle;
    }

    public void setBicycle(String bicycle) {
        this.bicycle = bicycle;
    }

    public String getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(String washingMachine) {
        this.washingMachine = washingMachine;
    }

    public boolean getWashingMachineInstallation() {
        return washingMachineInstallation;
    }

    public void setWashingMachineInstallation(boolean washingMachineInstallation) {
        this.washingMachineInstallation = washingMachineInstallation;
    }

}
