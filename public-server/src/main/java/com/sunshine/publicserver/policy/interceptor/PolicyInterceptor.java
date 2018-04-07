package com.sunshine.publicserver.policy.interceptor;

import com.sunshine.publicserver.context.Context;

public interface PolicyInterceptor {

	public boolean preHandler(Context context);

	public boolean postHandler(Context context);
}
