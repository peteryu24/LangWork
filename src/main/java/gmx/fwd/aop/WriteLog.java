package gmx.fwd.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WriteLog {

	private static final Logger logger = LoggerFactory.getLogger(WriteLog.class);

	@Pointcut("execution(* gmx.fwd.service.content.ContentService.getLangWorkItem(..))")
	private void forGetLangWorkItem() {
	}
	
	@Pointcut("execution(* gmx.fwd.service.content.ContentService.getOrSearchLangWork(..))")
	private void forGetOrSearchLangWork() {
	}
	
	@Pointcut("execution(* gmx.fwd.service.content.ContentService.modifyLangWork(..))")
	private void forModifyLangWork() {
	}
	
	@Pointcut("execution(* gmx.fwd.service.content.ContentService.deleteLangWork(..))")
	private void forDeleteLangWork() {
	}

	@AfterThrowing(pointcut = "forGetLangWorkItem()", throwing = "e")
	public void errorGetLangWorkItem(Exception e) {
		logger.error("서버 요청에 실패하였습니다.(getLangWorkItem) Error: " + e.getMessage());
	}
	
	@AfterThrowing(pointcut = "forGetOrSearchLangWork()", throwing = "e")
	public void errorGetOrSearchLangWork(Exception e) {
		logger.error("서버 요청에 실패하였습니다.(getOrSearchLangWork) Error: " + e.getMessage());
	}
	
	@AfterThrowing(pointcut = "forModifyLangWork()", throwing = "e")
	public void errorModifyLangWork(Exception e) {
		logger.error("서버 요청에 실패하였습니다.(modifyLangWork) Error: " + e.getMessage());
	}
	
	@AfterThrowing(pointcut = "forDeleteLangWork()", throwing = "e")
	public void errorDeleteLangWork(Exception e) {
		logger.error("서버 요청에 실패하였습니다.(deleteLangWork) Error: " + e.getMessage());
	}
}
