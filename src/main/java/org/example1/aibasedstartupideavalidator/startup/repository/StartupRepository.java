package org.example1.aibasedstartupideavalidator.startup.repository;

import org.example1.aibasedstartupideavalidator.startup.entity.StartupIdea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<StartupIdea, Long> {

}