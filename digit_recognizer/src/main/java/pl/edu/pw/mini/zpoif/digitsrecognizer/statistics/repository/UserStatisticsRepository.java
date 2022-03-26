package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.repository;

import org.springframework.data.repository.CrudRepository;

import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.entity.UserStatistics;

public interface UserStatisticsRepository extends CrudRepository<UserStatistics, String>{

}
