package notes.test;

import org.junit.Test;

import notes.Main;

public class MainTest {

	@Test
	public void test() {
		Main.createContentFile("data", "data/content.json");
	}

}
