package com.domork.demo.enpoint;

import com.domork.demo.enpoint.dto.CompanyDto;
import com.domork.demo.entity.Company;
import com.domork.demo.exception.NotFoundException;
import com.domork.demo.enpoint.mapper.CompanyMapper;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(CompanyEndpoint.BASE_URL)
@RestController
public class CompanyEndpoint {
    static final String BASE_URL = "/company";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyEndpoint(CompanyMapper companyMapper, CompanyService companyService) {
        this.companyMapper = companyMapper;
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        LOGGER.info("GET " + BASE_URL + "ALL COMPANIES");
        try {
            List<Company> companies = companyService.getAllCompanies();
            List<CompanyDto> companyDtos = new ArrayList<>();
            for (Company company : companies) {
                companyDtos.add(companyMapper.entityToDto(company));
            }
            return companyDtos;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading companies", e);

        }
    }

    @PutMapping("/")
    public CompanyDto put(@RequestBody CompanyDto company) {
        LOGGER.info("PUT " + BASE_URL + "/{}", company);
        try {
            return companyMapper.entityToDto(companyService.putNewCompany(companyMapper.dtoToEntity(company)));
        } catch (ValidationException e){
            LOGGER.warn("PUT COMPANY THROWS BAD REQUEST ({})", company);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }

    }
}
