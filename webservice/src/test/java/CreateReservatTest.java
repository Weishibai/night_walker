import com.travelport.schema.air_v42_0.AirPriceRsp;
import com.travelport.schema.air_v42_0.AirSegmentPricingModifiers;
import com.travelport.schema.air_v42_0.TypeBaseAirSegment;
import com.travelport.uapi.request.*;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class CreateReservatTest {
    @Test
    public  void Test1() throws ParseException, DatatypeConfigurationException {
        TypeBaseAirSegment segment1= AirPrice.createAirSegment( "1","A","0","CX","690","SIN","HKG","2017-11-08T12:55:00.000+08:00","2017-11-08T17:05:00.000+08:00","1G",true, "");//报文格式除了最后一个不要connection节点  其他都要节点
        TypeBaseAirSegment segment2=AirPrice.createAirSegment("2","A","0","CX","890","HKG","EWR","2017-11-08T18:20:00.000+08:00","2017-11-08T21:00:00.000-05:00","1G",false, "");

        TypeBaseAirSegment segment3= AirPrice.createAirSegment( "3","A","1","CX","841","JFK","HKG","2017-11-15T09:00:00.000-05:00","2017-11-16T14:10:00.000+08:00","1G",true, "");//报文格式除了最后一个不要connection节点  其他都要节点
        TypeBaseAirSegment segment4=AirPrice.createAirSegment("4","A","1","CX","635","HKG","SIN","2017-11-16T15:15:00.000+08:00","2017-11-16T19:15:00.000+08:00","1G",false, "");
        List<TypeBaseAirSegment> Segments=new ArrayList(Arrays.asList(segment1,segment2,segment3,segment4));
        //仓位信息 航段编号与上面创建的 对应
        AirSegmentPricingModifiers modifier1=AirPrice.createSegmentPricingModifier("1",null,"S");
        AirSegmentPricingModifiers modifier2=AirPrice.createSegmentPricingModifier("2",null,"S");
        AirSegmentPricingModifiers modifier3=AirPrice.createSegmentPricingModifier("3",null,"S");
        AirSegmentPricingModifiers modifier4=AirPrice.createSegmentPricingModifier("4",null,"S");
        List<AirSegmentPricingModifiers> modifiers= new ArrayList(Arrays.asList(modifier1,modifier2,modifier3,modifier4));
        String userName="Universal API/uAPI8686494205-66ec9190";//请求用户名
        String pwd="L/d7oK4=+3";//请求密码
        Map priceRspMap=AirPrice.createAirPriceReqMap(2,1,Segments,modifiers,userName,pwd);
        AirPriceRsp rsp=null;
        boolean status=false;
        if (priceRspMap.get("3319")==null){ //返回码有3319设置成false
            rsp= (AirPriceRsp) priceRspMap.get("true");
            status=true;
        }else if (priceRspMap.get("false")!=null){
            String errorMessage= (String) priceRspMap.get("false");
            System.out.println(errorMessage);
            return;
        }
        String fault= AirReq.resolveRspFaultMsg(rsp);
        if (fault==null&&rsp!=null){
            List<String> segmentStrings= CreateReservat.getSegmentRefs(rsp);
            Passenger pass=new Passenger("1995-02-01","M","1","ADT","Peng",
                    "Sun","Mr","P/CN/P1234567/CN/01Jan95/M/13Apr15/Jones/Stephen","test@travelport.com","Home");
            Address address=new Address("Home","123 Dalton Drive",
                    "Calgary","AB","T2P1K6","CA");
            Passenger pass1=new Passenger("1995-02-02","M","2","ADT","Peng",
                    "dun","Mr","P/CN/P1234567/CN/01Jan95/M/13Apr15/Jones/sddsd","test@travesort.com","Home");
            //Address address1=new Address("Home","123 Dalton Drive","Calgary","AB","T2P1K6","CA");
            Passenger pass2=new Passenger("2014-02-02","M","3","CNN","Pendg",
                    "Sudn","Mr","P/CN/P1234567/CN/01Jan95/M/13Apr15/Jsfnes/sddsd","test@travdsort.com","Home");
            String areaCode="403";
            String countryCode="1";
            String location="YYC";
            String number="555-1212";
            String departTime=segment1.getDepartureTime();
            CreateReservat.getAirCreateRspJson(rsp, TravelportConfig.TARGET_BRANCH,segmentStrings,address,Arrays.asList(pass,pass1,pass2),areaCode,countryCode,location,number,departTime,userName,pwd);
        }else {
            System.out.println(fault);
        }
    }
}
