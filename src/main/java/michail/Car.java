package michail;

public class Car {
    private String direction; // Direction of a car ("SN", "NS", "WE", "EW", "ES")
    // S - South, N - North, W - West, E - East; FROM/TO

    public Car(String direction){
        this.direction = direction;
    }

    public String getDirection(){
        return this.direction;
    }

    public boolean isIntersect(Car car){
        String otherCar = car.getDirection();

        if(car == null || otherCar.equals(this.direction)){
            return false;
        }

        String start = Character.toString(this.direction.charAt(0));
        String finish = Character.toString(this.direction.charAt(1));

        // Calculate what's direction is forward, left, right for a given 'start' direction
        DirectionCalculator dirCal = new DirectionCalculator(start);
        String forward = dirCal.getForward();
        String left = dirCal.getLeft();
        String right = dirCal.getRight();

        // A main logic for intersect function, I've created images to visualize it.
        if(finish.equals(forward)) { // finish is forward
            return !(otherCar.equals(forward + left) || otherCar.equals(forward + start) || otherCar.equals(left + start));
        }
        else if(finish.equals(left)){ // finish is left
            return !(otherCar.equals(left + start));
        }
        else if(finish.equals(right)){ // finish is right
            return !(otherCar.equals(right + forward) || otherCar.equals(right + left) || otherCar.equals(right + start)
                    || otherCar.equals(forward + start) || otherCar.equals(forward + left) || otherCar.equals(left + start));
        }
        else
        {
            throw new IllegalArgumentException("Invalid finish direction: " + finish);
        }
    }

    public String toString(){
        return "[" + this.direction + "]";
    }
}
