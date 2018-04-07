import com.travelport.schema.air_v42_0.AirPriceRsp;
import com.travelport.schema.air_v42_0.AirSegmentPricingModifiers;
import com.travelport.schema.air_v42_0.TypeBaseAirSegment;
import com.travelport.uapi.request.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class AvailabilitySearchTest {
    @Test
    public  void Test1(){
        TypeBaseAirSegment segment1= AirPrice.createAirSegment( "1","S","0","CA","116","HKG","PEK","2017-11-30T20:00:00.000+08:00","2017-11-30T22:55:00.000+08:00","1G",true, "");//报文格式除了最后一个不要connection节点  其他都要节点
        TypeBaseAirSegment segment2=AirPrice.createAirSegment("2","S","0","CA","819","PEK","EWR","2017-11-31T11:35:00.000+08:00","2017-11-31T11:55:00.000-05:00","1G",false, "");
        List<TypeBaseAirSegment> Segments=new ArrayList(Arrays.asList(segment1,segment2));
        //仓位信息 航段编号与上面创建的 对应
        AirSegmentPricingModifiers modifier1=AirPrice.createSegmentPricingModifier("1",null,"V");
        AirSegmentPricingModifiers modifier2=AirPrice.createSegmentPricingModifier("2",null,"V");
        List<AirSegmentPricingModifiers> modifiers= new ArrayList(Arrays.asList(modifier1,modifier2));
        String userName="Universal API/uAPI8686494205-66ec9190";//请求用户名
        String pwd="L/d7oK4=+3";//请求密码
        Map priceRspMap=AirPrice.createAirPriceReqMap(2,1,Segments,modifiers,userName,pwd);
        AirPriceRsp rsp=null;
        boolean status=true;
        if (priceRspMap.get("3319")!=null){ //返回码有3319设置成false
            status=false;
            System.out.println("错误");
            return;
        }else if (priceRspMap.get("false")!=null){
            String errorMessage= (String) priceRspMap.get("false");
            System.out.println(errorMessage);
            return;
        }
        rsp= (AirPriceRsp) priceRspMap.get("true");
        String fault= AirReq.resolveRspFaultMsg(rsp);
        if (fault==null&&rsp!=null){
            String jsonStr= AvailabilitySearch.getJsonStr(rsp,status);
            System.out.println(jsonStr);
        }else {
            System.out.println(fault);//错误的json串
        }
    }
}
