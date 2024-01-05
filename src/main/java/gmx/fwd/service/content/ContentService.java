package gmx.fwd.service.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmx.fwd.mapper.content.ContentMapper;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.utils.exception.LangWorkItemNotFoundException;
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

		if (contentMapper.checkLangWork(contentVo.getReqLang())) {
			return gmxResult.resultError("중복된 요청 건이 존재합니다", null);
		}

		contentMapper.addLangWork(contentVo);
		return gmxResult.result(true);
	}

	public GmxResult getLangWorkItem(int mgrSeq) {
		List<ContentVo> getLangWorkItem = contentMapper.getLangWorkItem(mgrSeq);
		if (getLangWorkItem.isEmpty()) {
			throw new LangWorkItemNotFoundException(mgrSeq);
		}
		return gmxResult.result(getLangWorkItem);
	}

	public GmxResult getOrSearchLangWork(ContentVo contentVo) {
		List<ContentVo> getLangWorkList = contentMapper.getOrSearchLangWork(contentVo);
		return getLangWorkList.isEmpty() ? gmxResult.resultError("조회 실패", null) : gmxResult.result(getLangWorkList);
	}

	public GmxResult modifyLangWork(ContentVo contentVo) {
		return contentMapper.modifyLangWork(contentVo) ? gmxResult.result(true) : gmxResult.resultError("번역 실패", null);
	}

	public GmxResult deleteLangWork(int mgrSeq) {
		return contentMapper.deleteLangWork(mgrSeq) ? gmxResult.result(true) : gmxResult.resultError("삭제 실패", null);
	}

}
