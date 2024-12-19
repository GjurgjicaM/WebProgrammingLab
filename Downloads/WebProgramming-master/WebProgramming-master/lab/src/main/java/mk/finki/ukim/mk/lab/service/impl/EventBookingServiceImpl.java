package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.jpa.EventBookingRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    public final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(Long id, String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(id, eventName,attendeeName,attendeeAddress,(long)numberOfTickets);
        EventBooking savedBooking = eventBookingRepository.save(eventBooking);
        if (savedBooking.getEventName() == null) {
            throw new RuntimeException("Booking was not saved!");
        }
        return savedBooking;
    }

    @Override
    public List<EventBooking> listAll() {
        return this.eventBookingRepository.findAll();
    }

    @Override
    public Optional<EventBooking> findById(Long id) {
        return this.eventBookingRepository.findById(id);
    }

    @Override
    public List<EventBooking> searchEvents(String text) {
        return this.eventBookingRepository.findAll().stream().filter(b->b.getAttendeeName().contains(text)).toList();
    }


}
