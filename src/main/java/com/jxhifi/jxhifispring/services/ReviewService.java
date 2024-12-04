package com.jxhifi.jxhifispring.services;

import com.jxhifi.jxhifispring.entities.Review;
import com.jxhifi.jxhifispring.repositories.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private static long idNumber = 1L;
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Initializes the idNumber with the next available unique identifier.
     * This method runs after the bean's properties have been initialized.
     */
    @PostConstruct
    private void initNumber() {
        Optional<Review> lastProductOptional = this.reviewRepository.findTopByOrderByIdDesc();

        if (lastProductOptional.isPresent()) {
            String lastId = lastProductOptional.get().getId();
            idNumber = Long.parseLong(lastId.substring(3));
        }
    }

    /**
     * Retrieves a list of all reviews from the repository.
     *
     * @return a list of Review objects representing all reviews in the repository.
     */
    List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves a review by its unique identifier from the repository.
     *
     * @param id the unique identifier of the review.
     * @return an Optional containing the Review if found, or empty if not found.
     */
    public Optional<Review> getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    /**
     * Creates a new review entry in the repository with a uniquely generated identifier.
     *
     * @param review the Review object to be saved, which will have its ID set internally.
     * @return the saved Review object with the newly assigned ID.
     */
    public Review createNewReview(Review review) {
        review.setId("REV" + generateNewId());
        return reviewRepository.save(review);
    }

    /**
     * Generates a new unique identifier for products.
     *
     * @return a long value representing the new unique product identifier.
     */
    private synchronized long generateNewId() {
        return idNumber + 1;
    }
}
