package com.sunshine.publicserver.service.order;

import com.sunshine.publicserver.enums.Platform;

public interface IOrderService<T, V> {

    Platform platform();

    V order(T request);

}
