package gmx.fwd.controller.content;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gmx.fwd.service.content.ContentService;
import gmx.fwd.vo.contentvo.ContentVo;

@Controller
@RequestMapping("/gmxlang")
public class ContentController {

	@Autowired
	private ContentService contentService;


	@ResponseBody
	@PostMapping(value = "/addLangWork")
	public HashMap<String, Object> addLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.addLangWork(contentVo);
	}

	@ResponseBody
	@PostMapping(value = "/getLangWorkItem")
	public HashMap<String, Object> getLangWorkItem(@RequestParam int mgrSeq) {
		return contentService.getLangWorkItem(mgrSeq);
	}

	@ResponseBody
	@RequestMapping(value = "/getLangWorkList")
	public HashMap<String, Object> getOrSearchLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.getOrSearchLangWork(contentVo);	 
	}

	@ResponseBody
	@PostMapping(value = "/modLangWork")
	public HashMap<String, Object> modifyLangWork(@ModelAttribute ContentVo contentVo) {
		return contentService.modifyLangWork(contentVo);
	}

	@ResponseBody
	@RequestMapping(value = "/delLangWork")
	public HashMap<String, Object> deleteLangWork(@RequestParam int mgrSeq) {
		return contentService.deleteLangWork(mgrSeq);
	}
}
