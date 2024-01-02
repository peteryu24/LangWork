package gmx.fwd.controller.content;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gmx.fwd.service.content.ContentService;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.vo.contentvo.ContentVo;

@Controller
@RequestMapping("/gmxlang")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private GmxResult gmxResult;

	@ResponseBody
	@PostMapping(value = "/addLangWork")
	public HashMap<String, Object> addLangWork(@RequestParam String reqName, @RequestParam String resName, @RequestParam String reqLang,
			@RequestParam String resLang, @RequestParam String etcNote) {

		int addFlag = contentService.addLangWork(reqName, resName, reqLang, resLang, etcNote);
		
		if (addFlag <= 0) {
			return gmxResult.resultError(addFlag == -1 ? "중복된 요청 건이 존재합니다." : "게시글 작성 실패");
		}

		return gmxResult.result(true);
	}

	@ResponseBody
	@PostMapping(value = "/getLangWorkItem")
	public HashMap<String, Object> getLangWorkItem(int mgrSeq) {

		List<ContentVo> getLangWorkList = contentService.getLangWorkItem(mgrSeq);

		return getLangWorkList.isEmpty() ? gmxResult.resultError("조회 실패") : gmxResult.result(getLangWorkList);
	}

	@ResponseBody
	@RequestMapping(value = "/getLangWorkList")
	public HashMap<String, Object> getOrSearchLangWork(@RequestParam(defaultValue = "", required = false) String reqName,
			@RequestParam(defaultValue = "", required = false) String resName,
			@RequestParam(defaultValue = "", required = false) String reqLang,
			@RequestParam(defaultValue = "", required = false) String resLang,
			@RequestParam(defaultValue = "", required = false) String resFlag) {

		List<ContentVo> getOrSearchLangWorkList = contentService.getOrSearchLangWork(reqName, resName, reqLang, resLang, resFlag);
		return getOrSearchLangWorkList.isEmpty() ? gmxResult.resultError("조회 실패") : gmxResult.result(getOrSearchLangWorkList);
	}

	@ResponseBody
	@PostMapping(value = "/modLangWork")
	public HashMap<String, Object> modifyLangWork(@RequestParam int mgrSeq, @RequestParam String resName, @RequestParam String reqName,
			@RequestParam String reqLang, @RequestParam String resLang, @RequestParam String etcNote) {

		return contentService.modifyLangWork(mgrSeq, resName, reqName, resLang, reqLang, etcNote) ? gmxResult.result(true)
				: gmxResult.resultError("번역 실패");
	}

	@ResponseBody
	@RequestMapping(value = "/delLangWork")
	public HashMap<String, Object> deleteLangWork(@RequestParam int mgrSeq) {

		return contentService.deleteLangWork(mgrSeq) ? gmxResult.result(true) : gmxResult.resultError("삭제 실패");
	}
}
