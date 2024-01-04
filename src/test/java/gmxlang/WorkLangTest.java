package gmxlang;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    }
}
