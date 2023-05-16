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
        return new Dimensions(dimensions.getWidth(), dimensions.getHeight(), dimensions.getLength());
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

    public static class Dimensions {
        private final int width;
        private final int height;
        private final int length;

        public Dimensions(int width, int height, int length) {
            this.width = width;
            this.height = height;
            this.length = length;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getLength() {
            return length;
        }

        public int getVolume() {
            return width * height * length;
        }
    }
}
