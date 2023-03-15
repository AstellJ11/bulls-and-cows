package org.example.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO {

    /**
     * A method that takes in a message to display on the console,
     * and then waits for an answer from the user before continuing.
     *
     * @param prompt - String explaining what information you want from the user.
     * @return the answer to the message as string
     */
    String readString(String prompt);

    /**
     * A method that takes in a message to display on the console,
     * and then waits for an integer answer from the user before continuing.
     *
     * @param prompt - String explaining what information you want from the user.
     * @return the answer to the message as int
     */
    int readInt(String prompt);

    LocalDate readDate(String prompt);

    String readYN();


}
