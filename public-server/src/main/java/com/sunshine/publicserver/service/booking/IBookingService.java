package com.sunshine.publicserver.service.booking;

import com.sunshine.publicserver.enums.Platform;

public interface IBookingService<T, V> {

    Platform platform();

    V booking(T request);

}
