package com.project.robotmate.admin.domain.price.service;

import com.project.robotmate.admin.domain.price.dto.EditPriceDto;
import com.project.robotmate.admin.domain.price.dto.PriceDto;
import com.project.robotmate.domain.entity.price.Price;
import com.project.robotmate.domain.entity.price.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultPriceService implements PriceService{

    private final PriceRepository priceRepository;

    @Override
    public List<PriceDto> getPrices() {
        return priceRepository.findAll().stream()
                .map(PriceDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public PriceDto getPrice(Long id) {
        return new PriceDto(findPrice(id));
    }

    @Override
    @Transactional
    public Long create(EditPriceDto request) {
        Price price = buildPrice(request);
        priceRepository.save(price);

        return price.getId();
    }

    @Override
    @Transactional
    public void update(Long id, EditPriceDto request) {
        Price price = findPrice(id);

        price.changeTeachingCourse(request.getTeachingCourse());
        price.changeTeachingExpenses(String.valueOf(request.getTeachingExpenses()));
        price.changeTeachingHour(request.getTeachingHour());
        price.changeType(request.getType().equals("로봇") ? request.getType() : null);
        price.changeCollectionUnit(request.getCollectionUnit());
        price.changeCost(
                zeroToEmpty(request.getMockTestCost()),
                zeroToEmpty(request.getMaterialCost()),
                zeroToEmpty(request.getClothesCost()),
                zeroToEmpty(request.getLunchMoney()),
                zeroToEmpty(request.getBoardingExpenses()),
                zeroToEmpty(request.getCarCost())
        );
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Price price = findPrice(id);
        priceRepository.delete(price);
    }

    private Price findPrice(Long id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 가격정보입니다."));
    }

    private String zeroToEmpty(String value) {
        if (value.equals("0") || ObjectUtils.isEmpty(value)) {
            return null;
        }

        return value;
    }

    private Price buildPrice(EditPriceDto request) {
        Price price = Price.builder()
                .teachingCourse(request.getTeachingCourse())
                .teachingExpenses(String.valueOf(request.getTeachingExpenses()))
                .teachingHour(request.getTeachingHour())
                .type(request.getType().equals("로봇") ? request.getType() : null)
                .collectionUnit(request.getCollectionUnit())
                .build();

        price.changeCost(
                zeroToEmpty(request.getMockTestCost()),
                zeroToEmpty(request.getMaterialCost()),
                zeroToEmpty(request.getClothesCost()),
                zeroToEmpty(request.getLunchMoney()),
                zeroToEmpty(request.getBoardingExpenses()),
                zeroToEmpty(request.getCarCost())
        );

        return price;
    }
}
