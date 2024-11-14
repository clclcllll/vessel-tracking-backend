package main.service;

import main.bean.Voyage;
import main.dao.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    // 查询所有航程信息
    public List<Voyage> findAll() {
        return voyageRepository.findAll();
    }

    // 根据 ID 查询航程信息
    public Voyage findById(Long id) {
        return voyageRepository.findById(id).orElse(null);
    }

    // 根据 shipId 查询单条航程信息
    public Voyage findByShipId(Long shipId) {
        return voyageRepository.findByShipId(shipId);
    }

    // 根据 arrivalPortId 查询航程信息
    public List<Voyage> findByArrivalPortId(Integer arrivalPortId) {
        return voyageRepository.findByArrivalPortId(arrivalPortId);
    }

    // 根据 departurePortId 查询航程信息
    public List<Voyage> findByDeparturePortId(Integer departurePortId) {
        return voyageRepository.findByDeparturePortId(departurePortId);
    }

    // 保存或更新航程信息
    public Voyage save(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    // 根据 ID 删除航程信息
    public void deleteById(Long id) {
        voyageRepository.deleteById(id);
    }
}
