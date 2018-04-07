import com.travelport.uapi.request.TravelportConfig;
import com.travelport.uapi.request.UniversalRecordCancel;
import org.junit.Test;


public class UniversalRecordCancelTest {
    @Test
    public  void Test() {
        String userName="Universal API/uAPI8686494205-66ec9190";//请求用户名
        String pwd="L/d7oK4=+3";//请求密码
        UniversalRecordCancel.getRecordCancelRspJson(TravelportConfig.TARGET_BRANCH,"5QX62K",userName,pwd);
    }
}
