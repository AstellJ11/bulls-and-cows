package org.example.view;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class UserIOConsoleImpl implements UserIO {

    final private Scanner console = new Scanner(System.in);

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        boolean validInput = true;
        int num = 0;

        while (validInput) {
            try {
                String stringValue = this.readString(prompt);  // Print prompt
                num = Integer.parseInt(stringValue);
                validInput = false;

            } catch (NumberFormatException e) {
                System.out.println(("Input error. Not a number. Please try again."));  // If not a number
            }
        }

        return num;
    }

    @Override
    public String readGuess(String prompt) {
        String string = null;
        boolean validInput = true;

        do {
            String stringInput = readString(prompt);

            if (stringInput.length() == 4) {
                string = stringInput;
                validInput = true;
            } else {
                System.out.println("Input error. Please enter a guess in the correct format.");
                validInput = false;
            }
        } while (!validInput);

        return string;
    }


}
