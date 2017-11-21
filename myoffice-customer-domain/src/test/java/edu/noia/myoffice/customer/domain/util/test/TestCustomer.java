package edu.noia.myoffice.customer.domain.util.test;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCustomer {

    private static RandomString randomString8 = new RandomString(10, ThreadLocalRandom.current());

    public static CustomerState randomValid() {
        return CustomerSample.builder(
                randomString8.nextString(),
                randomString8.nextString(),
                randomString8.nextString(),
                randomString8.nextString())
                .salutation(randomString8.nextString())
                .firstName(randomString8.nextString())
                .birthDate(LocalDate.now().minusYears(randomInt(80)))
                .streetNo(String.format("%s %d", randomString8.nextString(), randomInt(10)))
                .region(randomString8.nextString())
                .phoneNumber1(PhoneNumber.of(randomSwissPhoneNumber(), PhoneNumber.Kind.PRIVATE))
                .emailAddress1(EmailAddress.of(randomEmailAddress(), EmailAddress.Kind.PRIVATE))
                .websiteUrl(randomWebsiteUrl())
                .social(Social.of(
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString()))
                .profile(Profile.of(false, false, false))
                .notes(randomString8.nextString())
                .build();
    }

    public static Customer random() {
        return Customer.ofValid(CustomerId.random(), randomValid());
    }

    static String randomSwissPhoneNumber() {
        return String.format("+4179%d", randomInt(1000000, 9999999));
    }

    static String randomEmailAddress() {
        return String.format("%s@%s.com", randomString8.nextString(), randomString8.nextString());
    }

    static String randomWebsiteUrl() {
        return String.format("http://www.%s.com", randomString8.nextString());
    }

    static int randomInt(int limit) {
        return ThreadLocalRandom.current().nextInt(limit);
    }

    static int randomInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
