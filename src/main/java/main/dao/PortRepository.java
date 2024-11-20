package main.dao;

import main.bean.Port ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortRepository extends JpaRepository<Port, Integer> {
    // 根据 portCode 查询记录
    Port findByPortCode(String portCode);

    // 根据 port_code 查询所有记录
    @Query(value =
            "SELECT p.id, p.port_code, p.port_name, p.country, p.port_type, p.port_size, " +
                    "ST_X(p.position) AS longitude, ST_Y(p.position) AS latitude, p.time_zone, p.anchorage, " +
                    "p.berth_draft, p.chart_number, p.port_authority, p.address, p.phone, p.fax, p.website, " +
                    "p.introduction, p.create_time, p.update_time " +
                    "FROM ports p WHERE p.port_code = :portCode",
            nativeQuery = true)
    List<Object[]> findPortByCodeWithCoordinatesNative(@Param("portCode") String portCode);

    // 根据 id 查询所有记录
    @Query(value =
            "SELECT p.id, p.port_code, p.port_name, p.country, p.port_type, p.port_size, " +
                    "ST_X(p.position) AS longitude, ST_Y(p.position) AS latitude, p.time_zone, p.anchorage, " +
                    "p.berth_draft, p.chart_number, p.port_authority, p.address, p.phone, p.fax, p.website, " +
                    "p.introduction, p.create_time, p.update_time " +
                    "FROM ports p WHERE p.id = :id",
            nativeQuery = true)
    List<Object[]> findPortByIdWithCoordinatesNative(@Param("id") Integer id);
}
