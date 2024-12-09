package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class SimplePersonEventHandler implements PersonDepartureEventHandler, PersonArrivalEventHandler {

   private Map <Id<Person>, Double> personToDepartureTime= new HashMap<>();
    @Override
    public void handleEvent(PersonDepartureEvent event) {
        var departureTime= event.getTime();
        var personId= event.getPersonId();
        personToDepartureTime.put(personId,departureTime);
    }

    @Override
    public void handleEvent(PersonArrivalEvent event) {
        var arrivalTime = event.getTime();
        var departureTime= personToDepartureTime.get(event.getPersonId());
        var travelTime = arrivalTime - departureTime;

        System.out.println("Person:" + event.getPersonId() + "travelled:" + travelTime + "s.");
    }


}
