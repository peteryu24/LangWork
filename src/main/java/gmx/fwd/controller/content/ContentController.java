package gmx.fwd.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gmx.fwd.service.content.ContentService;
import gmx.fwd.utils.CustomException;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.vo.contentvo.ContentVo;

@RestController
@RequestMapping("/gmxlang")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@PostMapping(value = "/addLangWork")
	public GmxResult addLangWork(@ModelAttribute ContentVo contentVo) {
		try {
			return contentService.addLangWork(contentVo);
		} catch (CustomException e) {
			return new GmxResult().resultError(e.getMessage());
		}
	}

	@PostMapping(value = "/getLangWorkItem")
	public GmxResult getLangWorkItem(@RequestParam int mgrSeq) {
		try {
			return contentService.getLangWorkItem(mgrSeq);
		} catch (CustomException e) {
			return new GmxResult().resultError(e.getMessage());
		}
	}

	@PostMapping(value = "/getLangWorkList")
	public GmxResult getOrSearchLangWork(@ModelAttribute ContentVo contentVo) {
		try {
			return contentService.getOrSearchLangWork(contentVo);
		} catch (CustomException e) {
			return new GmxResult().resultError(e.getMessage());
		}

	}

	@PostMapping(value = "/modLangWork")
	public GmxResult modifyLangWork(@ModelAttribute ContentVo contentVo) {
		try {
			return contentService.modifyLangWork(contentVo);
		} catch (CustomException e) {
			return new GmxResult().resultError(e.getMessage());
		}
	}

	@PostMapping(value = "/delLangWork")
	public GmxResult deleteLangWork(@RequestParam int mgrSeq) {
		try {
			return contentService.deleteLangWork(mgrSeq);
		} catch (CustomException e) {
			return new GmxResult().resultError(e.getMessage());
		}
	}
}
