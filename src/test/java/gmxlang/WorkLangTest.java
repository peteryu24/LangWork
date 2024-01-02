package gmxlang;

import static org.mockito.Mockito.*;
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
    public void testAddLangWork_Success() {
        when(mockMapper.checkLangWork(anyString())).thenReturn(true);
        when(mockMapper.addLangWork(any(ContentVo.class))).thenReturn(1);

        int result = contentService.addLangWork("reqName", "resName", "reqLang", "resLang", "note");
        assertEquals(1, result);
    }

}



