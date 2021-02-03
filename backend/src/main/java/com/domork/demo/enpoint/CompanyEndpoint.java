package com.domork.demo.enpoint;

import com.domork.demo.enpoint.dto.CompanyDto;
import com.domork.demo.enpoint.dto.CompanyExtendedDto;
import com.domork.demo.enpoint.mapper.CompanyExtendedMapper;
import com.domork.demo.entity.Company;
import com.domork.demo.entity.CompanyExtended;
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
    private final CompanyExtendedMapper companyExtendedMapper;
    @Autowired
    public CompanyEndpoint(CompanyMapper companyMapper, CompanyService companyService, CompanyExtendedMapper companyExtendedMapper) {
        this.companyMapper = companyMapper;
        this.companyService = companyService;
        this.companyExtendedMapper=companyExtendedMapper;
    }

    @GetMapping
    public ResponseEntity<List<CompanyExtendedDto>>
    getAllCompaniesWithGivenName(@RequestParam(value = "name", required = false, defaultValue = "")
                                         String name) {
        LOGGER.info("getAllCompaniesWithGivenName: ({})", name);
        try {
            List<CompanyExtended> companies = companyService.getAllCompaniesWithGivenName(name);
            List<CompanyExtendedDto> companyDtos = new ArrayList<>();
            for (CompanyExtended company : companies) {
                companyDtos.add(companyExtendedMapper.entityToDto(company));
            }
            return new ResponseEntity<>(companyDtos, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Error during reading companies. Possible reason: no such a company is stored", e);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyExtendedDto> getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return new ResponseEntity<CompanyExtendedDto>(companyExtendedMapper.entityToDto(companyService.getOneById(id)), HttpStatus.OK);
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



    @PutMapping
    public ResponseEntity<CompanyExtendedDto> put(@RequestBody CompanyExtendedDto company) {
        LOGGER.info("PUT " + BASE_URL + "/{}", company);
        try {

            //check if the company already exists
            try {
                companyService.getCompanyByName(company.getName());
            } catch (NotFoundException e) {

            //if not -> the new company will be saved
                return new ResponseEntity<CompanyExtendedDto>
                        (companyExtendedMapper.entityToDto(
                                companyService.putNewCompany
                                        (companyExtendedMapper.dtoToEntity(company))), HttpStatus.CREATED);
            }
        } catch (ValidationException e) {
            LOGGER.warn("PUT COMPANY THROWS VALIDATION EXCEPTION ({})", company);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
        //otherwise bad request will be sent back
        LOGGER.warn("PUT COMPANY PROVIDES ALREADY EXISTING COMPANY ({})", company);
        throw new ResponseStatusException(HttpStatus.CONFLICT, "This company already exists");
    }

    @GetMapping(value = "/brew_coffee_with_a_teapot")
    public String teaPot() {
        LOGGER.info("I'M A TEAPOT" + BASE_URL +"/brew_coffee_with_a_teapot");
        return "I am a teapot!";
    }

}
