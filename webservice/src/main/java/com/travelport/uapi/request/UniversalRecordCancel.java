package com.travelport.uapi.request;

import com.google.gson.Gson;
import com.travelport.ResponseMapping.RecordCancelResponse;
import com.travelport.schema.universal_v42_0.ProviderReservationStatus;
import com.travelport.schema.universal_v42_0.UniversalRecordCancelReq;
import com.travelport.schema.universal_v42_0.UniversalRecordCancelRsp;
import com.travelport.tutorial.support.WSDLService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversalRecordCancel {

    public static String  getRecordCancelRspJson(String targetBranch,String pnrCode,String userName,String pwd){
        UniversalRecordCancelReq req=new UniversalRecordCancelReq();
        req.setAuthorizedBy("user");
        req.setTargetBranch(targetBranch);
        req.setTraceId("trace");
        req.setUniversalRecordLocatorCode(pnrCode);
        req.setVersion(new BigInteger("3"));
        AirReq.addPointOfSale(req,"UAPI");
        try {
            WSDLService.universalCance.showXML(true,userName,pwd);
            UniversalRecordCancelRsp rsp = new UniversalRecordCancelRsp();
            rsp = WSDLService.universalCance.get().service(req);
            RecordCancelResponse response=new RecordCancelResponse();
            String fault=AirReq.resolveRspFaultMsg(rsp);
            if (fault!=null){
                return fault;
            }
            List<ProviderReservationStatus> statuss=rsp.getProviderReservationStatus();
            if (statuss!=null&&statuss.size()!=0){
                for (ProviderReservationStatus status:statuss) {
                    if (status.isCancelled()){
                        response.setResult(true);
                        response.setResponseMessage("取消成功");
                    }else {
                        response.setResult(false);
                        response.setResponseMessage("取消失败");
                    }
                }

            }
            Gson gson=new Gson();
            String jsonStr=gson.toJson(response);
            System.out.println(jsonStr);
            return jsonStr;
        } catch (Exception e) {
            return AirReq.resolveExceptionMsg(e);
        }
    }

}
