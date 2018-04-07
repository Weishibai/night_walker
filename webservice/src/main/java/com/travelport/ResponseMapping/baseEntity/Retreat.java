package com.travelport.ResponseMapping.baseEntity;

/**
 * Created by jiww on 2017/10/17.
 */
public class Retreat {
    private String Type;//乘客类型
    private String ChangePenalty;//改期手续费
    private String  CancelPenalty;//退票手续费
    private String Currency;//手续费币种

    public Retreat(String type, String changePenalty, String cancelPenalty, String currency) {
        Type = type;
        ChangePenalty = changePenalty;
        CancelPenalty = cancelPenalty;
        Currency = currency;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getChangePenalty() {
        return ChangePenalty;
    }

    public void setChangePenalty(String changePenalty) {
        ChangePenalty = changePenalty;
    }

    public String getCancelPenalty() {
        return CancelPenalty;
    }

    public void setCancelPenalty(String cancelPenalty) {
        CancelPenalty = cancelPenalty;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
