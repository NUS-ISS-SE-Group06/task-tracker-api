package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
@Repository

public interface TaskInfoRepository extends  JpaRepository<TaskInfo, Integer> {


    @Query(value =
            "select a.user_id,a.name,a.group_id,a.group_name,a.task_reward_point " +
            "from (select a.user_id, a.name, a.group_id, a.group_name, sum(a.task_reward_point) as task_reward_point " +
            "        from (select a.user_id, a.name, a.group_id, c.group_name, b.task_reward_point " +
            "                from user_info a " +
            "                inner join task_info b on a.user_id = b.task_assignee and b.delete_flag = 'FALSE' " +
            "                inner join group_info c on a.group_id = c.group_id and c.delete_flag = 'FALSE' " +
            "                where a.delete_flag = 'FALSE') a " +
            "        group by a.user_id, a.name, a.group_id, a.group_name " +
            ") a " +
            "where a.group_id = :groupId " +
            "order by a.task_reward_point desc ", nativeQuery = true)
    List<Object[]> findTaskRewardPointsByGroupId(@Param("groupId") Integer groupId);




}



