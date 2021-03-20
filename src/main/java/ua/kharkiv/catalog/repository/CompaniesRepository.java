package ua.kharkiv.catalog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.kharkiv.catalog.entity.Company;

public interface CompaniesRepository extends JpaRepository<Company, Long> {
    Company findByName(String companyName);

    @Override
    <S extends Company> S save(S s);
}
