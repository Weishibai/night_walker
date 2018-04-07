package com.travelport.uapi.request;

import com.travelport.schema.air_v42_0.AirPriceResult;
import com.travelport.schema.air_v42_0.AirPricingSolution;
import com.travelport.schema.air_v42_0.AirReservation;
import com.travelport.schema.common_v42_0.*;
import com.travelport.schema.universal_v42_0.AirCreateReservationReq;
import com.travelport.schema.universal_v42_0.UniversalRecord;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommReq {
    public static void addBookingTravler(BaseCreateReservationReq req, Passenger pass,
                                         Address address,
                                         String areaCode, String countryCode,
                                         String location, String number, List<SSR> ssrs,String departTime) throws ParseException {
        BookingTraveler bookingTraveler = new BookingTraveler();
        bookingTraveler.setTravelerType(pass.getType());
        bookingTraveler.setKey(pass.getId());
        bookingTraveler.setGender(pass.getGender());
        bookingTraveler.setDOB(pass.getBirthDay());
        BigInteger age =getAge(departTime,pass.getBirthDay().getYear());
        pass.setAge(age);
        bookingTraveler.setAge(age);
        //设置bookingtravleName
        bookingTraveler.setBookingTravelerName(createBookingTravlerName(pass));
        //req.getBookingTraveler().add(bookingTraveler);    没有delivery info//
        bookingTraveler.getPhoneNumber().add(createPhoneNum(areaCode,countryCode, location, number));
        //添加Email节点

        bookingTraveler.getEmail().add(createEmail(pass));
        //添加ssr节点
        bookingTraveler.getSSR().addAll(ssrs);
        //添加Address节点
        if (address!=null){
            bookingTraveler.getAddress().add(createAddress(address));
        }
        req.getBookingTraveler().add(bookingTraveler);

    }
    public static BigInteger getAge(String departTime,int birth) throws ParseException {
        String departTime1=departTime.substring(0,11);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=sdf.parse(departTime1);
        Calendar cl=Calendar.getInstance();
        cl.setTime(date1);
        int year=cl.get(Calendar.YEAR);
        int age=year-birth;
        return BigInteger.valueOf(age);
    }

    public static void addActionStatus(AirCreateReservationReq req, String providerCode) {
        ActionStatus actionStatus = new ActionStatus();
        actionStatus.setProviderCode(providerCode);
        actionStatus.setTicketDate("T*");
        actionStatus.setType("ACTIVE");
        req.getActionStatus().add(actionStatus);
    }
    public static BookingTravelerName createBookingTravlerName(Passenger pass) {
        BookingTravelerName name = new BookingTravelerName();
        name.setFirst(pass.getFirstName());
        name.setLast(pass.getLastName());
        name.setPrefix(pass.getNike());
        return name;
    }

    public static SSR createSSR(String segmentRef,String freeText) {
        SSR ssr = new SSR();
        ssr.setFreeText(freeText);
        ssr.setSegmentRef(segmentRef);
        ssr.setStatus("HK");
        ssr.setType("DOCS");
        return ssr;
    }
    public static FormOfPayment createFormOfPayment(String type,String key) {
        FormOfPayment fop = new FormOfPayment();
        fop.setType(type);
        fop.setKey(key);
        return fop;
    }
    public static PhoneNumber createPhoneNum(String areaCode,String countryCode,
                                             String location,String number) {
        PhoneNumber phone = new PhoneNumber();
        phone.setAreaCode(areaCode);
        phone.setNumber(number);
        phone.setCountryCode(countryCode);
        phone.setLocation(location);
        return phone;
    }

    public static Email createEmail(Passenger pass) {
        Email email = new Email();
        email.setEmailID(pass.getEmailId());
        email.setType(pass.getEmailType());
        return email;
    }
    public static TypeStructuredAddress createAddress(Address address) {
        TypeStructuredAddress generalAddress = new TypeStructuredAddress();
        if (address!=null){
            generalAddress.setAddressName(address.getAddressName());
            generalAddress.setCity(address.getCity());
            State vt1 = new State();
            vt1.setValue(address.getState());
            generalAddress.setState(vt1);
            generalAddress.getStreet().add(address.getStreet());
            generalAddress.setCountry(address.getCountry());
            generalAddress.setPostalCode(address.getPostalCode());
        }
        return generalAddress;
    }

    public static void addContinueCheckOverride(AirCreateReservationReq req){
        ContinuityCheckOverride over=new ContinuityCheckOverride();
        over.setKey("Yes");
        over.setValue("Yes");
        req.setContinuityCheckOverride(over);
    }

    public static String getSupplierCode(UniversalRecord record){
        String supplierCode="";
        AirReservation reservations=record.getAirReservation().get(0);
        List<SupplierLocator> locators=reservations.getSupplierLocator();
        if (locators.size()>0){
            supplierCode=locators.get(0).getSupplierCode();
        }
        return supplierCode;
    }
    public static String  getCurrency(String price){
        if(price==null){
            return null;
        }
        String regEx="[^0-9]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(price);
        if (m.find()){
            return m.group();
        }else{
            return null;
        }
    }
    public static int getPrice(String price){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(price);
        String pce=m.replaceAll("").trim();
        return Integer.parseInt(pce);
    }

    public static AirPricingSolution getLowPriceSoln(List<AirPriceResult> price){
        AirPricingSolution solns=null;
        int low=0;
        for (AirPriceResult result:price) {
            List<AirPricingSolution> ls=result.getAirPricingSolution();
            for (AirPricingSolution soln: ls ) {
                String totalPrice= soln.getTotalPrice();
                int temp=CommReq.getPrice(totalPrice);
                if (low==0){
                    low=temp;
                    solns=soln;
                }else if(temp<low){
                    low=temp;
                    solns=soln;
                }
            }
        }
        return solns;
    }

}
