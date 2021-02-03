package com.domork.demo.enpoint.mapper;

import com.domork.demo.enpoint.dto.CompanyExtendedDto;
import com.domork.demo.entity.CompanyExtended;
import org.springframework.stereotype.Component;

@Component
public class CompanyExtendedMapper {
    public CompanyExtendedDto entityToDto(CompanyExtended companyExtended) {
        return new CompanyExtendedDto(companyExtended.getId(), companyExtended.getName(), companyExtended.getWebsite(), companyExtended.getDescription(), companyExtended.getBasedIn());
    }

    public CompanyExtended dtoToEntity (CompanyExtendedDto companyExtendedDto){
        return new CompanyExtended(companyExtendedDto.getId(), companyExtendedDto.getName(), companyExtendedDto.getWebsite(), companyExtendedDto.getDescription(), companyExtendedDto.getBasedIn());
    }

}
