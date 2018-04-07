package com.sunshine.publicserver.service.strategy;

import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.PricePolicy;
import com.sunshine.common.vo.WhitelistPolicy;
import com.sunshine.publicserver.utils.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class WhiteListStrategyService {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    @Value("${whiteListUrl}")
    private String whiteListUrl;

    public List<WhitelistPolicy> query(String fromCountry, String toCountry) {
        WhitelistPolicy request = new WhitelistPolicy();
        request.setFromState(fromCountry);
        request.setToState(toCountry);

        String resp = HttpClientUtils.post(whiteListUrl, JsonUtil.writeString(request), HttpClientUtils.DEFAULT_HEADER, 2000, 3000);
        LOGGER.debug("white list query response: {}", resp);
        if (StringUtils.isBlank(resp)) {
            return Collections.emptyList();
        }

        @SuppressWarnings("unchecked")
        HttpResponse<String> response = JsonUtil.readValue(resp, HttpResponse.class);
        if (null == response || !response.isSuccess() || StringUtils.isBlank(response.getData())) {
            return Collections.emptyList();
        }

        try {
            return JsonUtil.OBJECT_MAPPER.readValue(response.getData(), new TypeReference<List<WhitelistPolicy>>(){});
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
