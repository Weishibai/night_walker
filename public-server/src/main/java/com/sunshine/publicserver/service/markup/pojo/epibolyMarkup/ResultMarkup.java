package com.sunshine.publicserver.service.markup.pojo.epibolyMarkup;

import java.util.List;

import com.sunshine.publicserver.service.markup.pojo.markup.MarkupResult;

public class ResultMarkup {
    private String success; // 是否成功
    private String errorMsg;// 失败信息
    private List<List<Markupinfo>> markupinfos; // 节点
    private MarkupResult original;    //原始数据

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public List<List<Markupinfo>> getMarkupinfos() {
        return markupinfos;
    }

    public void setMarkupinfos(List<List<Markupinfo>> markupinfos) {
        this.markupinfos = markupinfos;
    }

    public MarkupResult getOriginal() {
        return original;
    }

    public void setOriginal(MarkupResult original) {
        this.original = original;
    }
}
