# JavaLab3
# Lab3 Report by Mikhail Ivanov - Intersection 1

## Description

Requirements:
- Java 17+
- Source code

![cars_1](https://github.com/michailivanov/JavaLab3/assets/85007060/49377088-e4fd-48f5-b489-569204883f81)


I added all possible routes for cars in the model to make it more interesting.

## Main Work

I used IntelliJ IDEA as the development environment and Maven as the build tool to write the source code.

To implement the intersection model, I created the classes Car, SmartTrafficControl, and DirectionCalculator.

SmartTrafficControl contains a "queue" attribute, representing a list of cars currently at the intersection. The system allows movement for one car from the queue and all other vehicles that do not obstruct its path.

To determine if cars create obstructions, a method is introduced in the Car class: `isIntersect(Car car)`:

```java
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
```

This method uses a specific scheme to determine if there are intersections or not:

![res1](https://github.com/michailivanov/JavaLab3/assets/85007060/82c4e953-fe6a-4344-ae5f-80607470cb39)


To obtain values for forward, left, and right for any "start" direction, the DirectionCalculator class was implemented.

In the main section, two threads are initiated. The first thread adds 3 cars to the queue with random directions, while the second thread calls the method for intelligent intersection control (SmartTrafficControl), determining which cars are allowed to move.

## Verify Program Correctness

![res2](https://github.com/michailivanov/JavaLab3/assets/85007060/09f33ebb-ef5f-4419-8d1b-ed78c62d3988)


## Conclusion

This work focuses on studying and developing a model for a scenario with asynchronous interaction among its entities.
