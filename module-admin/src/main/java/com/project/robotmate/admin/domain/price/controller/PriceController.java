package com.project.robotmate.admin.domain.price.controller;

import com.project.robotmate.admin.domain.gallery.dto.response.GalleryResponse;
import com.project.robotmate.admin.domain.gallery.service.GalleryService;
import com.project.robotmate.admin.domain.price.dto.EditPriceDto;
import com.project.robotmate.admin.domain.price.dto.PriceDto;
import com.project.robotmate.admin.domain.price.service.PriceService;
import com.project.robotmate.admin.global.util.DateUtil;
import com.project.robotmate.domain.common.dto.Page;
import com.project.robotmate.domain.common.dto.Searchable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PriceController {

   private final PriceService priceService;
    @GetMapping(value = "/prices")
    public String viewPrices(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "type", required = false) String type,
            Model model
    ){
        List<PriceDto> prices = priceService.getPrices();

        model.addAttribute("prices", prices);

        return "price/list";
    }

    @GetMapping("/prices/edit/{id}")
    public String viewEditPrice(
            @PathVariable("id") Long id,
            Model model
    ) {
        PriceDto price = priceService.getPrice(id);
        model.addAttribute("price", price);

        return "price/edit";
    }

    @PostMapping("/prices/edit/{id}")
    public String editPrice(
            @PathVariable("id") Long id,
            @ModelAttribute("price") EditPriceDto request
    ) {
        priceService.update(id, request);
        return "redirect:/prices/edit/" + id;
    }


}