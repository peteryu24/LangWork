package gmx.fwd.service.content;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmx.fwd.mapper.content.ContentMapper;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.vo.contentvo.ContentVo;

@Service
public class ContentService {

	@Autowired
	private ContentMapper contentMapper;

	@Autowired
	private GmxResult gmxResult;

	@Autowired
	public ContentService(ContentMapper contentMapper) {
		this.contentMapper = contentMapper;
	}

	public HashMap<String, Object> addLangWork(ContentVo contentVo) { // 필수 파라미터 req_name, req_lang
		try {
			return contentMapper.checkLangWork(contentVo.getReqLang()) ? gmxResult.resultError("중복된 요청 건이 존재합니다") : gmxResult.result(true);
		} catch (Exception e) {
			return gmxResult.resultError("서버 요청 실패");
		}
	}

	public HashMap<String, Object> getLangWorkItem(int mgrSeq) {
		List<ContentVo> getLangWorkItem = contentMapper.getLangWorkItem(mgrSeq);
		try {
			return getLangWorkItem.isEmpty() ? gmxResult.resultError("조회 실패") : gmxResult.result(getLangWorkItem);
		} catch (Exception e) {
			return gmxResult.resultError("서버 요청 실패");
		}
	}

	public HashMap<String, Object> getOrSearchLangWork(ContentVo contentVo) { // 필수 파라미터 검색 값
		List<ContentVo> getLangWorkList = contentMapper.getOrSearchLangWork(contentVo);
		try {
			return getLangWorkList.isEmpty() ? gmxResult.resultError("조회 실패") : gmxResult.result(getLangWorkList);
		} catch (Exception e) {
			return gmxResult.resultError("서버 요청 실패");
		}
	}

	public HashMap<String, Object> modifyLangWork(ContentVo contentVo) { // 필수 파라미터 req_name, req_lang
		try {
			return contentMapper.modifyLangWork(contentVo) ? gmxResult.result(true) : gmxResult.resultError("번역 실패");
		} catch (Exception e) {
			return gmxResult.resultError("서버 요청 실패");
		}
	}

	public HashMap<String, Object> deleteLangWork(int mgrSeq) {
		try {
			return contentMapper.deleteLangWork(mgrSeq) ? gmxResult.result(true) : gmxResult.resultError("삭제 실패");
		} catch (Exception e) {
			return gmxResult.resultError("서버 요청 실패");
		}
	}

}
