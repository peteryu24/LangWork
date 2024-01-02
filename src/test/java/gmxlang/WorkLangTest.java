package gmxlang;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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

}
