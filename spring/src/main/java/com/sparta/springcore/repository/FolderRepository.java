package com.sparta.springcore.repository;
import com.sparta.springcore.model.User;
import com.sparta.springcore.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);
    boolean existsByUserAndName(User user, String name);
}

