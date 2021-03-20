package ua.kharkiv.catalog.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kharkiv.catalog.dto.CompanyData;
import ua.kharkiv.catalog.dto.NewCompanyData;
import ua.kharkiv.catalog.dto.ResponseMessage;
import ua.kharkiv.catalog.entity.Company;
import ua.kharkiv.catalog.given.Mapper;
import ua.kharkiv.catalog.given.ObjectFieldsDiffChecker;
import ua.kharkiv.catalog.repository.CompaniesRepository;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompaniesRepository companiesRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CompanyData read(String companyId) throws CatalogOperationException {
        Long id = Long.valueOf(companyId);
        Optional<Company> foundCompany = companiesRepository.findById(id);

        if (foundCompany.isEmpty()) {
            String exceptionMessage = "The information about company does not exist.";
            throw new CatalogOperationException(exceptionMessage, HttpStatus.NOT_FOUND);
        }
        CompanyData companyData = Mapper.companyDataFromEntity(foundCompany.get());

        return companyData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CompanyData> readAll(Integer page, Integer size, String[] sortBy) throws
            CatalogOperationException {

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (sortBy[0].contains(",")) {
            for (String sortOrder : sortBy) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
        }
        Page<Company> companies = companiesRepository.findAll(
                (PageRequest.of(page, size, Sort.by(orders))));

        if (companies.isEmpty()) {
            String exceptionMessage = "The information about companies does not exist.";
            throw new CatalogOperationException(exceptionMessage, HttpStatus.NOT_FOUND);
        }

        List<CompanyData> fetchedCompanies = new ArrayList<>();
        companies.forEach(company -> {
            CompanyData companyData = Mapper.companyDataFromEntity(company);
            fetchedCompanies.add(companyData);
        });

        return fetchedCompanies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public ResponseMessage add(NewCompanyData newCompanyData)
            throws CatalogOperationException {
        Company company = Mapper.entityFromNewData(newCompanyData);
        String companyName = company.getName();
        Company foundCompany = companiesRepository.findByName(companyName);

        if (foundCompany != null) {
            String exceptionMessage = "The company with name " + companyName + " already exists";
            throw new CatalogOperationException(exceptionMessage, HttpStatus.CONFLICT);
        }
        companiesRepository.save(company);
        String message = "Adding information about the new company was successful.";

        return new ResponseMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public ResponseMessage delete(String companyIdentifier)
            throws CatalogOperationException {
        Optional<Company> company = companiesRepository.findById(Long.valueOf(companyIdentifier));

        if (!company.isPresent()) {
            String exceptionMessage = "The record with information about company does not exist.";
            throw new CatalogOperationException(exceptionMessage, HttpStatus.NOT_FOUND);
        }
        companiesRepository.deleteById(Long.valueOf(companyIdentifier));
        String responseMessage = "Deleting record about the company information was successful.";

        return new ResponseMessage(responseMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public ResponseMessage update(CompanyData companyData)
            throws CatalogOperationException {
        Company updatedCompany = Mapper.entityFromExistingData(companyData);
        updatedCompany.setId(Long.valueOf(companyData.getId()));
        String companyName = updatedCompany.getName();

        Company foundCompany = companiesRepository.findByName(companyName);

        if (foundCompany == null) {
            String responseMessage = "The updated company did not exist, " +
                    "but new company was successfully created from current data.";
            Company newCompany = Mapper.entityFromExistingData(companyData);
            companiesRepository.save(newCompany);

            return new ResponseMessage(responseMessage);
        }

        return checkDifference(updatedCompany, foundCompany);
    }

    /**
     * Check the differences between two entities.
     * <p> Helps to avoid unnecessary data update operations if they
     * do not differ from the recorded data.
     *
     * @param updatedCompany
     *         an update company entity
     * @param foundCompany
     *         a company entity form datasource
     * @return the {@code ResponseMessage} object that contains details about the result
     *         of the data update
     * @see ObjectFieldsDiffChecker#checkDiffFieldsOfSameObject
     */
    private ResponseMessage checkDifference(Company updatedCompany, Company foundCompany) {
        List<Object> differenceFields;
        try {
            differenceFields = ObjectFieldsDiffChecker
                    .checkDiffFieldsOfSameObject(updatedCompany, foundCompany);
        } catch (IllegalAccessException e) {
            String errorMessage = "The updating data is impossible at the moment";
            throw new CatalogOperationException(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (differenceFields.isEmpty()) {
            String responseMessage = "The updated data is the same as current data.";
            return new ResponseMessage(responseMessage);
        }

        companiesRepository.save(updatedCompany);
        String message = "The updating information about company was successfully.";

        return new ResponseMessage(message);
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
