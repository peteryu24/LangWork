package gmx.fwd.service.content;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmx.fwd.mapper.content.ContentMapper;
import gmx.fwd.vo.contentvo.ContentVo;

@Service
public class ContentService {

	@Autowired
	private ContentMapper contentMapper; // change post -> content

	public int addLangWork(String reqName, String resName, String reqLang, String resLang, String etcNote) {
		try {
			if (!contentMapper.checkLangWork(reqLang)) {
				return -1; // reqLang already exists
			}

			ContentVo content = new ContentVo();

			content.setReqName(reqName);
			content.setResName(resName);
			content.setReqLang(reqLang);
			content.setResLang(resLang);
			content.setEtcNote(etcNote);
			content.setCtyCode(1);
			content.setPrjCode("00001");

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

	public List<ContentVo> getOrSearchLangWork(String reqName, String resName, String reqLang, String resLang, String resFlag) {
		try {
			HashMap<String, String> searchKeyWord = new HashMap<>();
			searchKeyWord.put("reqName", reqName);
			searchKeyWord.put("resName", resName);
			searchKeyWord.put("reqLang", reqLang);
			searchKeyWord.put("resLang", resLang);
			searchKeyWord.put("resFlag", resFlag);

			return contentMapper.getOrSearchLangWork(searchKeyWord);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean modifyLangWork(int mgrSeq, String resName, String reqName, String reqLang, String resLang, String etcNote) {
		try {
			ContentVo content = new ContentVo();
			content.setMgrSeq(mgrSeq);
			content.setResName(resName);
			content.setReqName(reqName);
			content.setReqLang(reqLang);
			content.setResLang(resLang);
			content.setEtcNote(etcNote);

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
