package br.com.lam.prod.jpa.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lam.prod.jpa.model.Booking;
import br.com.lam.prod.jpa.model.BookingRepository;



/**
 * @author Dinesh.Rajput
 *
 */
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingRepository bookingRepository;

	/**
	 * GET /create --> Create a new booking and save it in the database.
	 */
	@RequestMapping("/create")
	public Booking create(Booking booking) {
		booking.setTravelDate(new Date());
		booking = bookingRepository.save(booking);
		return booking;
	}

	/**
	 * GET /read --> Read a booking by booking id from the database.
	 */
	@RequestMapping("/read")
	public Booking read(@RequestParam Long bookingId) {
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		return booking.get();
	}

	/**
	 * GET /update --> Update a booking record and save it in the database.
	 */
	@RequestMapping("/update")
	public Booking update(@RequestParam Long bookingId, @RequestParam String psngrName) {
		Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
		Booking booking = bookingOpt.get();
		booking.setPsngrName(psngrName);
		booking = bookingRepository.save(booking);
		return booking;
	}
//
//	/**
//	 * GET /delete --> Delete a booking from the database.
//	 */
//	@RequestMapping("/delete")
//	public String delete(@RequestParam Long bookingId) {
//		bookingRepository.delete(bookingId);
//		return "booking #" + bookingId + " deleted successfully";
//	}
}