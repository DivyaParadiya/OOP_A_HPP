
interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

interface SwitchRule {
    boolean canSwitchOn(Switchable device, int hour);
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

public class RemoteDriver {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};

        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchRule rule1 = new SwitchRule() {
            public boolean canSwitchOn(Switchable device, int hour) {
                return hour >= 8 && hour <= 22;
            }
        };

        SwitchRule rule2 = (device, hour) -> hour >= 8 && hour <= 22;

        System.out.println("Anonymous rule: " + rule1.canSwitchOn(devices[0], 10));
        System.out.println("Lambda rule: " + rule2.canSwitchOn(devices[1], 23));
    }
}
