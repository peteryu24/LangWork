package gmxlang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

		// Mockito
		when(mockMapper.checkLangWork(anyString())).thenReturn(true);
		when(mockMapper.addLangWork(any(ContentVo.class))).thenReturn(1);
		int result = contentService.addLangWork(contentVo);
		assertEquals(1, result);
	}

	@Test
	public void addLangWorkDuplicationTest() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");

		// Mockito
		when(mockMapper.checkLangWork(anyString())).thenReturn(false);
		int result = contentService.addLangWork(contentVo);
		assertEquals(-1, result);
	}

	@Test
	public void getLangWorkItemSuccessTest() {
		List<ContentVo> expectedList = new ArrayList<>();
		expectedList.add(new ContentVo(0, null, null, null, null, null, 1, null));
		when(mockMapper.getLangWorkItem(anyInt())).thenReturn(expectedList); // Mockito

		List<ContentVo> result = contentService.getLangWorkItem(1);
		assertFalse(result.isEmpty());
	}

	@Test
	public void getLangWorkItemFailureTest() {
		when(mockMapper.getLangWorkItem(anyInt())).thenReturn(Collections.emptyList());

		List<ContentVo> result = contentService.getLangWorkItem(1);
		assertTrue(result.isEmpty());
	}

	@Test
	public void getOrSearchLangWorkTest() {
		ContentVo contentVo = new ContentVo(null, null, null, null, false);

		List<ContentVo> expectedList = new ArrayList<>();
		expectedList.add(contentVo);
		when(mockMapper.getOrSearchLangWork(any(ContentVo.class))).thenReturn(expectedList);
		List<ContentVo> result = contentService.getOrSearchLangWork(contentVo);
		assertFalse(result.isEmpty());
	}

	@Test
	public void modifyLangWorkTest() {
		when(mockMapper.modifyLangWork(any(ContentVo.class))).thenReturn(true);
		ContentVo contentVo = new ContentVo(0, null, null, null, null, null, 1, null);
		boolean result = contentService.modifyLangWork(contentVo);
		assertTrue(result);
	}

	@Test
	public void deleteLangWorkTest() {
		when(mockMapper.deleteLangWork(anyInt())).thenReturn(true);

		boolean result = contentService.deleteLangWork(1);
		assertTrue(result);
	}
}
