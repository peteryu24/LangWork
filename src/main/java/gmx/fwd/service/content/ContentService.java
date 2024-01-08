package gmx.fwd.service.content;

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

	public GmxResult addLangWork(ContentVo contentVo) { 
		
		if(contentVo.getReqName() == null || contentVo.getReqLang() == null) return gmxResult.resultError("필수 파라미터 없음");

		if (contentMapper.checkLangWork(contentVo.getReqLang())) {
			return gmxResult.resultError("중복된 요청 건이 존재합니다");
		}

		contentMapper.addLangWork(contentVo);
		return gmxResult.result(true);

	}

	public GmxResult getLangWorkItem(int mgrSeq) {
		List<ContentVo> getLangWorkItem = contentMapper.getLangWorkItem(mgrSeq);
		return getLangWorkItem.isEmpty() ? gmxResult.resultError("조회 실패") : gmxResult.result(getLangWorkItem);

	}

	public GmxResult getOrSearchLangWork(ContentVo contentVo) { // 필수 파라미터 검색 값
		List<ContentVo> getLangWorkList = contentMapper.getOrSearchLangWork(contentVo);
		return getLangWorkList.isEmpty() ? gmxResult.resultError("조회 실패") : gmxResult.result(getLangWorkList);
	}

	public GmxResult modifyLangWork(ContentVo contentVo) { // 필수 파라미터 req_name, req_lang
		if(contentVo.getReqName() == null || contentVo.getReqLang() == null) return gmxResult.resultError("필수 파라미터 없음");
		return contentMapper.modifyLangWork(contentVo) ? gmxResult.result(true) : gmxResult.resultError("번역 실패");
	}

	public GmxResult deleteLangWork(int mgrSeq) {
		return contentMapper.deleteLangWork(mgrSeq) ? gmxResult.result(true) : gmxResult.resultError("삭제 실패");
	}

}
