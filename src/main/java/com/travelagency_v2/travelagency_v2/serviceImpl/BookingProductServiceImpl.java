package com.travelagency_v2.travelagency_v2.serviceImpl;

import com.travelagency_v2.travelagency_v2.repositories.BookingProductRepository;
import com.travelagency_v2.travelagency_v2.services.BookingProductService;
import com.travelagency_v2.travelagency_v2.entities.BookingProduct;
import com.travelagency_v2.travelagency_v2.models.viewModels.BookingProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookingProductServiceImpl implements BookingProductService {

    private final BookingProductRepository bookingProductRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BookingProductServiceImpl(ModelMapper modelMapper, BookingProductRepository bookingProductRepository) {
        this.modelMapper = modelMapper;
        this.bookingProductRepository = bookingProductRepository;
    }

    @Override
    public BookingProductViewModel findById(long id) {
        BookingProduct bookingProduct = this.bookingProductRepository.findOneById(id);
        return this.modelMapper.map(bookingProduct,BookingProductViewModel.class);
    }
}
