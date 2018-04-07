package com.travelport.uapi.request;

import com.travelport.schema.air_v42_0.*;
import com.travelport.schema.common_v42_0.BookingTraveler;

import java.util.HashMap;
import java.util.Map;

public class TravleUtils {

    public static class AirSegmentMap extends HashMap<String, TypeBaseAirSegment> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void add(TypeBaseAirSegment segment) {
            put(segment.getKey(), segment);
        }

        @Override
        public TypeBaseAirSegment get(Object wontWork) {
            throw new RuntimeException("This is disallowed because it was a " + "common mistake to pass a AirSegmentRef here instead "
                    + "of the string contained in the AirSegmentRef");
        }

        public TypeBaseAirSegment getByKey(String ref) {
            return super.get(ref);
        }
    }

    public static class BookInfoMap extends HashMap<String, Map<String,BookingInfo>> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void add(BookingInfo info) {
            Map m=new  HashMap<String, BookingInfo>();
            m.put(info.getFareInfoRef(),info);
            put(info.getSegmentRef(),m);
        }


        public BookingInfo getByRefs(String segmentRef,String fareInfoRef) {
            return super.get(segmentRef).get(fareInfoRef);
        }
        public BookingInfo getByRef(String segmentRef) {
            BookingInfo info =null;
            Map<String,BookingInfo> fareMap= super.get(segmentRef);
            for (String farekey: fareMap.keySet()) {
              info=   fareMap.get(farekey);
            }
            return  info;
        }
    }

    public static class AirFareInfoMap extends HashMap<String, FareInfo> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void add(FareInfo info) {
            put(info.getKey(), info);
        }


        public FareInfo getByKey(String key) {
            return super.get(key);
        }
    }

    public static class BookingTravelerMap extends HashMap<String, BookingTraveler> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void add(BookingTraveler info) {
            put(info.getKey(), info);
        }


        public BookingTraveler getByKey(String key) {
            return super.get(key);
        }
    }

    public static class FareInfoMap extends HashMap<String, FareInfo> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void add(FareInfo info) {
            put(info.getKey(), info);
        }


        public FareInfo getByKey(String key) {
            return super.get(key);
        }
    }
    public static class FightDetailMap extends HashMap<String, FlightDetails> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void add(FlightDetails info) {
            put(info.getKey(), info);
        }


        public FlightDetails getByKey(String key) {
            return super.get(key);
        }
    }
}
