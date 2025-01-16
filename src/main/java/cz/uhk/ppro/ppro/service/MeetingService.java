package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Meeting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MeetingService {
    List<Meeting> getAllMeetings();
    Optional<Meeting> getMeetingsById(Long id);
    void deleteMeetingById(Long id);
    void saveMeeting(Meeting meeting);
}
