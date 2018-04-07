import com.sunshine.service.pojo.FareSearchResponse;
import com.travelport.service.air_v42_0.AirFaultMessage;
import com.travelport.uapi.request.LowFareSearch;
import com.travelport.uapi.request.TravelportConfig;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;


public class LowFareSearchTest {
    @Test
    public  void TT() throws FileNotFoundException, AirFaultMessage {
        Calendar calendar= Calendar.getInstance();
        calendar.set(2017, 10, 22);  //年月日
        Date date1=calendar.getTime();

        Calendar calendar1= Calendar.getInstance();
        calendar1.set(2017, 11, 22);  //年月日
        Date date2=calendar1.getTime();
        //run the ping request
        String userName="Universal API/uAPI8686494205-66ec9190";//请求用户名
        String pwd="L/d7oK4=+3";//请求密码
        LowFareSearch.createLowFareDoubleSearchReq("","",TravelportConfig.TARGET_BRANCH,"HKG","PEK","PEK","HKG",date1,date2,2,1,userName,pwd);
        LowFareSearch.createLowFareSingleSearchReq("","",TravelportConfig.TARGET_BRANCH,"HKG","JFK",date1,2,1,userName,pwd);
    }


    @Test
    public void priceSearch() {

        try {
            Date date = DateTime.parse("2018-01-05").toDate();
            String userName="Universal API/uAPI8686494205-66ec9190";//请求用户名
            String pwd="L/d7oK4=+3";//请求密码


            FareSearchResponse response = LowFareSearch.createLowFareSingleSearchReq("", "",TravelportConfig.TARGET_BRANCH, "HKG"
                    , "PEK", date, 1, 0, userName, pwd);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
