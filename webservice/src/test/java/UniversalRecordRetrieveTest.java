import com.travelport.uapi.request.UniversalRecordRetrieve;
import org.junit.Test;

import static com.travelport.uapi.request.UniversalRecordRetrieve.getRecordRetrieveJson;


public class UniversalRecordRetrieveTest {
    @Test
    public void Test1(){
        String userName="Universal API/uAPI8686494205-66ec9190";//请求用户名
        String pwd="L/d7oK4=+3";//请求密
        UniversalRecordRetrieve.getRecordRetrieveJson("5QX62K",userName,pwd);
    }
}
