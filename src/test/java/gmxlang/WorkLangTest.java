package gmxlang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import gmx.fwd.mapper.content.ContentMapper;
import gmx.fwd.service.content.ContentService;
import gmx.fwd.vo.contentvo.ContentVo;


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
		when(mockMapper.checkLangWork(anyString())).thenReturn(true);

		when(mockMapper.addLangWork(any(ContentVo.class))).thenReturn(1); // mockito
		int result = contentService.addLangWork("reqName", "resName", "reqLang", "resLang", "note");
		assertEquals(1, result);
	}

	@Test
	public void addLangWorkDuplicationTest() { // duplication test

		when(mockMapper.addLangWork(any(ContentVo.class))).thenReturn(1);
		int result = contentService.addLangWork("reqName", "resName", "reqLang", "resLang", "note");
		assertEquals(-1, result);
	}

	@Test
	public void getLangWorkItemSuccessTest() {
		List<ContentVo> expectedList = new ArrayList<>();
		expectedList.add(new ContentVo());
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
		List<ContentVo> expectedList = new ArrayList<>();
		expectedList.add(new ContentVo());

		when(mockMapper.getOrSearchLangWork(argThat(new ArgumentMatcher<HashMap<String, String>>() {
			@Override
			public boolean matches(HashMap<String, String> argument) {
				return argument instanceof HashMap;
			}
		}))).thenReturn(expectedList);

		List<ContentVo> result = contentService.getOrSearchLangWork("reqName", "resName", "reqLang", "resLang", "resFlag");
		assertFalse(result.isEmpty());
	}

	@Test
	public void modifyLangWorkTest() {
		when(mockMapper.modifyLangWork(any(ContentVo.class))).thenReturn(true);

		boolean result = contentService.modifyLangWork(1, "resName", "reqName", "reqLang", "resLang", "note");
		assertTrue(result);
	}

	@Test
	public void deleteLangWorkTest() {
		when(mockMapper.deleteLangWork(anyInt())).thenReturn(true);

		boolean result = contentService.deleteLangWork(1);
		assertTrue(result);
	}

}
