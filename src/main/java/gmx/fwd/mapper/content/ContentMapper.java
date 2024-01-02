package gmx.fwd.mapper.content;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import gmx.fwd.vo.contentvo.ContentVo;

@Mapper
public interface ContentMapper {

	int addLangWork(ContentVo content);

	List<ContentVo> getLangWorkItem(int mgrSeq);
	
	List<ContentVo> getOrSearchLangWork(HashMap<String, String> searchKeyWord);
	
	boolean modifyLangWork(ContentVo content);

	boolean deleteLangWork(int mgrSeq);

	boolean checkLangWork(String reqLang);

}
