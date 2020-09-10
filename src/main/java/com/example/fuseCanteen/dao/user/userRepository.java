package com.example.fuseCanteen.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fuseCanteen.Model.user.users;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by aishwarya on 9/4/20.
 */

@Repository
public interface userRepository extends JpaRepository<users,Integer> {

    @Query("SELECT entity from users entity")
    List<users> getAllUsers();

    @Query("SELECT entity from users entity where entity.empNumber=:empNumber")
    users getUserByempNum(@Param("empNumber") String  empNumber);

    @Query("SELECT entity from users entity where entity.userName=:userName")
    users getUserByUserName(@Param("userName") String  userName);

    @Query("SELECT entity from users entity where entity.id=:userId")
    users getUserById(@Param("userId") Integer userId);

}
