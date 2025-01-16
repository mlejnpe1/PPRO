package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Meeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingService {
    List<Meeting> getAllMeetings();
    Meeting getMeetingsById(Long id);
    void deleteMeetingById(Long id);
    void saveMeeting(Meeting meeting);
}
