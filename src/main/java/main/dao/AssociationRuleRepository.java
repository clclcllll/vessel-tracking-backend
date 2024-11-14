package main.dao;

import main.bean.AssociationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRuleRepository extends JpaRepository<AssociationRule, Long> {
    // JpaRepository 提供了基础的增删改查方法，无需额外定义
}
