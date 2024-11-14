package main.service;

import main.bean.Country;
import main.dao.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // 查询所有国家
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    // 根据 ID 查询国家，返回 null 或 Country
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    // 根据国家名称查询国家，返回 null 或 Country
    public Country findByCountryName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    // 保存或更新国家信息
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    // 根据 ID 删除国家
    public void deleteById(Integer id) {
        countryRepository.deleteById(id);
    }
}
