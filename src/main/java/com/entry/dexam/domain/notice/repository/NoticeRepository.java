package com.entry.dexam.domain.notice.repository;

import com.entry.dexam.domain.notice.entity.Notice;
import com.entry.dexam.domain.notice.enums.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(""" 
        select n
        from Notice n
        where n.target = :target
        and (:keyword is null 
        or n.title like concat('%', :keyword, '%')
        or n.content like concat('%', :keyword, '%') 
        )
        order by n.createdAt DESC 
""")
    List<Notice> findNotices(@Param("target") Target target,
                             @Param("keyword") String keyword);

    @Query("""
        select n
        from Notice n
        where n.target = :target
        and n.classInfo.classId.grade = :grade
        and n.classInfo.classId.classNum = :classNo
        and (:keyword is null 
        or n.title like concat('%', :keyword, '%')
        or n.content like concat('%', :keyword, '%') 
        )
        order by n.createdAt desc 
""")
    List<Notice> findClassNotices(@Param("target") Target target,
                                  @Param("grade") int grade,
                                  @Param("classNo") int classNo,
                                  @Param("keyword") String keyword);
}
