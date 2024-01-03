package gmx.fwd.service.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmx.fwd.mapper.content.ContentMapper;
import gmx.fwd.vo.contentvo.ContentVo;

@Service
public class ContentService {

	@Autowired
	private ContentMapper contentMapper; // change post -> content

	@Autowired
	public ContentService(ContentMapper contentMapper) {
		this.contentMapper = contentMapper;
	}

	public int addLangWork(ContentVo contentVo) {
		try {
			if (!contentMapper.checkLangWork(contentVo.getReqLang())) {
				return -1; // reqLang already exists
			}

			ContentVo content = new ContentVo(contentVo.getReqName(), contentVo.getResName(), contentVo.getReqLang(),
					contentVo.getResLang(), contentVo.getEtcNote(), 1, "00001");
			return contentMapper.addLangWork(content);

		} catch (Exception e) {
			return 0;
		}
	}

	public List<ContentVo> getLangWorkItem(int mgrSeq) {
		try {
			return contentMapper.getLangWorkItem(mgrSeq);
		} catch (Exception e) {
			return null;
		}
	}

	public List<ContentVo> getOrSearchLangWork(ContentVo contentVo) {
		try {
			ContentVo content = new ContentVo(contentVo.getReqName(), contentVo.getResName(), contentVo.getReqLang(),
					contentVo.getResLang(), contentVo.getResFlag());

			return contentMapper.getOrSearchLangWork(content);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean modifyLangWork(ContentVo contentVo) {
		try {
			ContentVo content = new ContentVo(contentVo.getMgrSeq(), contentVo.getResName(), contentVo.getReqName(), contentVo.getReqLang(),
					contentVo.getResLang(), contentVo.getEtcNote(), 1, "00001");

			return contentMapper.modifyLangWork(content);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteLangWork(int mgrSeq) {
		try {
			return contentMapper.deleteLangWork(mgrSeq);
		} catch (Exception e) {
			return false;
		}
	}
}
