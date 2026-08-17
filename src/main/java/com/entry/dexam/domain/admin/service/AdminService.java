package com.entry.dexam.domain.admin.service;

import com.entry.dexam.domain.admin.dto.UpdateClassRequest;
import com.entry.dexam.domain.admin.dto.UpdateRoleRequest;
import com.entry.dexam.domain.auth.entity.ClassId;
import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.ClassInfoRepository;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.global.exception.exceptions.ClassNotFoundException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final UserRepository userRepository;
    private final ClassInfoRepository classInfoRepository;

    public void updateRole(Long userId, UpdateRoleRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.assignRole(dto.role());

        userRepository.save(user);
    }

    public void updateClass(Long userId, UpdateClassRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ClassId classId = new ClassId(
                dto.grade(), dto.classNum()
        );

        ClassInfo classInfo = classInfoRepository.findById(classId)
                .orElseThrow(() -> ClassNotFoundException.EXCEPTION);

        user.assignClass(classInfo);

        userRepository.save(user);
    }
}
