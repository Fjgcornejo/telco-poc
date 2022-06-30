package com.minsait.onesait;

import java.util.Random;

public class ManagerAEntrypoint {

	public String handleRequest(String input) {
		final Integer total = input == null || input.isEmpty() ? 1000 : Integer.valueOf(input);
		final int drop = 17;

		boolean found = false;
		int iterations = 0;
		boolean lucky = false;

		while (!found) {
			final Integer guessed = new Random().nextInt(total);
			if (guessed == drop) {
				found = true;
			}
			if (iterations >= total * 100) {
				found = true;
			}
			iterations++;
		}
		if (iterations < 190) {
			lucky = true;
		}

		System.out.println("Mount drop after " + iterations + " tries.");
		String output = "Mount drop after " + iterations + " tries, with special number being: " + drop;
		if (lucky) {
			output = output + " ...and you are lucky!!!";
		}
		return output;
	}

}
