package cz.uhk.ppro.ppro.repository;

import cz.uhk.ppro.ppro.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
