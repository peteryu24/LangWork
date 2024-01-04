package gmxlang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import gmx.fwd.controller.content.ContentController;
import gmx.fwd.service.content.ContentService;
import gmx.fwd.utils.CustomException;
import gmx.fwd.utils.GmxResult;
import gmx.fwd.vo.contentvo.ContentVo;

@RunWith(MockitoJUnitRunner.class)
public class WorkLangTest {

	@Mock
	private ContentService contentService;

	@InjectMocks
	private ContentController contentController;

	@Before
	public void setUp() {
	}

	@Test
	public void runAddLangWork() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
		GmxResult expected = new GmxResult().result(true);
		when(contentService.addLangWork(contentVo)).thenReturn(expected);
		GmxResult result = contentController.addLangWork(contentVo);
		assertEquals(expected, result);
	}

	@Test
	public void runAddLangWorkException() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
		when(contentService.addLangWork(contentVo)).thenThrow(new CustomException("Error"));
		GmxResult result = contentController.addLangWork(contentVo);
		assertFalse(result.chkFlag());
		assertEquals("Error", result.getMsg());
		System.out.println(result.getMsg());
	}

	@Test
	public void runGetLangWorkItem() {
		int mgrSeq = 1;
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
		List<ContentVo> expectedList = Collections.singletonList(contentVo);
		GmxResult expected = new GmxResult().result(expectedList);
		when(contentService.getLangWorkItem(mgrSeq)).thenReturn(expected);
		GmxResult result = contentController.getLangWorkItem(mgrSeq);
		assertEquals(expected, result);
	}

	@Test
	public void runGetLangWorkItemException() {
		int mgrSeq = 1;
		when(contentService.getLangWorkItem(mgrSeq)).thenThrow(new CustomException("Error"));
		GmxResult result = contentController.getLangWorkItem(mgrSeq);
		assertFalse(result.chkFlag());
		assertEquals("Error", result.getMsg());
	}

	@Test
	public void runGetOrSearchLangWork() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
		List<ContentVo> expectedList = Collections.singletonList(contentVo);
		GmxResult expected = new GmxResult().result(expectedList);
		when(contentService.getOrSearchLangWork(contentVo)).thenReturn(expected);
		GmxResult result = contentController.getOrSearchLangWork(contentVo);
		assertEquals(expected, result);
	}

	@Test
	public void runGetOrSearchLangWorkException() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
		when(contentService.getOrSearchLangWork(contentVo)).thenThrow(new CustomException("Error"));
		GmxResult result = contentController.getOrSearchLangWork(contentVo);
		assertFalse(result.chkFlag());
		assertEquals("Error", result.getMsg());
	}

	@Test
	public void runModifyLangWork() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
		GmxResult expected = new GmxResult().result(true);
		when(contentService.modifyLangWork(contentVo)).thenReturn(expected);
		GmxResult result = contentController.modifyLangWork(contentVo);
		assertEquals(expected, result);
	}

	@Test
	public void runModifyLangWorkException() {
		ContentVo contentVo = new ContentVo("reqName", "resName", "reqLang", "resLang", "note", 1, "00001");
	    String errorMessage = "Error";
	    when(contentService.modifyLangWork(contentVo)).thenThrow(new CustomException(errorMessage));
	    GmxResult result = contentController.modifyLangWork(contentVo);
	    assertFalse(result.chkFlag());
	    assertEquals(errorMessage, result.getMsg());
	    System.out.println("Caught exception message: " + result.getMsg());
	}

	@Test
	public void runDeleteLangWork() {
		int mgrSeq = 1;
		GmxResult expected = new GmxResult().result(true);
		when(contentService.deleteLangWork(mgrSeq)).thenReturn(expected);
		GmxResult result = contentController.deleteLangWork(mgrSeq);
		assertEquals(expected, result);
	}

	@Test
	public void runDeleteLangWorkException() {
		int mgrSeq = 1;
		when(contentService.deleteLangWork(mgrSeq)).thenThrow(new CustomException("Error"));
		GmxResult result = contentController.deleteLangWork(mgrSeq);
		assertFalse(result.chkFlag());
		assertEquals("Error", result.getMsg());
	}
}
