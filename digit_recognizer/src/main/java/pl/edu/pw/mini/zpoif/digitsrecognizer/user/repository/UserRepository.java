package pl.edu.pw.mini.zpoif.digitsrecognizer.user.repository;

import org.springframework.data.repository.CrudRepository;

import pl.edu.pw.mini.zpoif.digitsrecognizer.user.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
	
}
