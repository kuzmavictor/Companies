package ua.kharkiv.catalog.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kharkiv.catalog.dto.CompanyData;
import ua.kharkiv.catalog.dto.NewCompanyData;
import ua.kharkiv.catalog.dto.ResponseMessage;
import ua.kharkiv.catalog.dto.serializer.SerializationUtil;
import ua.kharkiv.catalog.service.CompanyService;

import java.util.List;

/**
 * The REST endpoints witch is responsible for operation with companies.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/catalog")
public class CompaniesController {

    private CompanyService companyService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a new company to the catalog.",
            response = ResponseMessage.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The operation adding information " +
                    "of new company was successful."),
            @ApiResponse(code = 400, message = "Bad request, adjust before retrying"),
            @ApiResponse(code = 409, message = "The record about this company already exists.")
    })
    public ResponseEntity<?> addCompany(@ApiParam("New Company")
                                        @RequestBody NewCompanyData newCompanyData) {
        ResponseMessage responseMessage = companyService.add(newCompanyData);
        String json = SerializationUtil.serializeResponseMessage(responseMessage);

        return ResponseEntity.status(HttpStatus.OK)
                .body(json);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update the information about a company.",
            response = ResponseMessage.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update information of company successfully."),
            @ApiResponse(code = 400, message = "Bad request, adjust before retrying."),
            @ApiResponse(code = 404, message = "The record of information about company " +
                    "for updating does not exist")
    })
    public ResponseEntity<?> updateCompanyData(@ApiParam("Company Id")
                                               @PathVariable(value = "id") String id,
                                               @ApiParam("CompanyData")
                                               @RequestBody CompanyData companyData) {
        ResponseMessage responseMessage = companyService.update(companyData);
        String json = SerializationUtil.serializeResponseMessage(responseMessage);

        return ResponseEntity.status(HttpStatus.OK)
                .body(json);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get the information about company by her name.",
            response = CompanyData.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Getting information " +
                    "about the company was successful."),
            @ApiResponse(code = 404, message = "The record of information " +
                    "about company for getting does not exist")
    })
    public ResponseEntity<?> getCompany(@ApiParam("Company Id")
                                        @PathVariable(value = "id") String companyId) {
        CompanyData companyData = companyService.read(companyId);
        String jsonString = SerializationUtil.serializeCompanyData(companyData);

        return ResponseEntity.status(HttpStatus.OK)
                .body(jsonString);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Fetch data about all companies.",
            response = CompanyData.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Getting information " +
                    "about all companies was successful."),
            @ApiResponse(code = 404, message = "No data about all companies.")
    })
    public ResponseEntity<?> getAllCompanies(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(defaultValue = "id,desc", required = false) String[] sortBy
    ) {
        List<CompanyData> companies = companyService.readAll(page, size, sortBy);
        String jsonString = SerializationUtil.serializeListCompaniesData(companies);

        return ResponseEntity.status(HttpStatus.OK)
                .body(jsonString);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete the record with information about a company.",
            response = ResponseMessage.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleting information about " +
                    "company was successful."),
            @ApiResponse(code = 404, message = "The record of company information" +
                    " for deletion does not exist")
    })
    public ResponseEntity<?> deleteCompany(@ApiParam("Company Id")
                                           @PathVariable(value = "id") String id) {
        ResponseMessage responseMessage = companyService.delete(id);
        String jsonString = SerializationUtil.serializeResponseMessage(responseMessage);

        return ResponseEntity.status(HttpStatus.OK)
                .body(jsonString);
    }
}
