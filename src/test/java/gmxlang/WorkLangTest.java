package gmxlang;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gmx.fwd.mapper.content.ContentMapper;
import gmx.fwd.service.content.ContentService;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.vo.contentvo.ContentVo;

@RunWith(MockitoJUnitRunner.class)
public class WorkLangTest {

	private ContentService contentService;
	private ContentMapper mockMapper;

	@Before
	public void setUp() {
		mockMapper = mock(ContentMapper.class);
		contentService = new ContentService(mockMapper);
	}

	@Test
	public void addLangWorkTest() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");

		when(mockMapper.checkLangWork(anyString())).thenReturn(false);
		when(mockMapper.addLangWork(any(ContentVo.class))).thenReturn(1);

		GmxResult result = contentService.addLangWork(contentVo);
		assertTrue(result.chkFlag());
		assertNull(result.getMsg());
		assertNotNull(result.getData());
	}

	@Test
	public void addLangWorkDuplicationTest() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");

		when(mockMapper.checkLangWork(anyString())).thenReturn(true);

		GmxResult result = contentService.addLangWork(contentVo);
		assertFalse(result.chkFlag());
		assertEquals("중복된 요청 건이 존재합니다", result.getMsg());
	}

	@Test
	public void getLangWorkItemSuccessTest() {
		List<ContentVo> expectedList = new ArrayList<>();
		expectedList.add(new ContentVo(0, null, null, null, null, null, 1, null));
		when(mockMapper.getLangWorkItem(anyInt())).thenReturn(expectedList);

		GmxResult result = contentService.getLangWorkItem(1);
		assertTrue(result.chkFlag());
		assertNull(result.getMsg());
		assertNotNull(result.getData());
		assertEquals(expectedList, result.getData());
	}

	@Test
	public void getLangWorkItemFailureTest() {
		when(mockMapper.getLangWorkItem(anyInt())).thenReturn(Collections.emptyList());

		GmxResult result = contentService.getLangWorkItem(1);
		assertFalse(result.chkFlag());
		assertEquals("조회 실패", result.getMsg());
	}

	@Test
	public void getOrSearchLangWorkTest() {
		ContentVo contentVo = new ContentVo(null, null, null, null, false);
		List<ContentVo> expectedList = new ArrayList<>();
		expectedList.add(contentVo);
		when(mockMapper.getOrSearchLangWork(any(ContentVo.class))).thenReturn(expectedList);

		GmxResult result = contentService.getOrSearchLangWork(contentVo);
		assertTrue(result.chkFlag());
		assertNull(result.getMsg());
		assertNotNull(result.getData());
		assertEquals(expectedList, result.getData());
	}

	@Test
	public void modifyLangWorkTest() {
		ContentVo contentVo = new ContentVo(0, null, null, null, null, null, 1, null);
		when(mockMapper.modifyLangWork(any(ContentVo.class))).thenReturn(true);

		GmxResult result = contentService.modifyLangWork(contentVo);
		assertTrue(result.chkFlag());
		assertNull(result.getMsg());
	}

	@Test
	public void deleteLangWorkTest() {
		when(mockMapper.deleteLangWork(anyInt())).thenReturn(true);

		GmxResult result = contentService.deleteLangWork(1);
		assertTrue(result.chkFlag());
		assertNull(result.getMsg());
	}

}
