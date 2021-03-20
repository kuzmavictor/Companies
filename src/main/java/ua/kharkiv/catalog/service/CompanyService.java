package ua.kharkiv.catalog.service;

import ua.kharkiv.catalog.dto.CompanyData;
import ua.kharkiv.catalog.dto.NewCompanyData;
import ua.kharkiv.catalog.dto.ResponseMessage;

import java.util.List;

/**
 * A service provides handling operation with data of companies.
 *
 * <p>This layer defines the business or logical model of the data
 * and specifies the mapping between the business model and the physical schemas.
 */
public interface CompanyService {

    /**
     * Obtains the {@code CompanyData} object containing stored company data.
     *
     * @param companyId
     *         an identifier of company
     * @return the {@code CompanyData} object that contains information about company
     * @throws CatalogOperationException
     *         if the process obtaining data
     *         about company is failed
     */
    CompanyData read(String companyId) throws CatalogOperationException;

    /**
     * Obtains all records that contains information about companies.
     *
     * @return the information about all companies
     * @throws CatalogOperationException
     *         if the process of obtaining data
     *         about all companies is failed
     */
    List<CompanyData> readAll(Integer page, Integer size, String[] sort) throws CatalogOperationException;

    /**
     * Adds information about new company.
     *
     * <p>The {@code CatalogOperationException} is thrown if the
     * company with the same name will be existed.
     *
     * @param newCompanyData
     *         a DTO with information about new company
     * @throws CatalogOperationException
     *         if operation of adding data of companies is failed
     */
    ResponseMessage add(NewCompanyData newCompanyData) throws CatalogOperationException;

    /**
     * Updates the information about a company.
     *
     * @param companyData
     *         a DTO with updated information about a company
     * @throws CatalogOperationException
     *         is thrown if operation
     *         of updating data about of company is failed
     */
    ResponseMessage update(CompanyData companyData)
            throws CatalogOperationException;

    /**
     * Deletes the information about a company.
     *
     * @param companyIdentifier
     *         a unique identifier of the record containing
     *         information about the company
     * @throws CatalogOperationException
     *         if the operation of deleting information record is failed
     */
    ResponseMessage delete(String companyIdentifier)
            throws CatalogOperationException;
}
