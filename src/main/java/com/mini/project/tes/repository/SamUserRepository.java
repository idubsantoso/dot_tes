package com.mini.project.tes.repository;

import com.permata.recurring.core.model.entity.SamUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Winner [Alpabit]
 *
 * Dec 18, 2019
 */
@RepositoryRestResource
@Repository
public interface SamUserRepository extends JpaRepository<SamUserEntity, Long>, JpaSpecificationExecutor<SamUserEntity> {
	@Query(value = "select * from sam_user e where e.id_row =:id_row order by create_date desc ",nativeQuery = true)
    SamUserEntity findByIdEntity(@Param("id_row") int id_row);

    public SamUserEntity getById(Long id);

    @Query("select p from SamUserEntity p where p.activeStatus=?1 and p.superUser=?2 order by createdDate desc")
    public List<SamUserEntity> getAllByActiveStatus(String status, String superUser);

    @Query("select p from SamUserEntity p where p.username=?1 AND p.activeStatus=?2 order by createdDate asc")
    public List<SamUserEntity> getAllByUsername(String username, String status);

    @Query("select p from SamUserEntity p where p.username = ?1 and p.activeStatus = ?2 order by createdDate desc")
    public SamUserEntity findByUsernameAndActiveStatus(@Param("username") String username, @Param("activeStatus") String activeStatus);

	Optional<SamUserEntity> findByEmail(String email);
    List<SamUserEntity> findByIdIn(List<Long> userIds);
    SamUserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Modifying
    @Query("update SamUserEntity e set e.activeStatus = :status where e.id = :id")
    Integer updateStatus(@Param("id") long id, @Param("status") String status);

    @Query(value = "select a.user_id,b.role_name,a.user_department" +
            ",a.first_name,a.last_name,a.created_date,a.active_status,a.last_log_in " +
            "from sam_user a, sam_role b, sam_user_roles c " +
            "where b.id=c.role_id and a.id=c.user_id and a.user_id=:username and a.first_name=:first_name and a.last_name=:last_name and b.role_name=:role_name",nativeQuery = true)
    List<Object[]> reportUseriId(@Param("username") String username, @Param("first_name") String first_name, @Param("last_name") String last_name, @Param("role_name") String role_name);
    @Query(value = "select a.user_id,b.role_name,a.user_department" +
            ",a.first_name,a.last_name,a.created_date,a.active_status,a.last_log_in " +
            "from sam_user a, sam_role b, sam_user_roles c " +
            "where b.id=c.role_id and a.id=c.user_id and (:role_name is null or b.role_name=:role_name)",nativeQuery = true)
    List<Object[]> reportUseriIdByRoleName(@Param("role_name") String role_name);

    @Query(value = "select a.user_id,b.role_name" +
            ",a.first_name,a.last_name,a.created_date,a.created_by,a.last_modified_date,a.last_modified_by,a.last_log_in,a.active_status,a.id,a.email " +
            "from sam_user a, sam_role b, sam_user_roles c " +
            "where b.id=c.role_id and a.id=c.user_id and (:username is null or a.user_id=:username) and (:first_name is null or a.first_name=:first_name) and (:last_name is null or a.last_name=:last_name) and a.active_status=:status and (:role_name is null or b.role_name=:role_name)",nativeQuery = true)
    List<Object[]> reportUseriIdCanBeNullParamAllStatus(@Param("username") String username, @Param("first_name") String first_name, @Param("last_name") String last_name, @Param("role_name") String role_name, @Param("status") String status);

    @Query(value = "select a.user_id,b.role_name" +
            ",a.first_name,a.last_name,a.created_date,a.created_by,a.last_modified_date,a.last_modified_by,a.last_log_in,a.active_status,a.id,a.email " +
            "from sam_user a, sam_role b, sam_user_roles c " +
            "where b.id=c.role_id and a.id=c.user_id and (:username is null or a.user_id=:username) and (:first_name is null or a.first_name=:first_name) and (:last_name is null or a.last_name=:last_name) and a.active_status=:status and (:role_name is null or b.role_name=:role_name)",nativeQuery = true)
    List<Object[]> reportUseriIdCanBeNullParam(@Param("username") String username, @Param("first_name") String first_name, @Param("last_name") String last_name, @Param("role_name") String role_name, @Param("status") String status);


    @Query(value = "select b.role_name from sam_user a, sam_role b, sam_user_roles c " +
            " where b.id=c.role_id and a.id=c.user_id and a.id=:id order by a.created_date desc ",nativeQuery = true)
    String getRoleNameByUserId(@Param("id") Long id);

    @Query(value = "select b.role_name from sam_user a, sam_role b, sam_user_roles c " +
            " where b.id=c.role_id and a.id=c.user_id and a.user_id=:username and a.active_status=:active_status " +
            " order by a.created_date desc ",nativeQuery = true)
    String getRoleNameByUserName(@Param("username") String username, @Param("active_status") String active_status);

    @Query(value = "select b.role_name from sam_user a, sam_role b, sam_user_roles c " +
            " where b.id=c.role_id and a.id=c.user_id and a.email=:email order by a.created_date desc ",nativeQuery = true)
    String getRoleNameByEmail(@Param("email") String email);

    @Query(value = "select a.user_id from sam_user a, sam_role b, sam_user_roles c " +
            " where a.super_user=:superUser b.id=c.role_id and a.id=c.user_id and a.active_status=:active_status and b.role_name=:role_name" +
    		" order by a.created_date desc ",nativeQuery = true)
    List<String> getUserNameByRoleName(@Param("active_status") String active_status, @Param("role_name") String role_name, @Param("superUser") String superUser);

    @Query("select e from SamUserEntity e where ((e.lastLogOut is not null and e.lastLogOut > e.lastLogIn and e.lastLogOut < :date) " +
            "or (coalesce (e.lastLogOut, e.lastLogIn) >= e.lastLogIn and coalesce(e.lastLogOut, e.lastLogIn) < :date)) AND e.activeStatus = :status")
    List<SamUserEntity> findDormantUser(@Param("date") Date date, @Param("status") String status);

    @Query("select e from SamUserEntity e where e.superUser=:superUser and e.createdDate< :date and e.activeStatus= :status and e.lastLogIn is null")
    List<SamUserEntity> findDormantUserByCreatedDate(@Param("date") Date date, @Param("status") String status, @Param("superUser") String superUser);



    @Modifying
    @Query("update SamUserEntity e set e.activeStatus = :toBeStatus where (e.lastLogIn is not null and e.lastLogIn < :date) AND e.activeStatus = :status")
    Integer updateDormantUser(@Param("date") Date date, @Param("status") String status, @Param("toBeStatus") String toBeStatus);

    @Modifying
    @Query("update SamUserEntity e set e.activeStatus = :toBeStatus where e.lastLogIn is null AND e.createdDate < :date AND e.activeStatus = :status and e.superUser=:superUser")
    Integer updateDormantUserByCreatedDate(@Param("date") Date date, @Param("status") String status, @Param("toBeStatus") String toBeStatus, @Param("superUser") String superUser);

    @Query("select e from SamUserEntity e where e.activeStatus=: status")
    List<SamUserEntity> findByStatus(@Param("status") String status);

    SamUserEntity findById(long id);

    List<SamUserEntity> findBySuperUser(String superUser);
}