package org.muni.fi.pa165.lang_school.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.muni.fi.pa165.lang_school.entities.User;

/**
 * @author Simon Hyben, 421112
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find specific user by his email
     *
     * @param email
     *            email to search in String format
     * @return return specific user by his email
     */
    @Query("SELECT u FROM #{#entityName} u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);

}
