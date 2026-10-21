
@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class UrgentSMS implements Notifier, Urgent {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class NotificationDriver {
    public static void main(String[] args) {
        Notifier email = message -> System.out.println("Email: " + message);
        Notifier sms = new UrgentSMS();

        Notifier[] senders = {email, sms};
        String message = "Your class starts at 10 AM";

        for (Notifier n : senders) {
            n.send(message);

            if (n instanceof Urgent) {
                n.send(message);
            }
        }
    }
}
