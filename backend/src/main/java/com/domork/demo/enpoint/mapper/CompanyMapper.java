package com.domork.demo.enpoint.mapper;

import com.domork.demo.enpoint.dto.CompanyDto;
import com.domork.demo.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyDto entityToDto(Company company){
        return new CompanyDto(company.getId(), company.getName());
    }
    public Company dtoToEntity (CompanyDto companyDto){
        return new Company(companyDto.getId(), companyDto.getName());
    }
}
