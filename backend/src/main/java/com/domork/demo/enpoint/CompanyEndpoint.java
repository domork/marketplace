package com.domork.demo.enpoint;

import com.domork.demo.enpoint.dto.CompanyDto;
import com.domork.demo.entity.Company;
import com.domork.demo.exception.NotFoundException;
import com.domork.demo.enpoint.mapper.CompanyMapper;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.service.CompanyService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDto> getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return new ResponseEntity<CompanyDto>(companyMapper.entityToDto(companyService.getOneById(id)), HttpStatus.OK);
        } catch (NotFoundException e) {
            LOGGER.warn("GET COMPANY WITH ID: " + id + " THROWS 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading company", e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CompanyDto> deleteOneById(@PathVariable("id") Long id) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", id);
        try {

            companyService.deleteById(id);
            return new ResponseEntity<CompanyDto>(HttpStatus.OK);
        } catch (NotFoundException e) {
            LOGGER.warn("DELETE COMPANY WITH ID: " + id + " THROWS 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting company", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        LOGGER.info("GET ALL COMPANIES");
        try {
            List<Company> companies = companyService.getAllCompanies();
            List<CompanyDto> companyDtos = new ArrayList<>();
            for (Company company : companies) {
                companyDtos.add(companyMapper.entityToDto(company));
            }
            return new ResponseEntity<>(companyDtos, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading companies", e);

        }
    }

    @PutMapping("/")
    public ResponseEntity<CompanyDto> put(@RequestBody CompanyDto company) {
        LOGGER.info("PUT " + BASE_URL + "/{}", company);
        try {
            return new ResponseEntity<>(companyMapper.entityToDto(companyService.putNewCompany(companyMapper.dtoToEntity(company))), HttpStatus.OK);

        } catch (ValidationException e) {
            LOGGER.warn("PUT COMPANY THROWS BAD REQUEST ({})", company);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }
}
