package org.test.fizzbuzz;

import org.test.fizzbuzz.exception.FizzBuzzException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz implements IFizzBuzz {
	protected IntPredicate isDividesBy3 = i -> i % 3 == 0;
	protected IntPredicate isDividesBy5 = i -> i % 5 == 0;
	protected IntPredicate isDividedByNone = isDividesBy3
			.negate()
			.and(isDividesBy5.negate());

	protected Function<Integer, String> processNum = num -> {
		StringBuffer bfr = new StringBuffer();

		if (isDividesBy3.test(num)) {
			bfr.append("Fizz");
		}
		if (isDividesBy5.test(num)) {
			bfr.append("Buzz");
		}
		if (isDividedByNone.test(num)) {
			bfr.append(num);
		}

		return bfr.toString();
	};

	public List<String> getFizzBuzz(Optional<Integer> optionalNumber) {
		Integer number = optionalNumber
				.orElseThrow(() -> new FizzBuzzException("Input number is invalid"));
		return IntStream.
				rangeClosed(1, number)
				.mapToObj(num -> processNum.apply(num))
				.peek(System.out::println)
				.collect(Collectors.toList());
	}
}