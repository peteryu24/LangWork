package gmxlang;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gmx.fwd.controller.content.ContentController;

public class WorkLangTest {

	@Autowired
	private ContentController contentController;

	@Test
	void addLangWorkTest() {
		try {
			contentController.addLangWork("Kim", "Park", "안녕하세요", "Hello", "etc");
			contentController.addLangWork("Kim", "", "안녕하세요", "", "etc");
			System.out.println("addLangWork success");
		} catch (Exception e) {
			System.out.println("addLangWork fail");
		}

	}

	@Test
	void getLangWorkItemTest() {
		try {
			contentController.getLangWorkItem(1);
			System.out.println("getLangWorkItem success");
		} catch (Exception e) {
			System.out.println("getLangWorkItem fail");
		}
	}

	@Test
	void getOrSearchLangWorkListTest() {
		try {
			contentController.getOrSearchLangWork("유경철1", "", "", "", "");
			contentController.getOrSearchLangWork("", "유경철2", "", "", "");
			contentController.getOrSearchLangWork("", "", "안녕", "", "");
			contentController.getOrSearchLangWork("", "", "", "Hello", "");
			contentController.getOrSearchLangWork("", "", "", "", "true");
			System.out.println("getOrSearchLangWorkList success");
		} catch (Exception e) {
			System.out.println("getOrSearchLangWorkList fail");
		}
	}

	@Test
	void modLangWorkTest() {
		try {
			contentController.modifyLangWork(1, "유경철1 mod", "", "", "", "");
			contentController.modifyLangWork(2, "", "유경철2 mod", "", "", "");
			contentController.modifyLangWork(2, "", "", "안녕 mod", "", "");
			contentController.modifyLangWork(2, "", "", "", "Hello mod", "");
			contentController.modifyLangWork(2, "", "", "", "", "modify test");
			System.out.println("modLangWork success");
		} catch (Exception e) {
			System.out.println("modLangWork fail");
		}
	}

	@Test
	void delLangWorkTest() {
		try {
			contentController.deleteLangWork(1);
			System.out.println("delLangWork success");
		} catch (Exception e) {
			System.out.println("delLangWork fail");
		}
	}
}
