package edu.noia.myoffice.customer.data.test.util;

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
        return CustomerSample.of(
                randomString8.nextString(),
                randomString8.nextString(),
                randomString8.nextString(),
                randomString8.nextString())
                .setSalutation(randomString8.nextString())
                .setFirstName(randomString8.nextString())
                .setBirthDate(LocalDate.now().minusYears(randomInt(80)))
                .setStreetNo(String.format("%s %d", randomString8.nextString(), randomInt(10)))
                .setRegion(randomString8.nextString())
                .setPhoneNumber1(PhoneNumber.of(randomSwissPhoneNumber(), PhoneNumber.Kind.PRIVATE))
                .setEmailAddress1(EmailAddress.of(randomEmailAddress(), EmailAddress.Kind.PRIVATE))
                .setWebsiteUrl(randomWebsiteUrl())
                .setSocial(Social.of(
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString(),
                        randomString8.nextString()))
                .setProfile(Profile.of(false, false, false))
                .setNotes(randomString8.nextString());
    }

    public static Customer random() {
        return Customer.ofValid(CustomerId.random(), randomValid());
    }

    private static String randomSwissPhoneNumber() {
        return String.format("+4179%d", randomInt(1000000, 9999999));
    }

    private static String randomEmailAddress() {
        return String.format("%s@%s.com", randomString8.nextString(), randomString8.nextString());
    }

    private static String randomWebsiteUrl() {
        return String.format("http://www.%s.com", randomString8.nextString());
    }

    private static int randomInt(int limit) {
        return ThreadLocalRandom.current().nextInt(limit);
    }

    private static int randomInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
