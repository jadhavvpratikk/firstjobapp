package com.secure.firstjobapp.review.impl;

import com.secure.firstjobapp.company.Company;
import com.secure.firstjobapp.company.CompanyService;
import com.secure.firstjobapp.review.Review;
import com.secure.firstjobapp.review.ReviewRepository;
import com.secure.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    //To get instance of company service
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
            Company company = companyService.getCompanyById(companyId);
            if(company != null){
                review.setCompany(company);
                reviewRepository.save(review);
                return true;
            }else{
                return false;
            }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                      .filter(review -> review.getId().equals(reviewId))
                      .findFirst()
                      .orElse(null);
    }
}
