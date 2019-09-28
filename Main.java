import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Airport airport = Airport.getInstance();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime afterTwoHours = currentTime.plusHours(2);
        Date date = new Date();
        System.out.println("Текущее время в формате Java.util.Date: " + date);

        //////////////////////////////Пример конвертирования Date в LocalDateTime///////////////////////////////////////
        ZoneId defaultZoneID = ZoneId.systemDefault();
        System.out.println("Часовой пояс системы: " + defaultZoneID);

        //Конвертирование Date в Instant
        Instant instant = date.toInstant();
        System.out.println("Instant: " + instant); //Default zone: UTC +0

        //Instant + system default time zone + toLocalDateTime() = LocalDateTime
        LocalDateTime localDateTime = instant.atZone(defaultZoneID).toLocalDateTime();
        System.out.println("LocalDateTime: " + localDateTime);

        //////////////////////////////Решение домашнего задания/////////////////////////////////////////////////////////
        airport.getTerminals().forEach(terminal -> terminal.getFlights().stream().
                filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE)).
                filter(flight -> flight.getDate().toInstant().atZone(defaultZoneID).toLocalDateTime().isAfter(currentTime)).
                filter(flight -> flight.getDate().toInstant().atZone(defaultZoneID).toLocalDateTime().isBefore(afterTwoHours)).
                forEach(System.out::println));

    }
}
