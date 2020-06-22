package uTest;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import code.NovelWriter;

import org.hamcrest.CoreMatchers;

public class NovelWriterTest {
	public static NovelWriter novelWriter;

	@BeforeClass
	public static void beforeClass() {
		novelWriter = new NovelWriter();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void privateCountLetters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		List<String> input = Arrays.asList("Foo", "Foobar123", "Foo Bar Baz");

		Method method = NovelWriter.class.getDeclaredMethod("countLetters", List.class);
		method.setAccessible(true);
		List<Integer> output = (List<Integer>) method.invoke(novelWriter, input);

		assertThat(output, CoreMatchers.notNullValue());
		assertThat(output.size(), CoreMatchers.is(3));
		assertThat(output, CoreMatchers.hasItems(3, 6, 9));
	}

	@Test
	public void checkPrivateStringTest() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Method method = NovelWriter.class.getDeclaredMethod("checkPrivateString");	//no parmatters you dont need to add param
		method.setAccessible(true);
		// test standart route
		String expected = "OK";
		String actual = (String) method.invoke(novelWriter);
		assertEquals(expected, actual);
		// alter the private field route
		Field field = NovelWriter.class.getDeclaredField("strForTesting");
		field.setAccessible(true);
		field.set(novelWriter, "Not");
		expected = "NOT";
		actual = (String) method.invoke(novelWriter);
		assertEquals(expected, actual);
	}

	@Test
	public void shoutTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = NovelWriter.class.getDeclaredMethod("shout", String.class);
		method.setAccessible(true);
		// test without regex
		String input =  "hi";
		String expected = "HI";
		String actual = (String) method.invoke(novelWriter,input);
		assertEquals(expected, actual);
		// test with regex
		input =  "hi.";
		expected = "HI!";
		actual = (String) method.invoke(novelWriter,input);
		assertEquals(expected, actual);
	}
}
