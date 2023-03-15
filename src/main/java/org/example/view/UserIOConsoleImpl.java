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
    public LocalDate readDate(String prompt) {
        LocalDate date = null;
        boolean validInput = true;

        do {
            try {
                System.out.println(prompt);
                String stringInput = console.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                date = LocalDate.parse(stringInput, formatter);
                validInput = true;

            } catch (DateTimeException e) {
                System.out.println("Input error. Date is not in the correct format. Please try again.");
                validInput = false;
            }
        } while (!validInput);

        return date;
    }

    @Override
    public String readYN() {
        String string = null;
        boolean validInput = true;

        do {
            String stringInput = console.nextLine();

            if (stringInput.equals("Y") || stringInput.equals("N")) {
                string = stringInput;
                validInput = true;
            } else {
                System.out.println("Input error. Please enter Y or N.");
                validInput = false;
            }
        } while (!validInput);

        return string;
    }


}
