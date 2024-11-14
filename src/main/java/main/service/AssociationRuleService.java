package main.service;

import main.bean.AssociationRule;
import main.dao.AssociationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociationRuleService {

    @Autowired
    private AssociationRuleRepository associationRuleRepository;

    // 查询所有记录
    public List<AssociationRule> findAll() {
        return associationRuleRepository.findAll();
    }

    // 根据 ID 查询记录
    public AssociationRule findById(Long id) {
        return associationRuleRepository.findById(id).orElse(null);
    }

    // 保存或更新记录
    public AssociationRule save(AssociationRule associationRule) {
        return associationRuleRepository.save(associationRule);
    }

    // 根据 ID 删除记录
    public void deleteById(Long id) {
        associationRuleRepository.deleteById(id);
    }
}
