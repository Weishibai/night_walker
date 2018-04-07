package com.sunshine.publicserver.service.pay;

import com.sunshine.publicserver.enums.Platform;

public interface IPayService<T, V> {

    Platform platform();

    V pay(T request);

}
