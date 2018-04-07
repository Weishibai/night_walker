//package com.sunshine.publicserver.policy.interceptor;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.google.common.cache.LoadingCache;
//import com.sunshine.admin.service.IPolicyService;
//import com.sunshine.common.vo.WhitelistPolicy;
//import com.sunshine.publicserver.context.Context;
//
//@Service
//public class WhitelistInterceptor implements PolicyInterceptor {
//
//
////	private Map<Long, WhitelistPolicy> whitelistCache;
////
////	@Scheduled(cron = "${policy.update.cron.expr}")
////	public void timelyUpdate() {
////		Map<Long, Map<String, WhitelistPolicy>> newCache = new HashMap<Long, Map<String, WhitelistPolicy>>();
////		List<WhitelistPolicy> whitelist = this.policyService
////				.queryWhitelistPolicies();
////		for (WhitelistPolicy whitelistPolicy : whitelist) {
////			long agentId = whitelistPolicy.getAgentId();
////			Map<String, WhitelistPolicy> s = newCache.get(agentId);
////
////		}
////
////	}
////
////	@PostConstruct
////	public void init() throws Exception {
////	}
////
////	@Autowired
////	private IPolicyService policyService;
////
////	@Override
////	public boolean preHandler(Context context) {
////		long agentId = context.getLong(Context.AGENT_ID);
//////		policyService.queryWhitelistPolicies(agentId);
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	@Override
////	public boolean postHandler(Context context) {
////		// TODO Auto-generated method stub
////		return false;
////	}
//
//}
