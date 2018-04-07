package com.sunshine.publicserver.service.search;

import com.sunshine.publicserver.enums.Platform;

public interface ISearchService<T, V> {

    Platform platform();

    V search(T request);

}
