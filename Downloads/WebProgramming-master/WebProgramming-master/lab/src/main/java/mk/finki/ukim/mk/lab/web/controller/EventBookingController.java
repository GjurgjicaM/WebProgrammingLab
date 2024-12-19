package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    private final EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }

    @GetMapping
    private String getEventBookingPage() {
        return "bookingConfirmation";
    }

    @PostMapping("/book")
    private String bookEvent(@RequestParam String attName,
                             @RequestParam String idEvent,
                             @RequestParam int numTickets,
                             HttpServletRequest request) {
        Optional<Event> optionalEvent = this.eventService.findById(Long.parseLong(idEvent));
        if (optionalEvent.isEmpty()) {
            return "redirect:/events?error=EventNotFound";
        }
        Event event = optionalEvent.get();

        if ((event.getNumTickets() - numTickets < 0)) {
            return "redirect:/events?error=InsufficientTickets";
        }

        event.setNumTickets((event.getNumTickets() - numTickets));
        eventBookingService.placeBooking(event.getId(), event.getName(), attName, request.getRemoteAddr(), numTickets);

        request.getSession().setAttribute("myBookings", this.eventBookingService.listAll());
        return "redirect:/eventBooking";
    }
}
