package org.test.fizzbuzz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.test.fizzbuzz.exception.FizzBuzzException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {
	private FizzBuzz fizzBuzz = null;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		fizzBuzz = new FizzBuzz();
	}

	@Test
	public void shouldGetTheFizzBuzz() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data.dat").getFile());
		String absolutePath = file.getAbsolutePath();

		List<String> expectedList = Files.readAllLines(Paths.get(absolutePath));
		assertThat(fizzBuzz.getFizzBuzz(Optional.of(100))).isEqualTo(expectedList);
	}

	@Test
	public void shouldThrowExceptionWhenGetTheFizzBuzzWithInvalidRange(){
		exception.expect(FizzBuzzException.class);
		exception.expectMessage("Input number is invalid");
		fizzBuzz.getFizzBuzz(Optional.empty());
	}

	@Test
	public void shouldReturnFizz(){
		assertThat(fizzBuzz.processNum.apply(3)).isEqualTo("Fizz");
	}

	@Test
	public void shouldReturnBuzz(){
		assertThat(fizzBuzz.processNum.apply(5)).isEqualTo("Buzz");
	}

	@Test
	public void shouldReturnFizzBuzz(){
		assertThat(fizzBuzz.processNum.apply(15)).isEqualTo("FizzBuzz");
	}

	@Test
	public void shouldReturnNumber(){
		assertThat(fizzBuzz.processNum.apply(11)).isEqualTo("11");
	}

	@Test
	public void shouldBeDividedBy3(){
		assertThat(fizzBuzz.isDividesBy3.test(3)).isTrue();
	}

	@Test
	public void shouldNotBeDividedBy3(){
		assertThat(fizzBuzz.isDividesBy3.test(5)).isFalse();
	}

	@Test
	public void shouldBeDividedBy5(){
		assertThat(fizzBuzz.isDividesBy5.test(5)).isTrue();
	}

	@Test
	public void shouldNotBeDividedBy5(){
		assertThat(fizzBuzz.isDividesBy5.test(6)).isFalse();
	}

	@Test
	public void shouldBeDividedBy3And5(){
		assertThat(fizzBuzz.isDividedByNone.test(11)).isTrue();
	}

	@Test
	public void shouldNotBeDividedBy3And5(){
		assertThat(fizzBuzz.isDividedByNone.test(15)).isFalse();
	}

}
