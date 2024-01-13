package michail;

public class DirectionCalculator {
    private String start;

    public DirectionCalculator(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public String getRight() {
        return switch (start) {
            case "S" -> "E";
            case "N" -> "W";
            case "W" -> "S";
            case "E" -> "N";
            default -> throw new IllegalArgumentException("Invalid start direction: " + start);
        };
    }

    public String getLeft() {
        return switch (start) {
            case "S" -> "W";
            case "N" -> "E";
            case "W" -> "N";
            case "E" -> "S";
            default -> throw new IllegalArgumentException("Invalid start direction: " + start);
        };
    }

    public String getForward() {
        return switch (start) {
            case "S" -> "N";
            case "N" -> "S";
            case "W" -> "E";
            case "E" -> "W";
            default -> throw new IllegalArgumentException("Invalid start direction: " + start);
        };
    }
}
