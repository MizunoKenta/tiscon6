package com.tiscon.controller;

import com.tiscon.dao.EstimateDao;
import com.tiscon.dto.UserOrderDto;
import com.tiscon.form.UserOrderForm;
import com.tiscon.form.UserOrderForm2;
import com.tiscon.service.EstimateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 引越し見積もりのコントローラークラス。
 *
 * @author Oikawa Yumi
 */
@Controller
public class EstimateController {

    private final EstimateDao estimateDAO;

    private final EstimateService estimateService;

    /**
     * コンストラクタ
     *
     * @param estimateDAO EstimateDaoクラス
     * @param estimateService EstimateServiceクラス
     */
    public EstimateController(EstimateDao estimateDAO, EstimateService estimateService) {
        this.estimateDAO = estimateDAO;
        this.estimateService = estimateService;
    }

    @GetMapping("")
    String index(Model model) {
        return "top";
    }

    /**
     * 入力画面に遷移する。
     *
     * @param model 遷移先に連携するデータ
     * @return 遷移先
     */
    @GetMapping("input")
    String input(Model model) {
        if (!model.containsAttribute("userOrderForm")) {
            model.addAttribute("userOrderForm", new UserOrderForm());
        }

        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        return "input";
    }

    // 新しく追加
    @GetMapping("input2")
    String input2(Model model) {
        if (!model.containsAttribute("userOrderForm2")) {
            model.addAttribute("userOrderForm2", new UserOrderForm2());
        }

        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        return "input2";
    }

    /**
     * TOP画面に戻る。
     *
     * @param model 遷移先に連携するデータ
     * @return 遷移先
     */


    @PostMapping(value = "submit", params = "backToTop")
    String backToTop(Model model) {
        return "top";
    }

    /**
     * 確認画面に遷移する。
     *
     * @param userOrderForm 顧客が入力した見積もり依頼情報
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "submit", params = "confirm")
    String confirm(UserOrderForm userOrderForm, Model model) {

        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm);
        return "confirm";
    }

    /**
     * 確認画面2に遷移する。
     *
     * @param userOrderForm2 顧客が入力した見積もり依頼情報
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "submit", params = "confirm2")
    String confirm(UserOrderForm2 userOrderForm2, Model model) {

        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm2);
        return "confirm2";
    }

    /**
     * 入力画面に戻る。
     *
     * @param userOrderForm 顧客が入力した見積もり依頼情報
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "result", params = "backToInput")
    String backToInput(UserOrderForm userOrderForm, Model model) {
        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm);
        return "input";
    }

    /**
     * 確認画面に戻る。
     *
     * @param userOrderForm2 顧客が入力した見積もり依頼情報
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "order", params = "backToConfirm")
    String backToConfirm(UserOrderForm2 userOrderForm2, Model model) {
        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm2);
        return "input2";
    }

    /**
     * 概算見積もり画面に遷移する。
     *
     * @param userOrderForm 顧客が入力した見積もり依頼情報
     * @param result        精査結果
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "result", params = "calculation")
    String calculation(@Validated UserOrderForm userOrderForm, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
            model.addAttribute("userOrderForm", userOrderForm);
            return "confirm";
        }

        // 料金の計算を行う。
        UserOrderDto dto = new UserOrderDto();
        BeanUtils.copyProperties(userOrderForm, dto);
        Integer price = estimateService.getPrice(dto);

        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm);
        model.addAttribute("price", price);
        return "result";
    }

    /**
     * 入力画面2に遷移する。
     *
     * @param userOrderForm 顧客が入力した見積もり依頼情報
     * @param result        精査結果
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "input2")
    String input2(@Validated UserOrderForm userOrderForm, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
            model.addAttribute("userOrderForm", userOrderForm);
            return "confirm";
        }

        // 入力画面に遷移する時点ではデータベースへの情報登録はまだしない
//        UserOrderDto dto = new UserOrderDto();
//        BeanUtils.copyProperties(userOrderForm, dto);
//        estimateService.registerOrder(dto);
        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm);

        return "input2";
    }

    /**
     * 申し込み完了画面に遷移する。
     *
     * @param userOrderForm2 顧客が入力した見積もり依頼情報
     * @param result        精査結果
     * @param model         遷移先に連携するデータ
     * @return 遷移先
     */
    @PostMapping(value = "order", params = "complete")
    String complete(@Validated UserOrderForm2 userOrderForm2, BindingResult result, Model model) {
        //入力内容に問題があれば確認画面を表示する
        if (result.hasErrors()) {

            model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
            model.addAttribute("userOrderForm", userOrderForm2);
            return "confirm2";
        }

        // データベースに入力された情報を登録する
        UserOrderDto dto = new UserOrderDto();
        BeanUtils.copyProperties(userOrderForm2, dto);
        estimateService.registerOrder(dto);
        model.addAttribute("prefectures", estimateDAO.getAllPrefectures());
        model.addAttribute("userOrderForm", userOrderForm2);

        return "complete";
    }

}
