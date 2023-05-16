import java.util.Scanner;

public class Elevator {
    private int currentFloor;
    private final int minFloor;
    private final int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = 1;
    }

    public final class Cargo {
        private final String deliveryAddress;
        private final Dimensions dimensions;
        private final double mass;
        private final boolean isFlipAllowed;
        private final String registrationNumber;
        private final boolean isFragile;

        public Cargo(String deliveryAddress, Dimensions dimensions, double mass,
                     boolean isFlipAllowed, String registrationNumber, boolean isFragile) {
            this.deliveryAddress = deliveryAddress;
            this.dimensions = dimensions;
            this.mass = mass;
            this.isFlipAllowed = isFlipAllowed;
            this.registrationNumber = registrationNumber;
            this.isFragile = isFragile;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public Dimensions getDimensions() {
            return new Dimensions(dimensions.width(), dimensions.height(), dimensions.length());
        }

        public double getMass() {
            return mass;
        }

        public boolean isFlipAllowed() {
            return isFlipAllowed;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public boolean isFragile() {
            return isFragile;
        }

        public Cargo withDeliveryAddress(String newDeliveryAddress) {
            return new Cargo(newDeliveryAddress, dimensions, mass, isFlipAllowed, registrationNumber, isFragile);
        }

        public Cargo withDimensions(Dimensions newDimensions) {
            return new Cargo(deliveryAddress, newDimensions, mass, isFlipAllowed, registrationNumber, isFragile);
        }

        public Cargo withMass(double newMass) {
            return new Cargo(deliveryAddress, dimensions, newMass, isFlipAllowed, registrationNumber, isFragile);
        }

        public record Dimensions(int width, int height, int length) {

            public int getVolume() {
                        return width * height * length;
                    }
                }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        if (currentFloor > minFloor)
            currentFloor--;
    }

    public void moveUp() {
        if (currentFloor < maxFloor)
            currentFloor++;
    }

    public void move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Ошибка: этаж выходит за пределы допустимого диапазона!");
            return;
        }
        while (currentFloor != floor) {
            if (currentFloor > floor) {
                moveDown();
            } else {
                moveUp();
            }
            System.out.printf("Текущий этаж: %d\n", currentFloor);
        }
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(-3, 26);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите номер этажа: ");
            int floor = scanner.nextInt();
            elevator.move(floor);
        }
    }
}



