package moebiusSolutionsInc_TestCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
/**
 * The following mock program passes on our development machines,
 * but the client reports that it fails on their 64MB VM.
 * <p>
 * 1. Run on a 64M VM and copy the failing stack trace
 * 2. Modify main() to work on a 64MB VM.
 * 3. Ensure at most one performance warning.
 * <p>
 * (To set 64Mb VM) run using java -Xmx64M SmallMemoryMessageTest
 */

/** A given interface to process messages (DO NOT CHANGE) **/
interface MessageProcessor {
    void processMessage(Message msg);
}

/** A given interface to archive select messages (DO NOT CHANGE) **/
interface MessageArchiver {
    void archiveMessages(List<Message> messages, Predicate<Message> filter);
}

/** A given class to represent a message (DO NOT CHANGE) **/
class Message {
    private String subject;
    private String body;

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "subject: " + Util.abbreviate(subject, 20) +
                ", body: " + Util.abbreviate(body, 40) + "}";
    }
}

/**
 * Test class that works on developer boxes but fails on small VMs.
 *
 * Fix this class to work on 64Mb VM
 */
public class SmallMemoryMessageTest {
    public static void main(String []args) {
        MessageProcessor processor = Util.createMessageProcessor();
        MessageArchiver archiver = Util.createMessageArchiver();
        List<Message> messages = new ArrayList<>(Util.EXPECTED_TOTAL);
        for (int i = 0; i < Util.EXPECTED_TOTAL; i++) {
            Message msg = Util.random();
            processor.processMessage(msg);
            messages.add(msg);
        }
        archiver.archiveMessages(messages, m -> m.getSubject().startsWith("A"));
        /*
         * DO NOT CHANGE ANYTHING BELOW THIS LINE.
         * PROGRAM MUST EXIT SUCCESSFULLY
         */
        Util.validate();
    }


}

/** A given utility class (DO NOT CHANGE) **/
class Util {
    static final int EXPECTED_TOTAL = 98765;
    static final int EXPECTED_ARCHIVED = 3799;

    static Message random() {
        String subject = randomSubject();
        String body = randomBody();
        Message m = new Message(subject, body);
        return m;
    }

    static int count = 0;

    static String randomSubject() {
        StringBuilder sb = new StringBuilder(128);
        sb.append((char) ((int) 'A' + (count++ % 26)));
        Random r = new Random();
        while (sb.length() < 128) {
            char c = (char) r.nextInt(127);
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String randomBody() {
        StringBuilder sb = new StringBuilder(4096);
        Random r = new Random();
        while (sb.length() < 4096) {
            char c = (char) r.nextInt(127);
            if (!Character.isISOControl(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String abbreviate(String s, int n) {
        return s.length() > n + 3 ? s.substring(0, n - 3) + "..." : s;
    }

    static MessageProcessor createMessageProcessor() {
        return new TestProcessor();
    }

    static MessageArchiver createMessageArchiver() {
        return new TestArchiver();
    }

    static void validate() {
        if (TestArchiver.count != EXPECTED_TOTAL ||
                TestArchiver.archived != EXPECTED_ARCHIVED) {
            throw new IllegalStateException("Failed to archive all messages!");
        }
        System.out.println("SUCCESS. PROCESSED: " + TestArchiver.count + " ARCHIVED:" + TestArchiver.archived);
    }
}

/** Noop implementation of a message processor (DO NOT CHANGE) **/
class TestProcessor implements MessageProcessor {
    long count = 0;

    @Override
    public void processMessage(Message msg) {
        if (++count % 1000 == 0) {
            System.out.println("Processed: " + (count) + " Latest: " + msg);
            System.out.flush();
        }
    }
}

/** Noop implementation of a message archiver (DO NOT CHANGE) **/
class TestArchiver implements MessageArchiver {
    static int count = 0;
    long bytes = 0;
    static long archived = 0;

    @Override
    public void archiveMessages(List<Message> messages, Predicate<Message>
            filter) {
        if (messages.size() < 1000) {
            System.err.println("WARNING: message list too short, this will drastically reduce performance !");
        }
        messages.stream().filter(filter).forEach(this::archiveOne);
        System.out.println("Archived: " + bytes + " bytes.");
        count += messages.size();
        bytes = 0;
    }

    private void archiveOne(Message msg) {
        bytes += msg.getSubject().getBytes().length + 1;
        bytes += msg.getBody().getBytes().length;
        archived++;
    }
}