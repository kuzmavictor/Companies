package ua.kharkiv.catalog.given;

import com.google.errorprone.annotations.Immutable;
import ua.kharkiv.catalog.dto.CompanyData;
import ua.kharkiv.catalog.dto.NewCompanyData;
import ua.kharkiv.catalog.entity.Company;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * A simple mapper implementation using native java features.
 * <p>Allows mapping data from immutable DTOs to entities and vise versa.
 */
@Immutable
public final class Mapper {

    /**
     * Creates entity {@code Company} using data from {@code NewCompanyData}.
     *
     * @param companyData
     *         an instance of {@code NewCompanyData}
     * @return the filling entity {@code Company}
     */
    public static Company entityFromNewData(NewCompanyData companyData) {
        ZoneId zoneId = ZoneOffset.UTC;
        Company company = new Company();
        company.setName(companyData.getCompanyName());
        company.setDescription(companyData.getTitle());
        company.setCreationDate(LocalDateTime.now(zoneId));

        return company;
    }

    /**
     * Creates entity {@code Company} using data from {@code CompanyData}.
     *
     * @param companyData
     *         an instance of {@code CompanyData}
     * @return the filling {@code Company} entity
     */
    public static Company entityFromExistingData(CompanyData companyData) {
        Company company = new Company();
        company.setId(Long.valueOf(companyData.getId()));
        company.setName(companyData.getName());
        company.setDescription(companyData.getTitle());
        company.setCreationDate(LocalDateTime.parse(companyData.getCreationDate()));

        return company;
    }

    /**
     * Creates DTO {@code CompanyData} using data from entity {@code Company}.
     *
     * @param company
     *         a {@code Company} entity
     * @return the DTO containing information about company
     */
    public static CompanyData companyDataFromEntity(Company company) {
        String id = String.valueOf(company.getId());
        String name = company.getName();
        String description = company.getDescription();
        String foundDate = String.valueOf(company.getCreationDate());

        return new CompanyData(id, name, description, foundDate);
    }
}
