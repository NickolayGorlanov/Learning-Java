import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();

        List<Flight> flightsLeavingInTheNextTwoHours = findPlanesLeavingInTheNextTwoHours(airport);
        flightsLeavingInTheNextTwoHours.forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date twoHoursFromNow = new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000);

        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE && flight.getDate().after(new Date()) && flight.getDate().before(twoHoursFromNow))
                .collect(Collectors.toList());
    }


}
