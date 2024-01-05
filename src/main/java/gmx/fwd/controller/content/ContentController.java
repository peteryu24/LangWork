package gmx.fwd.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gmx.fwd.service.content.ContentService;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.utils.exception.LangWorkItemNotFoundException;
import gmx.fwd.vo.contentvo.ContentVo;

@RestController
@RequestMapping("/gmxlang")
public class ContentController {

	@Autowired
	private ContentService contentService;

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

	@PostMapping(value = "/modLangWork")
	public GmxResult modifyLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.modifyLangWork(contentVo);
	}

	@PostMapping(value = "/delLangWork")
	public GmxResult deleteLangWork(@RequestParam int mgrSeq) {
		return contentService.deleteLangWork(mgrSeq);
	}
}
