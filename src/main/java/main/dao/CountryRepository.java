package main.dao;

import main.bean.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    // JpaRepository 提供了基础的增删改查方法，无需额外定义
    // 根据国家名称查询国家
    Country findByCountryName(String countryName);
}
