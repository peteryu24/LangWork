package gmx.fwd.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gmx.fwd.service.content.ContentService;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.utils.exception.LangWorkItemNotFoundException;
import gmx.fwd.vo.contentvo.ContentVo;

@Controller
@RestController
@RequestMapping("/gmxlang")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private GmxResult gmxResult;

	@PostMapping(value = "/addLangWork")
	public GmxResult addLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.addLangWork(contentVo);
	}

	@PostMapping(value = "/getLangWorkItem")
	public GmxResult getLangWorkItem(@RequestParam int mgrSeq) {
		return contentService.getLangWorkItem(mgrSeq);
	}

	@PostMapping(value = "/getLangWorkList")
	public GmxResult getOrSearchLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.getOrSearchLangWork(contentVo);
	}

/*	@PostMapping(value = {"/getLangWorkList","/getLangWorkList"})
	public GmxResult getOrSearchLangWorkItemOrList(@ModelAttribute ContentVo contentVo) {
		return contentService.getOrSearchLangWork(contentVo);
	}*/

	@PutMapping(value = "/modLangWork")
	public GmxResult modifyLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.modifyLangWork(contentVo);
	}

	@DeleteMapping(value = "/delLangWork")
	public GmxResult deleteLangWork(@RequestParam int mgrSeq) {
		return contentService.deleteLangWork(mgrSeq);
	}

	@ExceptionHandler(RuntimeException.class)
	public GmxResult handleRuntimeException(RuntimeException exception) {
		return gmxResult.resultErrorEntity("RunTimeException", ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()));
	}

	@ExceptionHandler(LangWorkItemNotFoundException.class)
	public GmxResult handleLangWorkItemNotFoundException(LangWorkItemNotFoundException exception) {
		return gmxResult.resultErrorEntity("LangWorkItemNotFoundException",
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public GmxResult IllegalArgumentException(IllegalArgumentException exception) {
		return gmxResult.resultErrorEntity("IllegalArgumentException",
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()));
	}
}
